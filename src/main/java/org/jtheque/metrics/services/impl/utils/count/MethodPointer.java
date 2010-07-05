package org.jtheque.metrics.services.impl.utils.count;

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
