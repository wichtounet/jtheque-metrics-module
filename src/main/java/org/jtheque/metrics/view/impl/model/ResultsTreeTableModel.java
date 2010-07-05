package org.jtheque.metrics.view.impl.model;

import org.jtheque.core.managers.view.able.components.IModel;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;

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
