package org.jtheque.metrics.view.impl.nodes.lines.code;

import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

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
 * A root node for the "lines of code" mode.
 *
 * @author Baptiste Wicht
 */
public final class LinesOfCodeRootNode extends AbstractResultTreeTableNode {
    private final String name;
    private int total;
    private double average;

    /**
     * Construct a new LinesOfCodeRootNode.
     *
     * @param name The name of the node.
     */
    public LinesOfCodeRootNode(String name) {
        super();

        this.name = name;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = name;
                break;
            case Columns.TOTAL:
                res = total;
                break;
            case Columns.AVERAGE:
                res = average;
                break;
        }

        return res;
    }

    /**
     * Return the total lines of code.
     *
     * @return The total lines of code.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Set the total lines of code.
     *
     * @param total The total lines of code.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Set the average lines of code by class.
     *
     * @param average The average lines of code by class.
     */
    public void setAverage(double average) {
        this.average = average;
    }
}
