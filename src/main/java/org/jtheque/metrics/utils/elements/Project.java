package org.jtheque.metrics.utils.elements;

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