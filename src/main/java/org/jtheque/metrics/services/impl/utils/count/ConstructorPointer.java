package org.jtheque.metrics.services.impl.utils.count;

import org.jtheque.metrics.utils.elements.Constructor;

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
