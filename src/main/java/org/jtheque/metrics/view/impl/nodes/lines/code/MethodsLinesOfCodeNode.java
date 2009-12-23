package org.jtheque.metrics.view.impl.nodes.lines.code;

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