package org.jtheque.metrics.services.able;

import org.jtheque.metrics.services.impl.utils.ConfigManager;
import org.jtheque.metrics.utils.projects.ProjectDefinition;

import java.util.Collection;

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
 * A service for the configurations functions.
 *
 * @author Baptiste Wicht
 */
public interface IConfigurationService {
    /**
     * Return the current configuration of the application.
     *
     * @return The current configuration.
     */
    ConfigManager getConfiguration();

    /**
     * Indicate if a metrics configuration already exists or not.
     *
     * @param name The name of the configuration.
     *
     * @return true if the configuration already exists else false.
     */
    boolean configurationExists(String name);

    /**
     * Save a configuration. If the configuration already exists, the configuration will be edited else a new metrics
     * configuration will be created.
     *
     * @param name     The name of the metrics configuration.
     * @param projects The projects of the configuration.
     */
    void saveConfiguration(String name, Collection<ProjectDefinition> projects);
}