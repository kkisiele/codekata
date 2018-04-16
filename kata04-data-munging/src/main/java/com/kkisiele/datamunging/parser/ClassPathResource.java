package com.kkisiele.datamunging.parser;

import java.io.InputStream;

public class ClassPathResource implements Resource {
    private final String resource;
    private final ClassLoader classLoader;

    public ClassPathResource(String resource, ClassLoader classLoader) {
        this.resource = resource;
        this.classLoader = (classLoader != null) ? classLoader : getClass().getClassLoader();
    }

    public ClassPathResource(String resource) {
        this(resource, null);
    }

    @Override
    public InputStream getInputStream() {
        return classLoader.getResourceAsStream(resource);
    }

    @Override
    public String toString() {
        return "ClassPathResource [" + resource + "]";
    }
}