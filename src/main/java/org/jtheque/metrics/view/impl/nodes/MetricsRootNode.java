package org.jtheque.metrics.view.impl.nodes;

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
