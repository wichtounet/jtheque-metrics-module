package org.jtheque.metrics.view.impl.nodes.lines.physical;

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
 * A node to display the methods summary of a class in the "physical lines" mode.
 *
 * @author Baptiste Wicht
 */
public final class MethodsPhysicalLinesNode extends AbstractResultTreeTableNode {
    private final String name;
    private final int physicalLines;
    private final double physicalLinesConstructor;

    /**
     * Construct a new MethodsPhysicalLinesNode.
     *
     * @param name                     The name of the summary.
     * @param physicalLines            The number of lines of code of the childs.
     * @param physicalLinesConstructor The average lines of code by constructors.
     */
    public MethodsPhysicalLinesNode(String name, int physicalLines, double physicalLinesConstructor) {
        super();

        this.name = name;
        this.physicalLines = physicalLines;
        this.physicalLinesConstructor = physicalLinesConstructor;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = name;
                break;
            case Columns.TOTAL:
                res = physicalLines;
                break;
            case Columns.AVERAGE:
                res = physicalLinesConstructor;
                break;
        }

        return res;
    }
}
