package org.jtheque.metrics.utils.elements;

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

/**
 * A project.
 *
 * @author Baptiste Wicht
 */
public final class Project {
    private Package rootPackage;
    private final String name;

    /**
     * Construct a new Project.
     *
     * @param name The name of the project.
     */
    public Project(String name) {
        super();

        this.name = name;
    }

    /**
     * Set the root package of the project.
     *
     * @param rootPackage The root package of the project.
     */
    public void setRootPackage(Package rootPackage) {
        this.rootPackage = rootPackage;
    }

    /**
     * Return the root package of the project.
     *
     * @return The root package.
     */
    public Package getRootPackage() {
        return rootPackage;
    }

    /**
     * Return the name of the project.
     *
     * @return The name of the project.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the average lines of code by class.
     *
     * @return The average lines of code by class.
     */
    public double getAverageLinesOfCodeClass() {
        return rootPackage.getTotalNumberLinesOfCode() / (double) rootPackage.getTotalNumberOfClasses();
    }

    /**
     * Return the average lines of comment by class.
     *
     * @return The average lines of comment by class.
     */
    public double getAverageLinesOfCommentClass() {
        return rootPackage.getTotalNumberLinesOfComment() / (double) rootPackage.getTotalNumberOfClasses();
    }

    /**
     * Return the average lines by class.
     *
     * @return The average lines by class.
     */
    public double getAverageLinesClass() {
        return rootPackage.getTotalNumberLines() / (double) rootPackage.getTotalNumberOfClasses();
    }

    @Override
    public String toString() {
        return "Project{" +
                "rootPackage=" + rootPackage +
                ", name='" + name + '\'' +
                '}';
    }
}
