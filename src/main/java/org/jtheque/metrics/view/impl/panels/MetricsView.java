package org.jtheque.metrics.view.impl.panels;

import org.jdesktop.swingx.JXTable;
import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.language.ILanguageManager;
import org.jtheque.core.managers.view.able.IView;
import org.jtheque.core.managers.view.impl.components.panel.FileChooserPanel;
import org.jtheque.core.utils.ui.PanelBuilder;
import org.jtheque.core.utils.ui.ValidationUtils;
import org.jtheque.metrics.view.able.IMetricsView;
import org.jtheque.metrics.view.impl.AbstractDelegatedView;
import org.jtheque.metrics.view.impl.model.MetricsModel;
import org.jtheque.metrics.view.impl.model.ProjectsTableModel;
import org.jtheque.utils.ui.GridBagUtils;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.Collection;

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
 * A metrics view implementation.
 *
 * @author Baptiste Wicht
 */
public final class MetricsView extends AbstractDelegatedView implements IMetricsView {
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
     * @param model The model of the view. 
     * @param addAction The action to add configuration. 
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
        view.build();
    }

    /**
     * A panel to display the projets. 
     * 
     * @author Baptiste Wicht
     */
    private final class MetricsPanel extends AbstractTabPanel {
        /**
         * Build the view. 
         */
        private void build() {
            PanelBuilder builder = new PanelBuilder(this);
    
            builder.addI18nLabel("metrics.view.projects",
                    builder.gbcSet(0, 0, GridBagUtils.NONE, GridBagUtils.FIRST_LINE_START));
    
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
                    builder.gbcSet(0, 1, GridBagUtils.BOTH, GridBagUtils.FIRST_LINE_START, 2, 1, 1.0, 1.0));
    
            builder.addI18nLabel("metrics.view.add", builder.gbcSet(0, 2));
    
            builder.addI18nLabel("metrics.view.name", builder.gbcSet(0, 3));
    
            fieldName = builder.add(new JTextField(FIELD_COLUMNS), builder.gbcSet(1, 3));
    
            builder.addI18nLabel("metrics.view.folder", builder.gbcSet(0, 4));
    
            fileChooser = builder.add(new FileChooserPanel(), builder.gbcSet(1, 4));
            fileChooser.setDirectoriesOnly();
    
            builder.addButtonBar(builder.gbcSet(0, 5), addAction, removeAction);
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

    @Override
    public IView getImplementationView() {
        return view;
    }
}