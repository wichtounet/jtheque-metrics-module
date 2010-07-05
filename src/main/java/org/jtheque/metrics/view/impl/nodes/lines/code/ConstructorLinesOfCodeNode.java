package org.jtheque.metrics.view.impl.nodes.lines.code;

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

import org.jtheque.metrics.utils.elements.Constructor;
import org.jtheque.metrics.view.impl.Images;
import org.jtheque.metrics.view.impl.model.Decorated;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

import javax.swing.Icon;

/**
 * A node to display a constructor in the "lines of code" mode.
 *
 * @author Baptiste Wicht
 */
public final class ConstructorLinesOfCodeNode extends AbstractResultTreeTableNode implements Decorated {
    private final Constructor constructor;

    /**
     * Construct a new ConstructorLinesOfCodeNode for a specific constructor.
     *
     * @param constructor The constructor.
     */
    public ConstructorLinesOfCodeNode(Constructor constructor) {
        super();

        this.constructor = constructor;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = constructor.getName();
                break;
            case Columns.TOTAL:
                res = constructor.getCodeLines();
                break;
            case Columns.AVERAGE:
                res = constructor.getCodeLines();
                break;
        }

        return res;
    }

    @Override
    public Icon getIcon() {
        return Images.getMethodIcon();
    }
}
