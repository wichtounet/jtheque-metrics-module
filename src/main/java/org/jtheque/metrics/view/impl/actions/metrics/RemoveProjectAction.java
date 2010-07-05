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
