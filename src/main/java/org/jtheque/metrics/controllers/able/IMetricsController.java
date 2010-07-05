package org.jtheque.metrics.controllers.able;

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
