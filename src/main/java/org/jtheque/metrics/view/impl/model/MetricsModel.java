package org.jtheque.metrics.view.impl.model;

import org.jtheque.core.managers.view.able.components.IModel;
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
