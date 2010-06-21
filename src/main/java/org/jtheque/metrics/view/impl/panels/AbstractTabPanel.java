package org.jtheque.metrics.view.impl.panels;

import org.jtheque.core.managers.Managers;
import org.jtheque.core.managers.error.JThequeError;
import org.jtheque.core.managers.view.able.IView;
import org.jtheque.core.managers.view.able.IViewManager;
import org.jtheque.core.managers.view.able.components.IModel;

import javax.swing.JPanel;

import java.util.ArrayList;
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
 * An abstract tab panel.
 *
 * @author Baptiste Wicht
 */
public abstract class AbstractTabPanel extends JPanel implements IView {
    @Override
    public final void display() {
        //Nothing to do here
    }

    @Override
    public final void closeDown() {
        //Nothing to do here
    }

    @Override
    public final void toFirstPlan() {
        //Nothing to do here
    }

    @Override
    public final void sendMessage(String message, Object value) {
        //Nothing to do here
    }

    @Override
    public final void refresh() {
        Managers.getManager(IViewManager.class).refresh(this);
    }

    @Override
    public final boolean validateContent() {
        Collection<JThequeError> errors = new ArrayList<JThequeError>(6);

        validate(errors);

        for (JThequeError error : errors) {
            Managers.getManager(IViewManager.class).displayError(error);
        }

        return errors.isEmpty();
    }

    @Override
    public final Object getImpl() {
        return this;
    }

    @Override
    public IModel getModel() {
        return null;
    }

    /**
     * Validate the view and save all the validation's errors in the list.
     *
     * @param errors The error's list.
     */
    protected abstract void validate(Collection<JThequeError> errors);
}