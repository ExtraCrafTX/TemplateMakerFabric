package com.kaamiljasani.templatemakerfabric.versions;

import java.util.Collection;

public class IndexedFabricApiVersion extends FabricApiVersion implements Comparable<IndexedFabricApiVersion>{
    
    public final int mcVersionIndex;

    public IndexedFabricApiVersion(String name, Collection<MinecraftVersion> mcVersions){
        super(name);
        mcVersionIndex = getMcVersionIndex(mcVersions);
    }

    public IndexedFabricApiVersion(FabricApiVersion apiVersion, Collection<MinecraftVersion> mcVersions){
        super(apiVersion);
        mcVersionIndex = getMcVersionIndex(mcVersions);
    }

    private int getMcVersionIndex(Collection<MinecraftVersion> mcVersions){
        for(MinecraftVersion version : mcVersions){
            if(version.name.equals(mcVersion)){
                return version.index;
            }
        }
        return mcVersions.size();
    }

    @Override
    public int compareTo(IndexedFabricApiVersion version) {
        if(mcVersionIndex == version.mcVersionIndex){
            return version.build - build;
        }
        return mcVersionIndex - version.mcVersionIndex;
    }

}