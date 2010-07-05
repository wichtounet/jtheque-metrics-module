package org.jtheque.metrics.utils.projects;

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
