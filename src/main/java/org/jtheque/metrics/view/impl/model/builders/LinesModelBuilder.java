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
import org.jtheque.metrics.view.impl.nodes.lines.physical.ClassPhysicalLinesNode;
import org.jtheque.metrics.view.impl.nodes.lines.physical.ConstructorPhysicalLinesNode;
import org.jtheque.metrics.view.impl.nodes.lines.physical.ConstructorsPhysicalLinesNode;
import org.jtheque.metrics.view.impl.nodes.lines.physical.MethodPhysicalLinesNode;
import org.jtheque.metrics.view.impl.nodes.lines.physical.MethodsPhysicalLinesNode;
import org.jtheque.metrics.view.impl.nodes.lines.physical.PackagePhysicalLinesNode;
import org.jtheque.metrics.view.impl.nodes.lines.physical.PhysicalLinesRootNode;
import org.jtheque.metrics.view.impl.nodes.lines.physical.ProjectPhysicalLinesNode;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A builder to add the "lines" metrics node to the root node of the tree table model.
 *
 * @author Baptiste Wicht
 */
public final class LinesModelBuilder {
    /**
     * Create a new LinesOfCodeModelBuilder.
     */
    private LinesModelBuilder() {
        super();
    }

    /**
     * Add Lines nodes to the root.
     *
     * @param projects The projects.
     * @param root     The root node.
     */
    public static void addLinesNodes(Iterable<Project> projects, AbstractMutableTreeTableNode root) {
        PhysicalLinesRootNode modelNode = new PhysicalLinesRootNode(Managers.getManager(ILanguageManager.class).getMessage("results.models.lines.physical"));

        double classes = 0;

        for (Project project : projects) {
            AbstractMutableTreeTableNode projectNode = new ProjectPhysicalLinesNode(project);

            addSub(projectNode, project.getRootPackage());

            modelNode.add(projectNode);

            modelNode.setTotal(modelNode.getTotal() + project.getRootPackage().getTotalNumberLines());

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

        AbstractMutableTreeTableNode packageNode = new PackagePhysicalLinesNode(aPackage);

        for (Package p : aPackage.getPackages()) {
            addSub(packageNode, p);
        }

        for (Class c : aPackage.getClasses()) {
            AbstractMutableTreeTableNode classNode = new ClassPhysicalLinesNode(c);

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
            Collection<MethodPhysicalLinesNode> methodNodes = new ArrayList<MethodPhysicalLinesNode>(c.getMethods().size());

            int linesOfCode = 0;

            for (Method method : c.getMethods()) {
                methodNodes.add(new MethodPhysicalLinesNode(method));

                linesOfCode += method.getCodeLines();
            }

            double linesOfCodeMethod = linesOfCode / (double) methodNodes.size();

            AbstractMutableTreeTableNode methodsNode = new MethodsPhysicalLinesNode(
                    Managers.getManager(ILanguageManager.class).getMessage("results.model.nodes.methods"),
                    linesOfCode,
                    linesOfCodeMethod
            );

            for (MethodPhysicalLinesNode methodNode : methodNodes) {
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
            Collection<ConstructorPhysicalLinesNode> constructorNodes = new ArrayList<ConstructorPhysicalLinesNode>(c.getConstructors().size());

            int linesOfCode = 0;

            for (Constructor constructor : c.getConstructors()) {
                constructorNodes.add(new ConstructorPhysicalLinesNode(constructor));

                linesOfCode += constructor.getCodeLines();
            }

            double linesOfCodeConstructor = linesOfCode / (double) constructorNodes.size();

            AbstractMutableTreeTableNode constructorsNode = new ConstructorsPhysicalLinesNode(
                    Managers.getManager(ILanguageManager.class).getMessage("results.model.nodes.constructors"),
                    linesOfCode,
                    linesOfCodeConstructor
            );

            for (ConstructorPhysicalLinesNode constructorNode : constructorNodes) {
                constructorsNode.add(constructorNode);
            }

            classNode.add(constructorsNode);
        }
    }
}
