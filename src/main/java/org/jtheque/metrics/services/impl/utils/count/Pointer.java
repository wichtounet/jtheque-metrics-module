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
