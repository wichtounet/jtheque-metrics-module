package org.jtheque.metrics.utils.projects;

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

import java.util.ArrayList;
import java.util.Collection;

/**
 * A modular project.
 *
 * @author Baptiste Wicht
 */
public final class ModularProject {
    private final Collection<ProjectDefinition> subProjects = new ArrayList<ProjectDefinition>(15);

    /**
     * Add a sub project.
     *
     * @param p The project to add.
     */
    public void addSubProject(ProjectDefinition p) {
        subProjects.add(p);
    }

    /**
     * Return all the sub projects.
     *
     * @return A List containing all the sub projects.
     */
    public Collection<ProjectDefinition> getSubProjects() {
        return subProjects;
    }

    @Override
    public String toString() {
        return "ModularProject{" +
                "subProjects=" + subProjects +
                '}';
    }
}