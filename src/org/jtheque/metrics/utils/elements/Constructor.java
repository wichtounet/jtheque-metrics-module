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
 * Represent a constructor.
 *
 * @author Baptiste Wicht.
 */
public final class Constructor {
    private final String name;
    private int codeLines;
    private int commentLines;
    private int physicalLines;

    /**
     * Construct a new Constructor.
     *
     * @param name The name of Constructor.
     */
    public Constructor(String name) {
        super();

        this.name = name;
    }

    /**
     * Return the number of lines of code of the constructor.
     *
     * @return The number of lines of code of the constructor.
     */
    public int getCodeLines() {
        return codeLines;
    }

    /**
     * Set the number of lines of code of the constructor.
     *
     * @param codeLines The number of lines of code of the constructor.
     */
    public void setCodeLines(int codeLines) {
        this.codeLines = codeLines;
    }

    /**
     * Return the number of comment lines of the constructor.
     *
     * @return The number of comment lines of the constructor.
     */
    public int getCommentLines() {
        return commentLines;
    }

    /**
     * Set the number of comment lines of the constructor.
     *
     * @param commentLines The number of comment lines of the constructor.
     */
    public void setCommentLines(int commentLines) {
        this.commentLines = commentLines;
    }

    /**
     * Return the number of physical lines of the constructor.
     *
     * @return The number of physical lines of the constructor.
     */
    public int getPhysicalLines() {
        return physicalLines;
    }

    /**
     * Set the number of physical lines of the constructor.
     *
     * @param physicalLines The number of physical lines of the constructor.
     */
    public void setPhysicalLines(int physicalLines) {
        this.physicalLines = physicalLines;
    }

    /**
     * Return the name of the constructor.
     *
     * @return The name of the constructor.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "name='" + name + '\'' +
                ", codeLines=" + codeLines +
                ", commentLines=" + commentLines +
                ", physicalLines=" + physicalLines +
                '}';
    }
}