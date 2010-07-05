package org.jtheque.metrics.view.impl.actions.features;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.metrics.controllers.able.IMetricsController;
import org.jtheque.metrics.services.able.IConfigurationService;

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
 * An action to save the current configuration.
 *
 * @author Baptiste Wicht
 */
public final class SaveAction extends JThequeAction {
    @Resource
    private IMetricsController controller;

    @Resource
    private IConfigurationService configurationService;

    /**
     * Construct a new SaveAction.
     */
    public SaveAction() {
        super("metrics.features.save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog(Managers.getManager(ILanguageManager.class).getMessage("config.dialogs.name"));

        boolean end = false;

        while (!end && name != null) {
            if (configurationService.configurationExists(name)) {
                boolean validated = Managers.getManager(IViewManager.class).askUserForConfirmation(
                        Managers.getManager(ILanguageManager.class).getMessage("config.dialogs.config.exists"),
                        Managers.getManager(ILanguageManager.class).getMessage("config.dialogs.config.exists.title"));

                if (validated) {
                    save(name);
                    end = true;
                } else {
                    name = Managers.getManager(IViewManager.class).askUserForText(Managers.getManager(ILanguageManager.class).getMessage("config.dialogs.name"));
                }
            } else {
                save(name);
                end = true;
            }
        }
    }

    /**
     * Save a metrics configuration with the specified name.
     *
     * @param name The name of the metrics configuration.
     */
    private void save(String name) {
        configurationService.saveConfiguration(name, controller.getView().getModel().getProjects());
    }
}
