package org.jtheque.metrics.view.impl.panels;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.impl.components.panel.AbstractDelegatedView;
import org.jtheque.core.managers.view.impl.components.panel.FileChooserPanel;
import org.jtheque.core.utils.ui.ValidationUtils;
import org.jtheque.core.utils.ui.builders.I18nPanelBuilder;
import org.jtheque.core.utils.ui.builders.JThequePanelBuilder;
import org.jtheque.metrics.view.able.IMetricsView;
import org.jtheque.metrics.view.impl.model.MetricsModel;
import org.jtheque.metrics.view.impl.model.ProjectsTableModel;
import org.jtheque.utils.ui.GridBagUtils;

import org.jdesktop.swingx.JXTable;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.util.Collection;

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
 * A metrics view implementation.
 *
 * @author Baptiste Wicht
 */
public final class MetricsView extends AbstractDelegatedView<AbstractTabPanel> implements IMetricsView {
    private JXTable table;
    private JTextField fieldName;
    private FileChooserPanel fileChooser;

    private ProjectsTableModel tableModel;

    private final MetricsModel model;
    private final Action addAction;
    private final Action removeAction;

    private static final int FIELD_COLUMNS = 20;

    private MetricsPanel view;

    /**
     * Construct a new MetricsView.
     *
     * @param model        The model of the view.
     * @param addAction    The action to add configuration.
     * @param removeAction The action to remove a configuration.
     */
    public MetricsView(MetricsModel model, Action addAction, Action removeAction) {
        super();

        this.model = model;
        this.addAction = addAction;
        this.removeAction = removeAction;

        buildInEDT();
    }

    @Override
    protected void buildDelegatedView() {
        view = new MetricsPanel();
        setDelegate(view);
        view.build();
    }

    /**
     * A panel to display the projects.
     *
     * @author Baptiste Wicht
     */
    private final class MetricsPanel extends AbstractTabPanel {
        /**
         * Build the view.
         */
        private void build() {
            I18nPanelBuilder builder = new JThequePanelBuilder(this);

            builder.addI18nLabel("metrics.view.projects",
                    builder.gbcSet(0, 0, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 0, 1, 1.0, 0.0));

            tableModel = new ProjectsTableModel();
            tableModel.setHeader(new String[]{
                    Managers.getManager(ILanguageManager.class).getMessage("metrics.view.table.name"),
                    Managers.getManager(ILanguageManager.class).getMessage("metrics.view.table.folder")});

            table = new JXTable(tableModel);
            table.setSortable(true);
            table.getTableHeader().setReorderingAllowed(false);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            table.setColumnControlVisible(true);
            table.getActionMap().put("delete", removeAction);
            table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
            table.packAll();

            builder.addScrolled(table,
                    builder.gbcSet(0, 1, GridBagUtils.BOTH, GridBagUtils.BASELINE_LEADING, 0, 1, 1.0, 1.0));

            builder.addI18nLabel("metrics.view.add", builder.gbcSet(0, 2, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 0, 1, 1.0, 0.0));

            builder.addI18nLabel("metrics.view.name", builder.gbcSet(0, 3, GridBagUtils.NONE, GridBagUtils.BASELINE_LEADING));

            fieldName = builder.add(new JTextField(FIELD_COLUMNS), builder.gbcSet(1, 3, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 0, 1, 1.0, 0.0));

            builder.addI18nLabel("metrics.view.folder", builder.gbcSet(0, 4, GridBagUtils.NONE, GridBagUtils.BASELINE_LEADING));

            fileChooser = builder.add(new FileChooserPanel(), builder.gbcSet(1, 4, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 0, 1, 1.0, 0.0));
            fileChooser.setDirectoriesOnly();

            builder.addButtonBar(builder.gbcSet(0, 5, GridBagUtils.HORIZONTAL, GridBagUtils.BASELINE_LEADING, 0, 1, 1.0, 0.0),
                    addAction, removeAction);
        }

        @Override
        protected void validate(Collection<JThequeError> errors) {
            ValidationUtils.rejectIfEmpty(fieldName.getText(), "metrics.view.name", errors);
            ValidationUtils.rejectIfEmpty(fileChooser.getFilePath(), "metrics.view.folder", errors);
        }
    }

    @Override
    public MetricsModel getModel() {
        return model;
    }

    @Override
    public JXTable getTable() {
        return table;
    }

    @Override
    public ProjectsTableModel getTableModel() {
        return tableModel;
    }

    @Override
    public JTextField getFieldName() {
        return fieldName;
    }

    @Override
    public FileChooserPanel getFileChooser() {
        return fileChooser;
    }

    @Override
    public Integer getPosition() {
        return 1;
    }

    @Override
    public String getTitleKey() {
        return "metrics.view.title";
    }

    @Override
    public JComponent getComponent() {
        return view;
    }
}
