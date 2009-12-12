package org.jtheque.metrics.controllers.impl;

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