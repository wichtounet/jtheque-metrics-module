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
            if (configurationService.configurationExists(name)){
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
