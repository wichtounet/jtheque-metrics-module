package org.jtheque.metrics.view.impl.nodes.lines.comment;

import org.jtheque.metrics.utils.elements.Method;
import org.jtheque.metrics.view.impl.Images;
import org.jtheque.metrics.view.impl.model.Decorated;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

import javax.swing.Icon;

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
 * A node to display a method in the "lines of comment" mode.
 *
 * @author Baptiste Wicht
 */
public final class MethodLinesOfCommentNode extends AbstractResultTreeTableNode implements Decorated {
    private final Method method;

    /**
     * Construct a new MethodLinesOfCommentNode for a specific method.
     *
     * @param method The method.
     */
    public MethodLinesOfCommentNode(Method method) {
        super();

        this.method = method;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = method.getName();
                break;
            case Columns.TOTAL:
                res = method.getCommentLines();
                break;
            case Columns.AVERAGE:
                res = method.getCommentLines();
                break;
        }

        return res;
    }

    @Override
    public Icon getIcon() {
        return Images.getMethodIcon();
    }
}
