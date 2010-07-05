package org.jtheque.metrics.view.impl.actions.metrics;

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
