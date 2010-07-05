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
