package org.jtheque.metrics.services.impl.utils;

import org.jtheque.metrics.utils.projects.ProjectDefinition;

import java.util.ArrayList;
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