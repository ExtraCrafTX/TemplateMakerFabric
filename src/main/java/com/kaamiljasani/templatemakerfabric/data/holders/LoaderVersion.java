package com.kaamiljasani.templatemakerfabric.data.holders;

public class LoaderVersion{
    public final String name;
    public final String maven;
    public final int build;

    public LoaderVersion(String name, String maven, int build){
        this.name = name;
        this.maven = maven;
        this.build = build;
    }

    @Override
    public String toString() {
        return name;
    }
}