package org.jtheque.metrics.view.impl.nodes;

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

/**
 * The root node of the tree table.
 *
 * @author Baptiste Wicht
 */
public final class MetricsRootNode extends AbstractResultTreeTableNode {
    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = "";
                break;
            case Columns.TOTAL:
                res = "";
                break;
            case Columns.AVERAGE:
                res = "";
                break;
        }

        return res;
    }
}