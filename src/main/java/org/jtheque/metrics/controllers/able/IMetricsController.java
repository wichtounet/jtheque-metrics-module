package org.jtheque.metrics.controllers.able;

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

import org.jtheque.core.managers.view.able.controller.Controller;
import org.jtheque.metrics.utils.projects.ProjectDefinition;
import org.jtheque.metrics.view.able.IMetricsView;

import java.util.Collection;

/**
 * A metrics controller specification.
 *
 * @author Baptiste Wicht
 */
public interface IMetricsController extends Controller {
    /**
     * Add a project.
     *
     * @param name   The name of the project.
     * @param folder The folder of the project.
     */
    void addProject(String name, String folder);

    /**
     * Remove the project.
     *
     * @param project The project to remove.
     */
    void removeProject(ProjectDefinition project);

    @Override
    IMetricsView getView();

    /**
     * Set the projects. Clear the actual projects.
     *
     * @param projects The projects.
     */
    void setProjects(Collection<ProjectDefinition> projects);
}
