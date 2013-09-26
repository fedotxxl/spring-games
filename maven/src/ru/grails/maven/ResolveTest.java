/*
 * ResolveTest
 * Copyright (c) 2012 Cybervision. All rights reserved.
 */
package ru.grails.maven;

import org.eclipse.aether.resolution.ArtifactResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;

public class ResolveTest {

    private static final Logger log = LoggerFactory.getLogger(ResolveTest.class);

    public static void main(String[] args) {

        try {
            Collection<String> dependencies = new ArrayList<String>();

            dependencies.add("org.springframework:spring-context:3.2.2.RELEASE");
            dependencies.add("ru.grails:maven-test:1.0-SNAPSHOT");

            DependencyResolver resolver = new DependencyResolver(DependencyResolver.MAVEN_CENTRAL_URL);
            DependencyResolver.ResolveResult result = resolver.resolve(dependencies);

            for (ArtifactResult res : result.artifactResults) {
                System.out.println(res.getArtifact().getFile().toURI().toURL());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
