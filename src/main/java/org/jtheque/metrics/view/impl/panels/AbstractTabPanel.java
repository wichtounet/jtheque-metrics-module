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
