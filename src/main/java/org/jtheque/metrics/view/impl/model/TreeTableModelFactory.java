package org.jtheque.metrics.view.impl.model;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.view.impl.model.builders.LinesModelBuilder;
import org.jtheque.metrics.view.impl.model.builders.LinesOfCodeModelBuilder;
import org.jtheque.metrics.view.impl.model.builders.LinesOfCommentModelBuilder;
import org.jtheque.metrics.view.impl.nodes.MetricsRootNode;

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
     *
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
