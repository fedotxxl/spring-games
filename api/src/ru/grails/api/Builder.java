/*
 * Builder
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.grails.api;

public interface Builder {

    void runBefore(Builder... builders);
    void runAfter(Builder... builders);

    void doStuff();

}
