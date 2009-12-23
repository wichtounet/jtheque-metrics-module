package org.jtheque.metrics.services.impl.utils.count;

import java.util.HashMap;
import java.util.Map;

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
     * @return the pointer for this line or null if there is no pointer for this line.
     */
    public Pointer getPointer(int startLine) {
        return pointers.get(startLine);
    }
}