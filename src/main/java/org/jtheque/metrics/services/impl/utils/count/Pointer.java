package org.jtheque.metrics.services.impl.utils.count;

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
 * A line pointer. It seems an object who refers to a certain portion of code and needs to be counted.
 *
 * @author Baptiste Wicht
 */
public interface Pointer {
    /**
     * Return the start line of the pointer.
     *
     * @return The start line of the pointer.
     */
    int getStartLine();

    /**
     * Return the stop line of the pointer.
     *
     * @return The stop line of the pointer.
     */
    int getStopLine();

    /**
     * Set the final number of lines of code for this pointer.
     *
     * @param lines The number of lines of code.
     */
    void setLinesOfCode(int lines);

    /**
     * Set the final number of physical lines for this pointer.
     *
     * @param lines The number of physical lines.
     */
    void setPhysicalLines(int lines);

    /**
     * Set the final number of comment lines for this pointer.
     *
     * @param lines The number of comment lines.
     */
    void setCommentLines(int lines);
}