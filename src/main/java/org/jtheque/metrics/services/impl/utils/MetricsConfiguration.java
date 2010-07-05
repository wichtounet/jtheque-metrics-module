package org.jtheque.metrics.services.impl.utils;

import org.jtheque.metrics.utils.projects.ProjectDefinition;

import java.util.ArrayList;
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
 * A Metrics configuration.
 *
 * @author Baptiste Wicht
 */
public final class MetricsConfiguration {
    private final String name;
    private Collection<ProjectDefinition> projects;

    /**
     * Construct a MetricsConfiguration.
     *
     * @param name The name of the configuration.
     */
    public MetricsConfiguration(String name) {
        this.name = name;
    }

    /**
     * Construct a MetricsConfiguration.
     *
     * @param name     The name of the configuration.
     * @param projects The projects of the configuration.
     */
    public MetricsConfiguration(String name, Collection<ProjectDefinition> projects) {
        this.name = name;
        this.projects = new ArrayList<ProjectDefinition>(projects);
    }

    /**
     * Return the name of the configuration.
     *
     * @return The name of configuration.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the projects of the configuration.
     *
     * @return A List containing all the projects of the configuration.
     */
    public Collection<ProjectDefinition> getProjects() {
        return projects;
    }

    /**
     * Set the projects of the configuration.
     *
     * @param projects The projects of this configuration.
     */
    public void setProjects(Collection<ProjectDefinition> projects) {
        this.projects = new ArrayList<ProjectDefinition>(projects);
    }

    @Override
    public String toString() {
        return name;
    }
}
