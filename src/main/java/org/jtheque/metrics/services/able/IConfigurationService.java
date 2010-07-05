package org.jtheque.metrics.services.able;

import org.jtheque.metrics.services.impl.utils.ConfigManager;
import org.jtheque.metrics.utils.projects.ProjectDefinition;

import java.util.Collection;

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
