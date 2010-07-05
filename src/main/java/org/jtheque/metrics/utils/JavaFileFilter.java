package org.jtheque.metrics.utils;

import java.io.File;
import java.io.FileFilter;

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
