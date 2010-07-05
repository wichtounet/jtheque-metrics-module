package org.jtheque.metrics.view.impl.nodes.lines.code;

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

import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

/**
 * A node to display the methods summary of a class in the "lines of code" mode.
 *
 * @author Baptiste Wicht
 */
public final class MethodsLinesOfCodeNode extends AbstractResultTreeTableNode {
    private final Object name;
    private final Object linesOfCode;
    private final Object linesOfCodeConstructor;

    /**
     * Construct a new MethodsLinesOfCodeNode.
     *
     * @param name                   The name of the summary.
     * @param linesOfCode            The number of lines of code of the childs.
     * @param linesOfCodeConstructor The average lines of code by constructors.
     */
    public MethodsLinesOfCodeNode(Object name, Object linesOfCode, Object linesOfCodeConstructor) {
        super();

        this.name = name;
        this.linesOfCode = linesOfCode;
        this.linesOfCodeConstructor = linesOfCodeConstructor;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = name;
                break;
            case Columns.TOTAL:
                res = linesOfCode;
                break;
            case Columns.AVERAGE:
                res = linesOfCodeConstructor;
                break;
        }

        return res;
    }
}
