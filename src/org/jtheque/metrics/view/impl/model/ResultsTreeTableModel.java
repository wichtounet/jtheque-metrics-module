package org.jtheque.metrics.view.impl.model;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.jtheque.core.managers.view.able.components.IModel;

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
 * A results tree table model.
 *
 * @author Baptiste Wicht
 */
public final class ResultsTreeTableModel extends DefaultTreeTableModel implements IModel {
    /**
     * The columns of the tree table.
     *
     * @author Baptiste Wicht
     */
    public interface Columns {
        int NAME = 0;
        int TOTAL = 1;
        int AVERAGE = 2;
    }

    private final String[] headers;

    /**
     * Construct a new ResultsTreeTableModel.
     *
     * @param node    The root node.
     * @param headers The headers.
     */
    public ResultsTreeTableModel(TreeTableNode node, String[] headers) {
        super(node);

        this.headers = headers.clone();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(Object node, int column) {
        Object res = "n/a";

        if (node instanceof TreeTableNode) {
            TreeTableNode defNode = (TreeTableNode) node;

            res = defNode.getValueAt(column);
        }

        return res;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public boolean isCellEditable(Object node, int column) {
        return false;
    }
}