package org.jtheque.metrics;

import org.jtheque.metrics.services.impl.utils.ConfigManager;

/**
 * @author Baptiste Wicht
 */
public interface IMetricsModule {
    /**
     * Return the configuration of the module.
     *
     * @return The configuration of the module.
     */
    ConfigManager getConfiguration();
}
