package org.jtheque.metrics.view.impl.model.builders;

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
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.metrics.utils.elements.Class;
import org.jtheque.metrics.utils.elements.Constructor;
import org.jtheque.metrics.utils.elements.Method;
import org.jtheque.metrics.utils.elements.Package;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.view.impl.nodes.lines.comment.ClassLinesOfCommentNode;
import org.jtheque.metrics.view.impl.nodes.lines.comment.ConstructorLinesOfCommentNode;
import org.jtheque.metrics.view.impl.nodes.lines.comment.ConstructorsLinesOfCommentNode;
import org.jtheque.metrics.view.impl.nodes.lines.comment.LinesOfCommentRootNode;
import org.jtheque.metrics.view.impl.nodes.lines.comment.MethodLinesOfCommentNode;
import org.jtheque.metrics.view.impl.nodes.lines.comment.MethodsLinesOfCommentNode;
import org.jtheque.metrics.view.impl.nodes.lines.comment.PackageLinesOfCommentNode;
import org.jtheque.metrics.view.impl.nodes.lines.comment.ProjectLinesOfCommentNode;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A builder to add the "lines of comment" metrics node to the root node of the tree table model.
 *
 * @author Baptiste Wicht
 */
public final class LinesOfCommentModelBuilder {
    /**
     * Create a new LinesOfCodeModelBuilder.
     */
    private LinesOfCommentModelBuilder() {
        super();
    }

    /**
     * Add Lines of comment nodes to the root.
     *
     * @param projects The projects.
     * @param root     The root node.
     */
    public static void addLinesOfCommentNodes(Iterable<Project> projects, AbstractMutableTreeTableNode root) {
        LinesOfCommentRootNode modelNode = new LinesOfCommentRootNode(Managers.getManager(ILanguageManager.class).getMessage("results.models.lines.comment"));

        double classes = 0;

        for (Project project : projects) {
            AbstractMutableTreeTableNode projectNode = new ProjectLinesOfCommentNode(project);

            addSub(projectNode, project.getRootPackage());

            modelNode.add(projectNode);

            modelNode.setTotal(modelNode.getTotal() + project.getRootPackage().getTotalNumberLinesOfComment());

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

        AbstractMutableTreeTableNode packageNode = new PackageLinesOfCommentNode(aPackage);

        for (Package p : aPackage.getPackages()) {
            addSub(packageNode, p);
        }

        for (Class c : aPackage.getClasses()) {
            AbstractMutableTreeTableNode classNode = new ClassLinesOfCommentNode(c);

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
    private static void addMethods(Class c, AbstractMutableTreeTableNode classNode) {
        if (!c.getMethods().isEmpty()) {
            Collection<MethodLinesOfCommentNode> methodNodes = new ArrayList<MethodLinesOfCommentNode>(c.getMethods().size());

            int lines = 0;

            for (Method method : c.getMethods()) {
                methodNodes.add(new MethodLinesOfCommentNode(method));

                lines += method.getCommentLines();
            }

            double linesMethod = lines / (double) methodNodes.size();

            AbstractMutableTreeTableNode methodsNode = new MethodsLinesOfCommentNode(
                    Managers.getManager(ILanguageManager.class).getMessage("results.model.nodes.methods"),
                    lines,
                    linesMethod
            );

            for (MethodLinesOfCommentNode methodNode : methodNodes) {
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
            Collection<ConstructorLinesOfCommentNode> constructorNodes = new ArrayList<ConstructorLinesOfCommentNode>(c.getConstructors().size());

            int lines = 0;

            for (Constructor constructor : c.getConstructors()) {
                constructorNodes.add(new ConstructorLinesOfCommentNode(constructor));

                lines += constructor.getCommentLines();
            }

            double linesConstructor = lines / (double) constructorNodes.size();

            AbstractMutableTreeTableNode constructorsNode = new ConstructorsLinesOfCommentNode(
                    Managers.getManager(ILanguageManager.class).getMessage("results.model.nodes.constructors"),
                    lines,
                    linesConstructor
            );

            for (ConstructorLinesOfCommentNode constructorNode : constructorNodes) {
                constructorsNode.add(constructorNode);
            }

            classNode.add(constructorsNode);
        }
    }
}
