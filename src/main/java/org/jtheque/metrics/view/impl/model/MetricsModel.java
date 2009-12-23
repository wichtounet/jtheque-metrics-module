package org.jtheque.metrics.view.impl.model;

import org.jtheque.core.managers.view.able.components.IModel;
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
 * A metrics model.
 *
 * @author Baptiste Wicht
 */
public final class MetricsModel implements IModel {
    private final Collection<ProjectDefinition> projects;

    /**
     * Construct a new MetricsModel.
     */
    public MetricsModel() {
        super();

        projects = new ArrayList<ProjectDefinition>(10);
    }

    /**
     * Return the projects of the model.
     *
     * @return A List containing all the projects of the model.
     */
    public Collection<ProjectDefinition> getProjects() {
        return projects;
    }
}