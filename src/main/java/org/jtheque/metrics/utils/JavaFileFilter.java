package org.jtheque.metrics.utils;

import java.io.File;
import java.io.FileFilter;

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
 * A filter for java file.
 *
 * @author Baptiste Wicht
 */
public final class JavaFileFilter implements FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return !file.getName().startsWith(".");
        }

        return file.getName().endsWith("java");
    }
}