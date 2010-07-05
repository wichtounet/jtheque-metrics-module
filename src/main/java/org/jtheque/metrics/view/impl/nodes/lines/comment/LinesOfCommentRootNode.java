package org.jtheque.metrics.view.impl.nodes.lines.comment;

import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

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
 * A root node for the "lines of comment" mode.
 *
 * @author Baptiste Wicht
 */
public final class LinesOfCommentRootNode extends AbstractResultTreeTableNode {
    private final String name;
    private int total;
    private double average;

    /**
     * Construct a new LinesOfCommentRootNode.
     *
     * @param name The name of the node.
     */
    public LinesOfCommentRootNode(String name) {
        super();

        this.name = name;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = name;
                break;
            case Columns.TOTAL:
                res = total;
                break;
            case Columns.AVERAGE:
                res = average;
                break;
        }

        return res;
    }

    /**
     * Return the total lines of comment.
     *
     * @return The total lines of comment.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Set the total lines of comment.
     *
     * @param total The total lines of comment.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Set the average lines of comment by class.
     *
     * @param average The average lines of comment by class.
     */
    public void setAverage(double average) {
        this.average = average;
    }
}
