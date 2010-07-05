package org.jtheque.metrics.services.impl.utils.count;

/*
 * Copyright JTheque (Baptiste Wicht)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.log.ILoggingManager;
import org.jtheque.metrics.utils.JavaFileFilter;
import org.jtheque.metrics.utils.elements.Class;
import org.jtheque.metrics.utils.elements.Constructor;
import org.jtheque.metrics.utils.elements.Method;
import org.jtheque.metrics.utils.elements.Package;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.utils.io.FileUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.regex.Pattern;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.visitor.VoidVisitorAdapter;

/**
 * A counter for the Java project.
 *
 * @author Baptiste Wicht
 */
public final class JavaCounter implements Counter {
    private final Project project;

    private Package lastPackage;
    private Package currentPackage;
    private Package parentPackage;

    private final FileFilter filter = new JavaFileFilter();

    private String lastParentAbsolutePath;

    private final Pointers pointers;

    private static final Pattern PATTERN = Pattern.compile("\\\\");

    /**
     * Construct a new counter for a specific project.
     *
     * @param p The project to count.
     */
    public JavaCounter(Project p) {
        super();

        project = p;

        pointers = new Pointers();
    }

    @Override
    public void count(File rootFolder) {
        count(rootFolder, true);
    }

    /**
     * Count the folder.
     *
     * @param folder The folder to count.
     * @param root   A boolean flag indicating if the folder is the root folder or not.
     */
    private void count(File folder, boolean root) {
        resolvePackages(folder, root);

        File[] files = folder.listFiles(filter);

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    count(f, false);
                } else {
                    final Class c = new Class(f.getName().replace(".java", ""));
                    currentPackage.addClass(c);

                    countJava(f, c);
                    countLines(f, c);
                }
            }
        }
    }

    /**
     * Resolve the packages for the folder. It seems searching and linking, the current package and the last package.
     *
     * @param folder The folder.
     * @param root   A boolean flag indicating if the folder is the root folder or not.
     */
    private void resolvePackages(File folder, boolean root) {
        String parentAbsolutePath = folder.getParentFile().getAbsolutePath();

        if (root) {
            currentPackage = new Package(folder.getName());
            lastPackage = currentPackage;
            project.setRootPackage(currentPackage);
        } else {
            computePackagesPaths(folder, parentAbsolutePath);
        }

        lastParentAbsolutePath = parentAbsolutePath;
    }

    /**
     * Compute the packages paths.
     *
     * @param folder             The current folder.
     * @param parentAbsolutePath The absolute path of the parent.
     */
    private void computePackagesPaths(File folder, String parentAbsolutePath) {
        currentPackage = new Package(folder.getName());

        int levels = calcLevel(parentAbsolutePath);

        if (levels < 0) {
            lastPackage.addPackage(currentPackage);
            parentPackage = lastPackage;
            lastPackage = currentPackage;
        } else if (levels == 0) {
            parentPackage.addPackage(currentPackage);
            lastPackage = currentPackage;
        } else if (levels > 0) {
            Package parent = parentPackage;

            for (int i = 0; i <= levels - 1; i++) {
                parent = parent.getParent();
            }

            parent.addPackage(currentPackage);
            parentPackage = parent;
            lastPackage = currentPackage;
        }
    }

    /**
     * Calc the level of the path.
     *
     * @param parentAbsolutePath The path to search the level for.
     *
     * @return The level of the path.
     */
    private int calcLevel(String parentAbsolutePath) {
        int levels = 0;

        if (parentAbsolutePath.equals(lastParentAbsolutePath)) {
            levels = 0;
        } else if (parentAbsolutePath.startsWith(lastParentAbsolutePath)) {
            levels = -1;
        } else if (lastParentAbsolutePath.startsWith(parentAbsolutePath)) {
            String change = lastParentAbsolutePath.replaceFirst(parentAbsolutePath.replace("\\", "\\\\"), "");

            levels = PATTERN.split(change).length - 1;
        }
        return levels;
    }

    /**
     * Count lines of the file.
     *
     * @param f The file.
     * @param c The class representing the file.
     */
    private void countLines(File f, Class c) {
        int lines = 0;
        int commentLines = 0;
        int current = 1;

        FileChannel in = null;
        try {
            in = new FileInputStream(f).getChannel();

            Scanner scanner = new Scanner(in);

            Pointer pointer = null;

            int tempCodeLines = 0;
            int tempCommentLines = 0;
            int tempPhysicalLines = 0;

            while (scanner.hasNextLine()) {
                if (pointer == null) {
                    pointer = pointers.getPointer(current);

                    if (pointer != null) {
                        tempCodeLines = 0;
                        tempCommentLines = 0;
                        tempPhysicalLines = 0;
                    }
                }

                String line = scanner.nextLine().trim();

                if (!"".equals(line)) {
                    if (isComment(line)) {
                        commentLines++;
                        tempCommentLines++;
                    } else {
                        lines++;
                        tempCodeLines++;
                    }
                }

                current++;
                tempPhysicalLines++;

                pointer = updatePointerInfos(current, pointer, tempCodeLines, tempCommentLines, tempPhysicalLines);
            }
        } catch (FileNotFoundException e) {
            Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
        } finally {
            FileUtils.close(in);
        }

        updateClassInfos(c, lines, commentLines, current);
    }

    /**
     * Update the informations of the class.
     *
     * @param c             The Class to fill.
     * @param lines         The lines.
     * @param commentLines  The lines of comment.
     * @param physicalLines The number of physical lines.
     */
    private static void updateClassInfos(Class c, int lines, int commentLines, int physicalLines) {
        c.setCodeLines(lines);
        c.setCommentLines(commentLines);
        c.setPhysicalLines(physicalLines - 1);
    }

    /**
     * Update the informations of the line pointer.
     *
     * @param current           The current line.
     * @param pointer           The current pointer or <code>null</code> if there is no pointer now.
     * @param tempCodeLines     The code lines
     * @param tempCommentLines  The comment lines.
     * @param tempPhysicalLines The physical lines.
     *
     * @return The current pointer or null if the pointer is arrived at the end of his field.
     */
    private static Pointer updatePointerInfos(int current, Pointer pointer, int tempCodeLines, int tempCommentLines, int tempPhysicalLines) {
        if (pointer != null && pointer.getStopLine() == current) {
            pointer.setCommentLines(tempCommentLines);
            pointer.setLinesOfCode(tempCodeLines - 1);
            pointer.setPhysicalLines(tempPhysicalLines);

            return null;
        }

        return pointer;
    }

    /**
     * Indicate if a line is a comment or not.
     *
     * @param line The line to test.
     *
     * @return true if the line is a comment else false.
     */
    private static boolean isComment(String line) {
        return line.startsWith("//") || line.startsWith("/*") || line.startsWith("*") || line.startsWith("*/");
    }

    /**
     * Count the file for java metrics like methods, constructors.
     *
     * @param f The file.
     * @param c The class representing the file.
     */
    private void countJava(File f, Class c) {
        FileInputStream fin = null;

        pointers.clear();

        try {
            fin = new FileInputStream(f);

            CompilationUnit cu = JavaParser.parse(fin);

            new ClassVisitorCounter(c).visit(cu, null);
        } catch (FileNotFoundException e) {
            Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
        } catch (ParseException e) {
            Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
        } finally {
            FileUtils.close(fin);
        }
    }

    /**
     * A visitor to count the java properties of the class.
     *
     * @author Baptiste Wicht
     */
    private final class ClassVisitorCounter extends VoidVisitorAdapter<Object> {
        private final Class c;

        /**
         * Construct a new ClassVisitorCounter.
         *
         * @param c The class.
         */
        private ClassVisitorCounter(Class c) {
            super();

            this.c = c;
        }

        @Override
        public void visit(ConstructorDeclaration d, Object o) {
            Constructor constructor = new Constructor(constructName(d));

            pointers.addPointer(new ConstructorPointer(constructor, d.getBeginLine(), d.getEndLine()));

            c.getConstructors().add(constructor);
        }

        @Override
        public void visit(MethodDeclaration d, Object o) {
            Method m = new Method(constructName(d));

            int annotations = 0;

            if (d.getAnnotations() != null && !d.getAnnotations().isEmpty()) {
                annotations += d.getAnnotations().size();
            }

            pointers.addPointer(new MethodPointer(m, d.getBeginLine() + annotations, d.getEndLine()));

            c.getMethods().add(m);
        }

        /**
         * Construct the constructor name.
         *
         * @param d The constructor declaration.
         *
         * @return The name of the constructor.
         */
        private String constructName(ConstructorDeclaration d) {
            return constructName(d.getName(), d.getParameters());
        }

        /**
         * Construct the method name.
         *
         * @param d The method declaration.
         *
         * @return The name of the method.
         */
        private String constructName(MethodDeclaration d) {
            return constructName(d.getName(), d.getParameters());
        }

        /**
         * Construct the name for a method or constructor with parameters.
         *
         * @param name       The name of the class or constructor.
         * @param parameters The parameters.
         *
         * @return The name.
         */
        private String constructName(String name, Iterable<Parameter> parameters) {
            StringBuilder builder = new StringBuilder(name);

            builder.append('(');

            if (parameters != null) {
                boolean separated = false;

                for (Parameter p : parameters) {
                    if (separated) {
                        builder.append(',');
                    }

                    separated = true;

                    builder.append(p.getType().toString());
                    builder.append(' ');
                    builder.append(p.getId().getName());
                }
            }

            builder.append(')');

            return builder.toString();
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration d, Object arg) {
            super.visit(d, arg);
            if (!d.getName().equals(c.getName())) {
                currentPackage.addClass(new Class(d.getName()));
            }
        }
    }
}
