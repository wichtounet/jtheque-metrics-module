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

import org.jtheque.metrics.utils.elements.Method;

/**
 * A pointer on a method.
 *
 * @author Baptiste Wicht
 */
public final class MethodPointer implements Pointer {
    private final Method method;
    private final int startLine;
    private final int stopLine;

    /**
     * Construct a new MethodPointer.
     *
     * @param method    The method.
     * @param startLine The start line of the constructor.
     * @param stopLine  The stop line of the constructor.
     */
    public MethodPointer(Method method, int startLine, int stopLine) {
        super();

        this.method = method;
        this.startLine = startLine;
        this.stopLine = stopLine;
    }

    @Override
    public int getStartLine() {
        return startLine;
    }

    @Override
    public int getStopLine() {
        return stopLine;
    }

    @Override
    public void setLinesOfCode(int lines) {
        method.setCodeLines(lines);
    }

    @Override
    public void setPhysicalLines(int lines) {
        method.setPhysicalLines(lines);
    }

    @Override
    public void setCommentLines(int lines) {
        method.setCommentLines(lines);
    }
}