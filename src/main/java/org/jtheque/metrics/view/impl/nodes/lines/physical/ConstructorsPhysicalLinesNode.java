package org.jtheque.metrics.view.impl.nodes.lines.physical;

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

import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

/**
 * A node to display the constructors summary of a class in the "physical lines" mode.
 *
 * @author Baptiste Wicht
 */
public final class ConstructorsPhysicalLinesNode extends AbstractResultTreeTableNode {
    private final String name;
    private final int physicalLines;
    private final double physicalLinesConstructor;

    /**
     * Construct a new ConstructorsPhysicalLinesNode.
     *
     * @param name                     The name of the summary.
     * @param physicalLines            The number of lines of code of the childs.
     * @param physicalLinesConstructor The average lines of code by constructors.
     */
    public ConstructorsPhysicalLinesNode(String name, int physicalLines, double physicalLinesConstructor) {
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