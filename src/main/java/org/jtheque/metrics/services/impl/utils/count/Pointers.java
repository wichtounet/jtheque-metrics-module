package org.jtheque.metrics.services.impl.utils.count;

import java.util.HashMap;
import java.util.Map;

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
 * A pointer manager.
 *
 * @author Baptiste Wicht
 */
final class Pointers {
    private final Map<Integer, Pointer> pointers = new HashMap<Integer, Pointer>(25);

    /**
     * Add a pointer.
     *
     * @param pointer The pointer to add.
     */
    public void addPointer(Pointer pointer) {
        pointers.put(pointer.getStartLine(), pointer);
    }

    /**
     * Clear all the counters.
     */
    public void clear() {
        pointers.clear();
    }

    /**
     * Return the pointer for the start line.
     *
     * @param startLine The start line.
     *
     * @return the pointer for this line or null if there is no pointer for this line.
     */
    public Pointer getPointer(int startLine) {
        return pointers.get(startLine);
    }
}
