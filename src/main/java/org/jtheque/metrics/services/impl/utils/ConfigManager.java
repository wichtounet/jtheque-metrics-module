package org.jtheque.metrics.services.impl.utils;

import org.jtheque.core.managers.state.AbstractState;
import org.jtheque.core.managers.state.NodeState;
import org.jtheque.metrics.utils.projects.ProjectDefinition;

import java.io.File;
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
 * This class manage the configuration of the application.
 *
 * @author Baptiste Wicht
 */
public final class ConfigManager extends AbstractState {
    private final Collection<MetricsConfiguration> configurations = new ArrayList<MetricsConfiguration>(10);

    @Override
    public boolean isDelegated() {
        return true;
    }

    @Override
    public Collection<NodeState> delegateSave() {
        Collection<NodeState> nodes = new ArrayList<NodeState>(configurations.size());

        for (MetricsConfiguration configuration : configurations) {
            NodeState configNode = new NodeState("config");

            configNode.setAttribute("name", configuration.getName());

            for (ProjectDefinition def : configuration.getProjects()) {
                NodeState projectNode = new NodeState("project");

                projectNode.getChildrens().add(new NodeState("name", def.getName()));
                projectNode.getChildrens().add(new NodeState("folder", def.getRootFolder().getAbsolutePath()));

                configNode.getChildrens().add(projectNode);
            }

            nodes.add(configNode);
        }

        return nodes;
    }

    @Override
    public void delegateLoad(Collection<NodeState> nodes) {
        for (NodeState node : nodes) {
            if ("config".equals(node.getName())) {
                String name = node.getAttributeValue("name");

                MetricsConfiguration config = new MetricsConfiguration(name, new ArrayList<ProjectDefinition>(10));

                for (NodeState n : node.getChildrens()) {
                    if ("project".equals(n.getName())) {
                        addProjectFromNode(config, n);
                    }
                }

                configurations.add(config);
            }
        }
    }

    /**
     * Add a project from the node state.
     *
     * @param config The config to fill.
     * @param n      The node state.
     */
    private static void addProjectFromNode(MetricsConfiguration config, NodeState n) {
        String projectName = null;
        String folder = null;

        for (NodeState n2 : n.getChildrens()) {
            if ("name".equals(n2.getName())) {
                projectName = n2.getText();
            } else if ("folder".equals(n2.getName())) {
                folder = n2.getText();
            }
        }

        config.getProjects().add(new ProjectDefinition(new File(folder), projectName));
    }

    /**
     * Return all the saved configurations.
     *
     * @return The saved configurations.
     */
    public Collection<MetricsConfiguration> getConfigurations() {
        return configurations;
    }
}