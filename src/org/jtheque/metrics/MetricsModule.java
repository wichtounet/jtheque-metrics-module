package org.jtheque.metrics;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.beans.IBeansManager;
import org.jtheque.core.managers.error.IErrorManager;
import org.jtheque.core.managers.error.InternationalizedError;
import org.jtheque.core.managers.feature.Feature;
import org.jtheque.core.managers.feature.Feature.FeatureType;
import org.jtheque.core.managers.feature.IFeatureManager;
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
 * The metrics module.
 *
 * @author Baptiste Wicht
 */
@Module(id = "jtheque-metrics-module", i18n = "classpath:org/jtheque/metrics/ressources/i18n/metrics", version = "1.1.1-SNAPSHOT", core = "2.0.2",
        jarFile = "jtheque-metrics-module-1.1.1-SNAPSHOT.jar", updateURL = "http://jtheque.developpez.com/public/versions/MetricsModule.versions")
public final class MetricsModule implements IMetricsModule {
    private Feature openFeature;
    private Feature saveFeature;

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

        IFeatureManager feature = Managers.getManager(IFeatureManager.class);

        openFeature = feature.addSubFeature(feature.getFeature(IFeatureManager.CoreFeature.FILE), "openAction", FeatureType.ACTION, 10);
        saveFeature = feature.addSubFeature(feature.getFeature(IFeatureManager.CoreFeature.FILE), "saveAction", FeatureType.ACTION, 12);

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

        Managers.getManager(IFeatureManager.class).getFeature(IFeatureManager.CoreFeature.FILE).removeSubFeature(saveFeature);
        Managers.getManager(IFeatureManager.class).getFeature(IFeatureManager.CoreFeature.FILE).removeSubFeature(openFeature);
    }

    @Override
    public ConfigManager getConfiguration() {
        return config;
    }
}