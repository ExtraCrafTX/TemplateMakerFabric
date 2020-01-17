package com.kaamiljasani.templatemakerfabric.versions;

public class LoaderVersion{
    public final String name;
    public final String maven;
    public final int build;

    public LoaderVersion(String name, String maven, int build){
        this.name = name;
        this.maven = maven;
        this.build = build;
    }
}