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

import java.io.File;

/**
 * A project definition.
 *
 * @author Baptiste Wicht
 */
public final class ProjectDefinition {
    private final File rootFolder;
    private final String name;

    /**
     * Construct a new ProjectDefinition.
     *
     * @param rootFolder The root folder of the project.
     * @param name       The name of the project.
     */
    public ProjectDefinition(File rootFolder, String name) {
        super();

        this.name = name;
        this.rootFolder = rootFolder;
    }

    /**
     * Return the root folder of the project.
     *
     * @return The root folder of the project.
     */
    public File getRootFolder() {
        return rootFolder;
    }

    /**
     * Return the name of the project.
     *
     * @return The name of the project.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ProjectDefinition{" +
                "rootFolder=" + rootFolder +
                ", name='" + name + '\'' +
                '}';
    }
}
