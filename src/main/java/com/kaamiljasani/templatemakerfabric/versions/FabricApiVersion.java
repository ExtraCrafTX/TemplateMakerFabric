package com.kaamiljasani.templatemakerfabric.versions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FabricApiVersion{
    private static Pattern mcVersionPattern = Pattern.compile("\\[(([^/\\]]+)(?:/[^\\]]+)?)\\]");
    private static Pattern buildPattern = Pattern.compile("build (\\d+)");

    public final String name;
    public final String displayMcVersion;
    public final String mcVersion;
    public final int build;

    public FabricApiVersion(String name){
        this.name = name;
        Matcher mcMatcher = mcVersionPattern.matcher(name);
        if(mcMatcher.find()){
            this.displayMcVersion = mcMatcher.group(1);
            this.mcVersion = mcMatcher.group(2);
        }else{
            throw new IllegalArgumentException("The name '" + name + "' doesn't seem to have the Minecraft version");
        }
        Matcher buildMatcher = buildPattern.matcher(name);
        if(buildMatcher.find()){
            this.build = Integer.parseInt(buildMatcher.group(1));
        }else{
            throw new IllegalArgumentException("The name '" + name + "' doesn't seem to have the build number");
        }
    }

    public FabricApiVersion(FabricApiVersion apiVersion){
        name = apiVersion.name;
        displayMcVersion = apiVersion.displayMcVersion;
        mcVersion = apiVersion.mcVersion;
        build = apiVersion.build;
    }
}