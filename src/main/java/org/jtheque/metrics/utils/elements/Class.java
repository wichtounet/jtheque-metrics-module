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

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represent a class.
 *
 * @author Baptiste Wicht
 */
public final class Class {
    private final String name;
    private final Collection<Method> methods;
    private final Collection<Constructor> constructors;

    private int codeLines;
    private int commentLines;
    private int physicalLines;

    /**
     * Construct a new Class.
     *
     * @param className The name of the class.
     */
    public Class(String className) {
        super();

        name = className;
        methods = new ArrayList<Method>(20);
        constructors = new ArrayList<Constructor>(10);
    }

    /**
     * Return all the methods of the class.
     *
     * @return The methods of the class.
     */
    public Collection<Method> getMethods() {
        return methods;
    }

    /**
     * Return all the constructors of the class.
     *
     * @return The constructors of the class.
     */
    public Collection<Constructor> getConstructors() {
        return constructors;
    }

    /**
     * Return the name of the class.
     *
     * @return The name of the class.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the number of lines of code of the class.
     *
     * @return The number of lines of code of the class.
     */
    public int getCodeLines() {
        return codeLines;
    }

    /**
     * Set the number of lines of code of the class.
     *
     * @param codeLines The number of lines of code of the class.
     */
    public void setCodeLines(int codeLines) {
        this.codeLines = codeLines;
    }

    /**
     * Return the number of comment lines of the class.
     *
     * @return The number of comment lines of the class.
     */
    public int getCommentLines() {
        return commentLines;
    }

    /**
     * Set the number of comment lines of the class.
     *
     * @param commentLines The number of comment lines of the class.
     */
    public void setCommentLines(int commentLines) {
        this.commentLines = commentLines;
    }

    /**
     * Return the number of physical lines of the class.
     *
     * @return The number of physical lines of the class.
     */
    public int getPhysicalLines() {
        return physicalLines;
    }

    /**
     * Set the number of physical lines of the class.
     *
     * @param physicalLines The number of physical lines of the class.
     */
    public void setPhysicalLines(int physicalLines) {
        this.physicalLines = physicalLines;
    }

    /**
     * Return the average lines of codes by constructor for the class.
     *
     * @return The average lines of codes by constructor for the class.
     */
    public double getAverageLinesOfCodeMethodConstructor() {
        return codeLines / (double) (constructors.size() + methods.size());
    }

    /**
     * Return the average lines of comment by constructor for the class.
     *
     * @return The average lines of comment by constructor for the class.
     */
    public Object getAverageLinesOfCommentMethodConstructor() {
        return commentLines / (double) (constructors.size() + methods.size());
    }

    /**
     * Return the average physical lines by constructor for the class.
     *
     * @return The average physical lines by constructor for the class.
     */
    public Object getAveragePhysicalLinesMethodConstructor() {
        return physicalLines / (double) (constructors.size() + methods.size());
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", methods=" + methods +
                ", constructors=" + constructors +
                ", codeLines=" + codeLines +
                ", commentLines=" + commentLines +
                ", physicalLines=" + physicalLines +
                '}';
    }
}