package org.jtheque.metrics.view.impl.actions.metrics;

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

import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.metrics.controllers.able.IMetricsController;
import org.jtheque.metrics.view.able.IMetricsView;

import javax.annotation.Resource;

import java.awt.event.ActionEvent;

/**
 * An action to add a project to the list.
 *
 * @author Baptiste Wicht
 */
public final class AddProjectAction extends JThequeAction {
    @Resource
    private IMetricsController metricsController;

    /**
     * Construct a new AddProjectAction.
     */
    public AddProjectAction() {
        super("metrics.view.actions.add");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IMetricsView view = metricsController.getView();

        if (view.validateContent()) {
            metricsController.addProject(view.getFieldName().getText(), view.getFileChooser().getFilePath());

            view.getFieldName().setText("");
            view.getFileChooser().setFilePath("");
        }
    }
}