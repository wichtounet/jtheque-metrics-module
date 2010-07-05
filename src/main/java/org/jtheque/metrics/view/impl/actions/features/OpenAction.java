package org.jtheque.metrics.view.impl.actions.features;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.metrics.controllers.able.IMetricsController;
import org.jtheque.metrics.services.able.IConfigurationService;
import org.jtheque.metrics.services.impl.utils.MetricsConfiguration;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.event.ActionEvent;

/**
 * An action to open a saved configuration.
 *
 * @author Baptiste Wicht
 */
public final class OpenAction extends JThequeAction {
    @Resource
    private IMetricsController controller;

    @Resource
    private IConfigurationService configurationService;

    /**
     * Construct a new OpenAction.
     */
    public OpenAction() {
        super("metrics.features.open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MetricsConfiguration config = (MetricsConfiguration) JOptionPane.showInputDialog(
                (Component) Managers.getManager(IViewManager.class).getViews().getMainView(),
                Managers.getManager(ILanguageManager.class).getMessage("config.dialogs.configuration"),
                Managers.getManager(ILanguageManager.class).getMessage("config.dialogs.configuration.title"),
                JOptionPane.QUESTION_MESSAGE,
                null,
                configurationService.getConfiguration().getConfigurations().toArray(),
                null);

        if (config != null) {
            controller.setProjects(config.getProjects());
        }
    }
}
