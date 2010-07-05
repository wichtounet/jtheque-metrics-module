package org.jtheque.metrics.view.impl.nodes.lines.comment;

import org.jtheque.metrics.utils.elements.Project;
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
 * A node to display a project in the "lines of comment" mode.
 *
 * @author Baptiste Wicht
 */
public final class ProjectLinesOfCommentNode extends AbstractResultTreeTableNode {
    private final Project project;

    /**
     * Construct a new ProjectLinesOfCommentNode for a specific project.
     *
     * @param project The project.
     */
    public ProjectLinesOfCommentNode(Project project) {
        super();

        this.project = project;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = project.getName();
                break;
            case Columns.TOTAL:
                res = project.getRootPackage().getTotalNumberLinesOfComment();
                break;
            case Columns.AVERAGE:
                res = project.getAverageLinesOfCommentClass();
                break;
        }

        return res;
    }


}
