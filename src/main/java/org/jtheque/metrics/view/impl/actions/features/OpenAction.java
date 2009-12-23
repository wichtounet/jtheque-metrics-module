package org.jtheque.metrics.view.impl.actions.features;

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