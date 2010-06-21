package org.jtheque.metrics.view.impl.actions.metrics;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.metrics.controllers.able.IMetricsController;
import org.jtheque.metrics.utils.projects.ProjectDefinition;
import org.jtheque.metrics.view.impl.model.ProjectsTableModel;

import org.jdesktop.swingx.JXTable;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

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
 * An action to remove the selected project.
 *
 * @author Baptiste Wicht
 */
public final class RemoveProjectAction extends JThequeAction {
    @Resource
    private IMetricsController controller;

    /**
     * Construct a new RemoveProjectAction.
     */
    public RemoveProjectAction() {
        super("metrics.view.actions.delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JXTable table = controller.getView().getTable();
        ProjectsTableModel model = controller.getView().getTableModel();

        if (table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, Managers.getManager(ILanguageManager.class).getMessage("metrics.dialogs.selectProject"));
        } else if (table.getSelectedRowCount() == 1) {
            ProjectDefinition project = model.getProject(table.convertRowIndexToModel(table.getSelectedRow()));

            controller.removeProject(project);
        } else {
            int[] indexes = table.getSelectedRows();

            for (int index : indexes) {
                ProjectDefinition project = model.getProject(table.convertRowIndexToModel(index));

                controller.removeProject(project);
            }
        }
    }
}