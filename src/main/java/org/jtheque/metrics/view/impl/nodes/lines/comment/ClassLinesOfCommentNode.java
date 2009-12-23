package org.jtheque.metrics.view.impl.nodes.lines.comment;

import org.jtheque.metrics.utils.elements.Class;
import org.jtheque.metrics.view.impl.Images;
import org.jtheque.metrics.view.impl.model.Decorated;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

import javax.swing.Icon;

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
 * A node to display a class in the "lines of comment" mode.
 *
 * @author Baptiste Wicht
 */
public final class ClassLinesOfCommentNode extends AbstractResultTreeTableNode implements Decorated {
    private final Class srClass;

    /**
     * Construct a new ClassLinesOfCommentNode for a specific class.
     *
     * @param srClass The class.
     */
    public ClassLinesOfCommentNode(Class srClass) {
        super();

        this.srClass = srClass;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = srClass.getName();
                break;
            case Columns.TOTAL:
                res = srClass.getCommentLines();
                break;
            case Columns.AVERAGE:
                res = srClass.getAverageLinesOfCommentMethodConstructor();
                break;
        }

        return res;
    }

    @Override
    public Icon getIcon() {
        return Images.getClassIcon();
    }
}