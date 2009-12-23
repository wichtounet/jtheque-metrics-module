package org.jtheque.metrics.view.impl.model;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.view.impl.model.builders.LinesModelBuilder;
import org.jtheque.metrics.view.impl.model.builders.LinesOfCodeModelBuilder;
import org.jtheque.metrics.view.impl.model.builders.LinesOfCommentModelBuilder;
import org.jtheque.metrics.view.impl.nodes.MetricsRootNode;

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
 * @author Baptiste Wicht
 */
public final class TreeTableModelFactory {
    private static final String[] HEADERS;

    /**
     * Construct a new TreeTableModelFactory. This class is only for utility, it cannot be instanciated.
     */
    private TreeTableModelFactory() {
        super();
    }

    static {
        HEADERS = new String[3];

        HEADERS[0] = Managers.getManager(ILanguageManager.class).getMessage("results.model.headers.name");
        HEADERS[1] = Managers.getManager(ILanguageManager.class).getMessage("results.model.headers.total");
        HEADERS[2] = Managers.getManager(ILanguageManager.class).getMessage("results.model.headers.average");
    }

    /**
     * Build the model for the list of projects.
     *
     * @param projects The projects to build the metrics for.
     * @return The build model tree table model.
     */
    public static ResultsTreeTableModel buildModel(Iterable<Project> projects) {
        MetricsRootNode rootNode = new MetricsRootNode();

        ResultsTreeTableModel model = new ResultsTreeTableModel(rootNode, HEADERS);

        LinesModelBuilder.addLinesNodes(projects, rootNode);
        LinesOfCodeModelBuilder.addLinesOfCodeNodes(projects, rootNode);
        LinesOfCommentModelBuilder.addLinesOfCommentNodes(projects, rootNode);

        return model;
    }
}