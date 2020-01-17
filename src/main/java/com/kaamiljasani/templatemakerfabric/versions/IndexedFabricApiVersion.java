package com.kaamiljasani.templatemakerfabric.versions;

import java.util.Collection;

public class IndexedFabricApiVersion extends FabricApiVersion{
    
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

}