package org.jtheque.metrics.view.impl;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.resource.IResourceManager;

import javax.swing.Icon;

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
 * Utility class to get images for the elements.
 *
 * @author Baptiste Wicht
 */
public final class Images {
    private static final Icon PACKAGE = Managers.getManager(IResourceManager.class).getIcon("org/jtheque/metrics/ressources/images/package.gif");
    private static final Icon METHOD = Managers.getManager(IResourceManager.class).getIcon("org/jtheque/metrics/ressources/images/method.gif");
    private static final Icon CLASS = Managers.getManager(IResourceManager.class).getIcon("org/jtheque/metrics/ressources/images/class.gif");

    /**
     * Construct a new Images. Utility class- private constructor.
     */
    private Images() {
        super();
    }

    /**
     * Return the icon for a package.
     *
     * @return The Icon for a package.
     */
    public static Icon getPackageIcon() {
        return PACKAGE;
    }

    /**
     * Return the icon for a method.
     *
     * @return The Icon for a method.
     */
    public static Icon getMethodIcon() {
        return METHOD;
    }

    /**
     * Return the icon for a class.
     *
     * @return The Icon for a class.
     */
    public static Icon getClassIcon() {
        return CLASS;
    }
}
