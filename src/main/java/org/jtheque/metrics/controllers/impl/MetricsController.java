package org.jtheque.metrics.controllers.impl;

import org.jtheque.metrics.controllers.able.IMetricsController;
import org.jtheque.metrics.utils.projects.ProjectDefinition;
import org.jtheque.metrics.view.able.IMetricsView;

import javax.annotation.Resource;

import java.io.File;
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
