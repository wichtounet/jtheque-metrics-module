package org.jtheque.metrics;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.error.IErrorManager;
import org.jtheque.core.managers.error.InternationalizedError;
import org.jtheque.core.managers.feature.IFeatureManager;
import org.jtheque.core.managers.feature.Menu;
import org.jtheque.core.managers.log.ILoggingManager;
import org.jtheque.core.managers.module.annotations.Module;
import org.jtheque.core.managers.module.annotations.Plug;
import org.jtheque.core.managers.module.annotations.PrePlug;
import org.jtheque.core.managers.module.annotations.UnPlug;
import org.jtheque.core.managers.persistence.IPersistenceManager;
import org.jtheque.core.managers.state.IStateManager;
import org.jtheque.core.managers.state.StateException;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.able.components.TabComponent;
import org.jtheque.metrics.services.impl.utils.ConfigManager;

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
 * The metrics module.
 *
 * @author Baptiste Wicht
 */
@Module(id = "jtheque-metrics-module", i18n = "classpath:org/jtheque/metrics/i18n/metrics", version = "1.1.2", core = "2.0.3.1",
        jarFile = "jtheque-metrics-module-1.1.2.jar", updateURL = "http://jtheque.developpez.com/public/versions/MetricsModule.versions")
public final class MetricsModule implements IMetricsModule {
    private final Menu metricsMenu = new MetricsMenu();

    /**
     * The configuration of the module.
     */
    private ConfigManager config;

    /**
     * Pre plug the module.
     */
    @PrePlug
    public void prePlug() {
        Managers.getManager(IPersistenceManager.class).setEnabled(false);
    }

    /**
     * Plug the module.
     */
    @Plug
    public void plug() {
        config = Managers.getManager(IStateManager.class).getState(ConfigManager.class);

        if (config == null) {
            try {
                config = Managers.getManager(IStateManager.class).createState(ConfigManager.class);
            } catch (StateException e) {
                Managers.getManager(ILoggingManager.class).getLogger(getClass()).error(e);
                config = new ConfigManager();
                Managers.getManager(IErrorManager.class).addError(new InternationalizedError("error.loading.configuration"));
            }
        }

        Managers.getManager(IFeatureManager.class).addMenu(metricsMenu);

        Managers.getManager(IViewManager.class).addTabComponent(Managers.getManager(IBeansManager.class).<TabComponent>getBean("metricsView"));
        Managers.getManager(IViewManager.class).addTabComponent(Managers.getManager(IBeansManager.class).<TabComponent>getBean("resultsView"));
    }

    /**
     * Unplug the module.
     */
    @UnPlug
    public void unplug() {
        Managers.getManager(IViewManager.class).removeTabComponent(Managers.getManager(IBeansManager.class).<TabComponent>getBean("metricsView"));
        Managers.getManager(IViewManager.class).removeTabComponent(Managers.getManager(IBeansManager.class).<TabComponent>getBean("resultsView"));

        Managers.getManager(IFeatureManager.class).removeMenu(metricsMenu);
    }

    @Override
    public ConfigManager getConfiguration() {
        return config;
    }
}
