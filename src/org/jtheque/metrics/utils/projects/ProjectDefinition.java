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