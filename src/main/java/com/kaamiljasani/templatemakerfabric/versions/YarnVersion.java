package com.kaamiljasani.templatemakerfabric.versions;

public class YarnVersion{
    public final String name;
    public final String maven;
    public final String mcVersion;
    public final int build;

    public YarnVersion(String name, String maven, String mcVersion, int build){
        this.name = name;
        this.maven = maven;
        this.mcVersion = mcVersion;
        this.build = build;
    }
}