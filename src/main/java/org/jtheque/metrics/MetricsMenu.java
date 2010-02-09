package org.jtheque.metrics;

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

import org.jtheque.core.managers.feature.AbstractMenu;
import org.jtheque.core.managers.feature.Feature;

import java.util.List;

/**
 * A simple menu for the metrics module.
 *
 * @author Baptiste Wicht
 */
public final class MetricsMenu extends AbstractMenu {
    @Override
    protected List<Feature> getFileMenuSubFeatures() {
        return features(
                createSubFeature(10, "openAction"),
                createSubFeature(12, "saveAction")
        );
    }
}