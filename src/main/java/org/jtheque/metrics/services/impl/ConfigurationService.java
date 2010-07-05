package org.jtheque.metrics.services.impl;

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

import org.jtheque.metrics.IMetricsModule;
import org.jtheque.metrics.services.able.IConfigurationService;
import org.jtheque.metrics.services.impl.utils.ConfigManager;
import org.jtheque.metrics.services.impl.utils.MetricsConfiguration;
import org.jtheque.metrics.utils.projects.ProjectDefinition;

import javax.annotation.Resource;

import java.util.Collection;

/**
 * The implementation of the configuration service.
 *
 * @author Baptiste Wicht
 */
public final class ConfigurationService implements IConfigurationService {
    @Resource
    private IMetricsModule metricsModule;

    @Override
    public ConfigManager getConfiguration() {
        return metricsModule.getConfiguration();
    }

    @Override
    public boolean configurationExists(String name) {
        for (MetricsConfiguration configuration : getConfiguration().getConfigurations()) {
            if (configuration.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void saveConfiguration(String name, Collection<ProjectDefinition> projects) {
        MetricsConfiguration configuration = null;

        for (MetricsConfiguration c : getConfiguration().getConfigurations()) {
            if (c.getName().equals(name)) {
                configuration = c;
            }
        }

        if (configuration == null) {
            configuration = new MetricsConfiguration(name);
            getConfiguration().getConfigurations().add(configuration);
        }

        configuration.setProjects(projects);
    }
}
