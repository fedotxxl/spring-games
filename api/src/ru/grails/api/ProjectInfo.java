/*
 * ProjectInfo
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.grails.api;

import java.util.Collection;

public interface ProjectInfo {

    Collection<PluginDescriptor> getPlugins();
    ProjectConfig getConfig();

}
