package org.jtheque.metrics.view.impl.model;

import org.jtheque.metrics.utils.projects.ProjectDefinition;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
 * A project table model.
 *
 * @author Baptiste Wicht
 */
public final class ProjectsTableModel extends AbstractTableModel {
    /**
     * The different columns of the projects table.
     *
     * @author Baptiste Wicht
     */
    private interface Columns {
        int NAME = 0;
        int FOLDER = 1;
    }

    /**
     * Headers of the table.
     */
    private String[] headers;

    private final List<ProjectDefinition> projects;

    /**
     * Construct a new <code>FilmsToBuyTableModel</code>.
     */
    public ProjectsTableModel() {
        super();

        projects = new ArrayList<ProjectDefinition>(10);
    }

    /**
     * Set the header of the table.
     *
     * @param headers The header of the table model
     */
    public void setHeader(String[] headers) {
        this.headers = headers.clone();

        fireTableStructureChanged();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public int getRowCount() {
        return projects.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;

        ProjectDefinition project = projects.get(rowIndex);

        if (project != null) {
            switch (columnIndex) {
                case Columns.NAME:
                    value = project.getName();
                    break;
                case Columns.FOLDER:
                    value = project.getRootFolder().getAbsolutePath();
                    break;
                default:
                    value = "";
                    break;
            }
        }

        return value;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Set the projects of the table model.
     *
     * @param projects The projects to display on the table.
     */
    public void setProjects(Collection<ProjectDefinition> projects) {
        this.projects.clear();
        this.projects.addAll(projects);

        fireTableDataChanged();
    }

    /**
     * Add a project to the model. 0
     *
     * @param project The project to add to the model.
     */
    public void addProject(ProjectDefinition project) {
        projects.add(project);

        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    /**
     * Remove a project from the model.
     *
     * @param project The project to remove.
     */
    public void removeProject(ProjectDefinition project) {
        int index = projects.indexOf(project);

        projects.remove(project);

        fireTableRowsDeleted(index, index);
    }

    /**
     * Return the project with the specified index.
     *
     * @param index The index of the project.
     *
     * @return The project with the specific index else null.
     */
    public ProjectDefinition getProject(int index) {
        return projects.get(index);
    }
}
