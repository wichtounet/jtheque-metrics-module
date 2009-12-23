package org.jtheque.metrics.services.impl.utils.count;

import org.jtheque.metrics.utils.elements.Constructor;

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
 * A pointer on a constructor.
 *
 * @author Baptiste Wicht
 */
public final class ConstructorPointer implements Pointer {
    private final Constructor constructor;
    private final int startLine;
    private final int stopLine;

    /**
     * Construct a new ConstructorPointer.
     *
     * @param constructor The constructor.
     * @param startLine   The start line of the constructor.
     * @param stopLine    The stop line of the constructor.
     */
    public ConstructorPointer(Constructor constructor, int startLine, int stopLine) {
        super();

        this.constructor = constructor;
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
        constructor.setCodeLines(lines);
    }

    @Override
    public void setPhysicalLines(int lines) {
        constructor.setPhysicalLines(lines);
    }

    @Override
    public void setCommentLines(int lines) {
        constructor.setCommentLines(lines);
    }
}