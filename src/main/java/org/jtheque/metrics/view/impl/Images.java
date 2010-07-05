package org.jtheque.metrics.view.impl;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.resource.IResourceManager;

import javax.swing.Icon;

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
 * Utility class to get images for the elements.
 *
 * @author Baptiste Wicht
 */
public final class Images {
    private static final Icon PACKAGE = Managers.getManager(IResourceManager.class).getIcon("org/jtheque/metrics/images/package.png");
    private static final Icon METHOD = Managers.getManager(IResourceManager.class).getIcon("org/jtheque/metrics/images/method.png");
    private static final Icon CLASS = Managers.getManager(IResourceManager.class).getIcon("org/jtheque/metrics/images/class.png");

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
