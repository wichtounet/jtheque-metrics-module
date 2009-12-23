package org.jtheque.metrics.view.impl.nodes.lines.comment;

import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

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