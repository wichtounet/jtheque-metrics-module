package org.jtheque.metrics.controllers.impl;

import org.jtheque.metrics.controllers.able.IMetricsController;
import org.jtheque.metrics.utils.projects.ProjectDefinition;
import org.jtheque.metrics.view.able.IMetricsView;

import javax.annotation.Resource;
import java.io.File;
import java.util.Collection;

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

/**
 * A metrics controller implementation.
 *
 * @author Baptiste Wicht
 */
public final class MetricsController implements IMetricsController {
    @Resource
    private IMetricsView metricsView;

    @Override
    public void addProject(String name, String folder) {
        ProjectDefinition def = new ProjectDefinition(new File(folder), name);

        metricsView.getTableModel().addProject(def);
        metricsView.getModel().getProjects().add(def);
    }

    @Override
    public void removeProject(ProjectDefinition project) {
        metricsView.getTableModel().removeProject(project);
        metricsView.getModel().getProjects().remove(project);
    }

    @Override
    public IMetricsView getView() {
        return metricsView;
    }

    @Override
    public void setProjects(Collection<ProjectDefinition> projects) {
        metricsView.getTableModel().setProjects(projects);
        metricsView.getModel().getProjects().clear();
        metricsView.getModel().getProjects().addAll(projects);
    }

    @Override
    public void displayView() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void closeView() {
        throw new UnsupportedOperationException();
    }
}
