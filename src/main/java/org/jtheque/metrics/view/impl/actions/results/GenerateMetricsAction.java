package org.jtheque.metrics.view.impl.actions.results;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.edt.SimpleTask;
import org.jtheque.core.managers.view.impl.actions.JThequeAction;
import org.jtheque.metrics.controllers.able.IResultsController;
import org.jtheque.metrics.utils.projects.ModularProject;
import org.jtheque.metrics.utils.projects.ProjectDefinition;
import org.jtheque.metrics.view.able.IMetricsView;

import javax.annotation.Resource;

import java.awt.event.ActionEvent;

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
 * An action to generate the metrics.
 *
 * @author Baptiste Wicht
 */
public final class GenerateMetricsAction extends JThequeAction {
    @Resource
    private IMetricsView metricsView;

    /**
     * Construct a new GenerateMetricsAction.
     */
    public GenerateMetricsAction() {
        super("results.view.generate");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final ModularProject project = new ModularProject();

        for (ProjectDefinition def : metricsView.getModel().getProjects()) {
            project.addSubProject(def);
        }

        Managers.getManager(IViewManager.class).execute(new SimpleTask() {
            @Override
            public void run() {
                Managers.getManager(IViewManager.class).getViews().getMainView().startWait();

                new Thread(new GenerateMetricsRunnable(project)).start();
            }
        });
    }

    /**
     * A runnable to launch the metrics generation process.
     *
     * @author Baptiste Wicht
     */
    private static final class GenerateMetricsRunnable implements Runnable {
        private final ModularProject project;

        /**
         * Create a new GenerateMetricsRunnable.
         *
         * @param project The project
         */
        GenerateMetricsRunnable(ModularProject project) {
            super();

            this.project = project;
        }

        @Override
        public void run() {
            Managers.getManager(IBeansManager.class).<IResultsController>getBean("resultsController").generateMetrics(project);

            Managers.getManager(IViewManager.class).execute(new SimpleTask() {
                @Override
                public void run() {
                    Managers.getManager(IViewManager.class).getViews().getMainView().stopWait();
                }
            });
        }
    }
}