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

import org.jtheque.metrics.services.able.IMetricsService;
import org.jtheque.metrics.services.impl.utils.count.Counter;
import org.jtheque.metrics.services.impl.utils.count.JavaCounter;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.utils.projects.ModularProject;
import org.jtheque.metrics.utils.projects.ProjectDefinition;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A metrics service implementation.
 *
 * @author Baptiste Wicht
 */
public final class MetricsService implements IMetricsService {
    @Override
    public Collection<Project> generateMetrics(ModularProject project) {
        Collection<Project> projects = new ArrayList<Project>(project.getSubProjects().size());

        for (ProjectDefinition def : project.getSubProjects()) {
            Project p = new Project(def.getName());

            Counter counter = new JavaCounter(p);

            counter.count(def.getRootFolder());

            projects.add(p);
        }

        return projects;
    }
}
