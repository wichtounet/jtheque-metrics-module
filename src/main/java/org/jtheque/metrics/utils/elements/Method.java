package org.jtheque.metrics.utils.elements;

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
