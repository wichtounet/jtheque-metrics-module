package org.jtheque.metrics.controllers.impl;

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

import org.jtheque.metrics.controllers.able.IResultsController;
import org.jtheque.metrics.services.able.IMetricsService;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.utils.projects.ModularProject;
import org.jtheque.metrics.view.able.IResultsView;

import javax.annotation.Resource;

import java.util.Collection;

/**
 * A results controller implementation.
 *
 * @author Baptiste Wicht
 */
public final class ResultsController implements IResultsController {
    @Resource
    private IResultsView resultsView;

    @Resource
    private IMetricsService metricsService;

    @Override
    public void generateMetrics(ModularProject project) {
        Collection<Project> projects = metricsService.generateMetrics(project);

        resultsView.buildModel(projects);
    }

    @Override
    public IResultsView getView() {
        return resultsView;
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
