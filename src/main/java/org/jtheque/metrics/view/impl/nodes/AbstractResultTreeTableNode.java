package org.jtheque.metrics.view.impl.nodes;

import org.jtheque.metrics.view.impl.model.Element;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;

import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;

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
