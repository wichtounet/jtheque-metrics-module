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