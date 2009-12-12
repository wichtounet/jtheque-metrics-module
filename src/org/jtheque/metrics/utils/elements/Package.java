package org.jtheque.metrics.utils.elements;

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

import java.util.ArrayList;
import java.util.Collection;

/**
 * A package.
 *
 * @author Baptiste Wicht
 */
public final class Package {
    private final Collection<Class> classes = new ArrayList<Class>(25);
    private final Collection<Package> packages = new ArrayList<Package>(15);
    private Package parent;
    private final String name;

    /**
     * Construct a new Package.
     *
     * @param packageName The name of the package.
     */
    public Package(String packageName) {
        super();

        name = packageName;
        parent = this;
    }

    /**
     * Add a package.
     *
     * @param p The package.
     */
    public void addPackage(Package p) {
        packages.add(p);
        p.parent = this;
    }

    /**
     * Add class to the package.
     *
     * @param c The class to add.
     */
    public void addClass(Class c) {
        classes.add(c);
    }

    /**
     * Return all the classes of the package.
     *
     * @return A List containing all the classes of the package.
     */
    public Iterable<Class> getClasses() {
        return classes;
    }

    /**
     * Return all the sub-packages of the package.
     *
     * @return A List containing all the packages of this.
     */
    public Iterable<Package> getPackages() {
        return packages;
    }

    /**
     * Return the name of the package.
     *
     * @return The name of the packages.
     */
    public String getName() {
        return name;
    }

    /**
     * Return the parent package.
     *
     * @return The parent package.
     */
    public Package getParent() {
        return parent;
    }

    /**
     * Return the number of classes.
     *
     * @return The number of classes.
     */
    int getNumberOfClasses() {
        return classes.size();
    }

    /**
     * Return the total number of classes.
     *
     * @return The total number of classes.
     */
    public int getTotalNumberOfClasses() {
        int number = getNumberOfClasses();

        for (Package p : packages) {
            number += p.getTotalNumberOfClasses();
        }

        return number;
    }

    /**
     * Return the total number of lines of code.
     *
     * @return The total number of lines of code.
     */
    public int getTotalNumberLinesOfCode() {
        int lines = 0;

        for (Class c : classes) {
            lines += c.getCodeLines();
        }

        for (Package p : packages) {
            lines += p.getTotalNumberLinesOfCode();
        }

        return lines;
    }

    /**
     * Return the total number of lines of comment.
     *
     * @return The total number of lines of comment.
     */
    public int getTotalNumberLinesOfComment() {
        int lines = 0;

        for (Class c : classes) {
            lines += c.getCommentLines();
        }

        for (Package p : packages) {
            lines += p.getTotalNumberLinesOfComment();
        }

        return lines;
    }


    /**
     * Return the total number of lines.
     *
     * @return The total number of lines.
     */
    public int getTotalNumberLines() {
        int lines = 0;

        for (Class c : classes) {
            lines += c.getPhysicalLines();
        }

        for (Package p : packages) {
            lines += p.getTotalNumberLines();
        }

        return lines;
    }

    /**
     * Return the average lines of code by class.
     *
     * @return The average lines of code by class.
     */
    public double getAverageLinesOfCodeClass() {
        return getTotalNumberLinesOfCode() / (double) getTotalNumberOfClasses();
    }

    /**
     * Return the average lines of comment by class.
     *
     * @return The average lines of comment by class.
     */
    public double getAverageLinesOfCommentClass() {
        return getTotalNumberLinesOfComment() / (double) getTotalNumberOfClasses();
    }

    /**
     * Return the average lines class.
     *
     * @return The average lines class.
     */
    public double getAverageLinesClass() {
        return getTotalNumberLines() / (double) getTotalNumberOfClasses();
    }

    /**
     * Indicate if the package is empty or not.
     *
     * @return true if the package is empty else false.
     */
    public boolean isEmpty() {
        if (packages.isEmpty()) {
            return classes.isEmpty();
        }

        if (!classes.isEmpty()) {
            return false;
        }

        for (Package p : packages) {
            if (!p.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Package{" +
                "classes=" + classes +
                ", packages=" + packages +
                ", parent=" + (parent == null ? "" : parent.name) +
                ", name='" + name + '\'' +
                '}';
    }
}