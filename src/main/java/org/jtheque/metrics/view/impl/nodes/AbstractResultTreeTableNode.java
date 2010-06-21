package org.jtheque.metrics.view.impl.nodes;

import org.jtheque.metrics.view.impl.model.Element;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

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
 * An abstract node for the tree table models.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractResultTreeTableNode extends AbstractMutableTreeTableNode implements Element {
    @Override
    public final int getColumnCount() {
        return 3;
    }

    @Override
    public String getName() {
        return (String) getValueAt(Columns.NAME);
    }
}