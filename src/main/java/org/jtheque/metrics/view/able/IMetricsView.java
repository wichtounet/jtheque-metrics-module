package org.jtheque.metrics.view.able;

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

import org.jdesktop.swingx.JXTable;
import org.jtheque.core.managers.view.able.IView;
import org.jtheque.core.managers.view.able.components.TabComponent;
import org.jtheque.core.managers.view.impl.components.panel.FileChooserPanel;
import org.jtheque.metrics.view.impl.model.MetricsModel;
import org.jtheque.metrics.view.impl.model.ProjectsTableModel;

import javax.swing.JTextField;

/**
 * A metrics view specification.
 *
 * @author Baptiste Wicht
 */
public interface IMetricsView extends IView, TabComponent {
    /**
     * Return the table of projects.
     *
     * @return The table of projects.
     */
    JXTable getTable();

    /**
     * Return the field for name.
     *
     * @return The field for the name.
     */
    JTextField getFieldName();

    /**
     * Return the file chooser.
     *
     * @return The file chooser.
     */
    FileChooserPanel getFileChooser();

    /**
     * Return the projects table model.
     *
     * @return The projects table model.
     */
    ProjectsTableModel getTableModel();

    @Override
    MetricsModel getModel();
}