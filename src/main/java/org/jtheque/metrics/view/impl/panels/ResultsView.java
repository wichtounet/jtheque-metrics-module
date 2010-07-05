package org.jtheque.metrics.view.impl.panels;

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

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.edt.SimpleTask;
import org.jtheque.core.managers.view.impl.components.panel.AbstractDelegatedView;
import org.jtheque.core.utils.ui.builders.JThequePanelBuilder;
import org.jtheque.core.utils.ui.builders.PanelBuilder;
import org.jtheque.metrics.utils.elements.Project;
import org.jtheque.metrics.view.able.IResultsView;
import org.jtheque.metrics.view.impl.model.ElementsCellRenderer;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel;
import org.jtheque.metrics.view.impl.model.TreeTableModelFactory;
import org.jtheque.utils.ui.GridBagUtils;

import org.jdesktop.swingx.JXTreeTable;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;

import java.util.Collection;

/**
 * A results View implementation.
 *
 * @author Baptiste Wicht
 */
public final class ResultsView extends AbstractDelegatedView<AbstractTabPanel> implements IResultsView {
    private JXTreeTable treeTable;
    private ResultsTreeTableModel model;

    private final Action generateAction;

    private ResultsPanel view;

    /**
     * Construct a new ResultsView.
     *
     * @param generateAction The action to generate the metrics.
     */
    public ResultsView(Action generateAction) {
        super();

        this.generateAction = generateAction;

        buildInEDT();
    }

    @Override
    protected void buildDelegatedView() {
        view = new ResultsPanel();
        setDelegate(view);
        view.build();
    }

    /**
     * A panel to display the results.
     *
     * @author Baptiste Wicht
     */
    private final class ResultsPanel extends AbstractTabPanel {
        /**
         * Build the view.
         */
        private void build() {
            PanelBuilder builder = new JThequePanelBuilder(this);

            builder.addButton(generateAction, builder.gbcSet(0, 0));

            treeTable = new JXTreeTable();
            treeTable.setTreeCellRenderer(new ElementsCellRenderer());
            treeTable.setAutoCreateColumnsFromModel(true);
            treeTable.setSortable(false);
            treeTable.getTableHeader().setReorderingAllowed(false);
            treeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            treeTable.setColumnControlVisible(true);
            treeTable.setRootVisible(false);
            treeTable.packAll();

            builder.addScrolled(treeTable,
                    builder.gbcSet(0, 1, GridBagUtils.BOTH, GridBagUtils.FIRST_LINE_START, 1.0, 1.0));
        }

        @Override
        protected void validate(Collection<JThequeError> errors) {
            //Nothing to validate in this view. 
        }
    }

    @Override
    public Integer getPosition() {
        return 2;
    }

    @Override
    public String getTitleKey() {
        return "results.view.title";
    }

    @Override
    public ResultsTreeTableModel getModel() {
        return model;
    }

    @Override
    public void buildModel(Collection<Project> projects) {
        model = TreeTableModelFactory.buildModel(projects);

        Managers.getManager(IViewManager.class).execute(new SimpleTask() {
            @Override
            public void run() {
                treeTable.setTreeTableModel(model);
                treeTable.packAll();

                Managers.getManager(IViewManager.class).refresh(treeTable);
            }
        });
    }

    @Override
    public JComponent getComponent() {
        return view;
    }
}
