package org.jtheque.metrics.view.impl.nodes.lines.comment;

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
 * A node to display the methods summary of a class in the "lines of comment" mode.
 *
 * @author Baptiste Wicht
 */
public final class MethodsLinesOfCommentNode extends AbstractResultTreeTableNode {
    private final Object name;
    private final Object linesOfComment;
    private final Object linesOfCommentConstructor;

    /**
     * Construct a new MethodsLinesOfCommentNode.
     *
     * @param name                      The name of the summary.
     * @param linesOfComment            The number of lines of comment of the childs.
     * @param linesOfCommentConstructor The average lines of comment by constructors.
     */
    public MethodsLinesOfCommentNode(Object name, Object linesOfComment, Object linesOfCommentConstructor) {
        super();

        this.name = name;
        this.linesOfComment = linesOfComment;
        this.linesOfCommentConstructor = linesOfCommentConstructor;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = name;
                break;
            case Columns.TOTAL:
                res = linesOfComment;
                break;
            case Columns.AVERAGE:
                res = linesOfCommentConstructor;
                break;
        }

        return res;
    }
}