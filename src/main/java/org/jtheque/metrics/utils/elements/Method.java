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
 * Represent a method.
 *
 * @author Baptiste Wicht.
 */
public final class Method {
    private final String name;

    private int codeLines;
    private int commentLines;
    private int physicalLines;

    /**
     * Construct a new Method.
     *
     * @param name The name of Method.
     */
    public Method(String name) {
        super();

        this.name = name;
    }

    /**
     * Return the number of lines of code of the method.
     *
     * @return The number of lines of code of the method.
     */
    public int getCodeLines() {
        return codeLines;
    }

    /**
     * Set the number of lines of code of the method.
     *
     * @param codeLines The number of lines of code of the method.
     */
    public void setCodeLines(int codeLines) {
        this.codeLines = codeLines;
    }

    /**
     * Return the number of comment lines of the method.
     *
     * @return The number of comment lines of the method.
     */
    public int getCommentLines() {
        return commentLines;
    }

    /**
     * Set the number of comment lines of the method.
     *
     * @param commentLines The number of comment lines of the method.
     */
    public void setCommentLines(int commentLines) {
        this.commentLines = commentLines;
    }

    /**
     * Return the number of physical lines of the method.
     *
     * @return The number of physical lines of the method.
     */
    public int getPhysicalLines() {
        return physicalLines;
    }

    /**
     * Set the number of physical lines of the constructor.
     *
     * @param physicalLines The number of physical lines of the method.
     */
    public void setPhysicalLines(int physicalLines) {
        this.physicalLines = physicalLines;
    }

    /**
     * Return the name of the method.
     *
     * @return The name of the method.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", codeLines=" + codeLines +
                ", commentLines=" + commentLines +
                ", physicalLines=" + physicalLines +
                '}';
    }
}