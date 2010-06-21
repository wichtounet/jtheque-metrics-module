package org.jtheque.metrics.services.impl;

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