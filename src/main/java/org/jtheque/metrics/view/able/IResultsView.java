package org.jtheque.metrics.view.able;

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

import org.jtheque.core.managers.view.able.IView;
import org.jtheque.core.managers.view.able.components.TabComponent;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel;

import java.util.Collection;

/**
 * A results view specification.
 *
 * @author Baptiste Wicht
 */
public interface IResultsView extends IView, TabComponent {
    @Override
    ResultsTreeTableModel getModel();

    /**
     * Build the model with the projects.
     *
     * @param projects The projects to build the model for.
     */
    void buildModel(Collection<Project> projects);
}
