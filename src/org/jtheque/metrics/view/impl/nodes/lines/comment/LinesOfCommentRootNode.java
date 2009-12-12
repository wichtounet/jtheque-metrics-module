package org.jtheque.metrics.view.impl.nodes.lines.comment;

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
 * A root node for the "lines of comment" mode.
 *
 * @author Baptiste Wicht
 */
public final class LinesOfCommentRootNode extends AbstractResultTreeTableNode {
    private final String name;
    private int total;
    private double average;

    /**
     * Construct a new LinesOfCommentRootNode.
     *
     * @param name The name of the node.
     */
    public LinesOfCommentRootNode(String name) {
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
     * Return the total lines of comment.
     *
     * @return The total lines of comment.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Set the total lines of comment.
     *
     * @param total The total lines of comment.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Set the average lines of comment by class.
     *
     * @param average The average lines of comment by class.
     */
    public void setAverage(double average) {
        this.average = average;
    }
}
