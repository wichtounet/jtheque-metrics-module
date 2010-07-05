package org.jtheque.metrics.view.impl.nodes.lines.comment;

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
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

/**
 * A node to display the constructors summary of a class in the "lines of comment" mode.
 *
 * @author Baptiste Wicht
 */
public final class ConstructorsLinesOfCommentNode extends AbstractResultTreeTableNode {
    private final String name;
    private final int linesOfComment;
    private final double linesOfCommentConstructor;

    /**
     * Construct a new ConstructorsLinesOfCommentNode.
     *
     * @param name                      The name of the summary.
     * @param linesOfComment            The number of lines of comment of the childs.
     * @param linesOfCommentConstructor The average lines of comment by constructors.
     */
    public ConstructorsLinesOfCommentNode(String name, int linesOfComment, double linesOfCommentConstructor) {
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
