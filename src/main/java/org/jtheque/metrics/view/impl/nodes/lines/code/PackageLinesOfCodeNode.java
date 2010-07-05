package org.jtheque.metrics.view.impl.nodes.lines.code;

import org.jtheque.metrics.utils.elements.Package;
import org.jtheque.metrics.view.impl.Images;
import org.jtheque.metrics.view.impl.model.Decorated;
import org.jtheque.metrics.view.impl.model.ResultsTreeTableModel.Columns;
import org.jtheque.metrics.view.impl.nodes.AbstractResultTreeTableNode;

import javax.swing.Icon;

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
 * A node to display a package in the "lines of code" mode.
 *
 * @author Baptiste Wicht
 */
public final class PackageLinesOfCodeNode extends AbstractResultTreeTableNode implements Decorated {
    private final Package srcPackage;

    /**
     * Construct a new ClassLinesOfCodeNode for a specific package.
     *
     * @param srcPackage The package.
     */
    public PackageLinesOfCodeNode(Package srcPackage) {
        super();

        this.srcPackage = srcPackage;
    }

    @Override
    public Object getValueAt(int column) {
        Object res = null;

        switch (column) {
            case Columns.NAME:
                res = srcPackage.getName();
                break;
            case Columns.TOTAL:
                res = srcPackage.getTotalNumberLinesOfCode();
                break;
            case Columns.AVERAGE:
                res = srcPackage.getAverageLinesOfCodeClass();
                break;
        }

        return res;
    }

    @Override
    public Icon getIcon() {
        return Images.getPackageIcon();
    }
}
