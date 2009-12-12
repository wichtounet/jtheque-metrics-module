package org.jtheque.metrics.view.impl.model.builders;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.metrics.utils.elements.Class;
import org.jtheque.metrics.utils.elements.Constructor;
import org.jtheque.metrics.utils.elements.Method;
import org.jtheque.metrics.utils.elements.Package;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.view.impl.nodes.lines.code.ClassLinesOfCodeNode;
import org.jtheque.metrics.view.impl.nodes.lines.code.ConstructorLinesOfCodeNode;
import org.jtheque.metrics.view.impl.nodes.lines.code.ConstructorsLinesOfCodeNode;
import org.jtheque.metrics.view.impl.nodes.lines.code.LinesOfCodeRootNode;
import org.jtheque.metrics.view.impl.nodes.lines.code.MethodLinesOfCodeNode;
import org.jtheque.metrics.view.impl.nodes.lines.code.MethodsLinesOfCodeNode;
import org.jtheque.metrics.view.impl.nodes.lines.code.PackageLinesOfCodeNode;
import org.jtheque.metrics.view.impl.nodes.lines.code.ProjectLinesOfCodeNode;

import java.util.ArrayList;
import java.util.Collection;

/*
 * This file is part of JTheque.
 *
 * JTheque is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * JTheque is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTheque.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A builder to add the "lines of code" metrics node to the root node of the tree table model.
 *
 * @author Baptiste Wicht
 */
public final class LinesOfCodeModelBuilder {
    /**
     * Create a new LinesOfCodeModelBuilder.
     */
    private LinesOfCodeModelBuilder() {
        super();
    }

    /**
     * Add Lines of code nodes to the root.
     *
     * @param projects The projects.
     * @param root     The root node.
     */
    public static void addLinesOfCodeNodes(Iterable<Project> projects, AbstractMutableTreeTableNode root) {
        LinesOfCodeRootNode modelNode = new LinesOfCodeRootNode(Managers.getManager(ILanguageManager.class).getMessage("results.models.lines.code"));

        double classes = 0;

        for (Project project : projects) {
            AbstractMutableTreeTableNode projectNode = new ProjectLinesOfCodeNode(project);

            addSub(projectNode, project.getRootPackage());

            modelNode.add(projectNode);

            modelNode.setTotal(modelNode.getTotal() + project.getRootPackage().getTotalNumberLinesOfCode());

            classes += project.getRootPackage().getTotalNumberOfClasses();
        }

        modelNode.setAverage(modelNode.getTotal() / classes);

        root.add(modelNode);
    }

    /**
     * Add a sub package to the node.
     *
     * @param node     The current node.
     * @param aPackage The package to add.
     */
    private static void addSub(AbstractMutableTreeTableNode node, Package aPackage) {
        if (aPackage.isEmpty()) {
            return;
        }

        AbstractMutableTreeTableNode packageNode = new PackageLinesOfCodeNode(aPackage);

        for (Package p : aPackage.getPackages()) {
            addSub(packageNode, p);
        }

        for (Class c : aPackage.getClasses()) {
            AbstractMutableTreeTableNode classNode = new ClassLinesOfCodeNode(c);

            addConstructors(c, classNode);
            addMethods(c, classNode);

            packageNode.add(classNode);
        }

        node.add(packageNode);
    }

    /**
     * Extract the methods from a class and add them on the node.
     *
     * @param c         The class.
     * @param classNode The node of the class.
     */
    private static void addMethods(org.jtheque.metrics.utils.elements.Class c, AbstractMutableTreeTableNode classNode) {
        if (!c.getMethods().isEmpty()) {
            Collection<MethodLinesOfCodeNode> methodNodes = new ArrayList<MethodLinesOfCodeNode>(c.getMethods().size());

            int linesOfCode = 0;

            for (Method method : c.getMethods()) {
                methodNodes.add(new MethodLinesOfCodeNode(method));

                linesOfCode += method.getCodeLines();
            }

            double linesOfCodeMethod = linesOfCode / (double) methodNodes.size();

            AbstractMutableTreeTableNode methodsNode = new MethodsLinesOfCodeNode(
                    Managers.getManager(ILanguageManager.class).getMessage("results.model.nodes.methods"),
                    linesOfCode,
                    linesOfCodeMethod
            );

            for (MethodLinesOfCodeNode methodNode : methodNodes) {
                methodsNode.add(methodNode);
            }

            classNode.add(methodsNode);
        }
    }

    /**
     * Extract the constructors from a class and add them on the node.
     *
     * @param c         The class.
     * @param classNode The node of the class.
     */
    private static void addConstructors(Class c, AbstractMutableTreeTableNode classNode) {
        if (!c.getConstructors().isEmpty()) {
            Collection<ConstructorLinesOfCodeNode> constructorNodes = new ArrayList<ConstructorLinesOfCodeNode>(c.getConstructors().size());

            int linesOfCode = 0;

            for (Constructor constructor : c.getConstructors()) {
                constructorNodes.add(new ConstructorLinesOfCodeNode(constructor));

                linesOfCode += constructor.getCodeLines();
            }

            double linesOfCodeConstructor = linesOfCode / (double) constructorNodes.size();

            AbstractMutableTreeTableNode constructorsNode = new ConstructorsLinesOfCodeNode(
                    Managers.getManager(ILanguageManager.class).getMessage("results.model.nodes.constructors"),
                    linesOfCode,
                    linesOfCodeConstructor
            );

            for (ConstructorLinesOfCodeNode constructorNode : constructorNodes) {
                constructorsNode.add(constructorNode);
            }

            classNode.add(constructorsNode);
        }
    }
}