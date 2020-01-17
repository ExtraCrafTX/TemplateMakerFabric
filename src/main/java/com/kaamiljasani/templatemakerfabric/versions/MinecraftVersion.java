package com.kaamiljasani.templatemakerfabric.versions;

public class MinecraftVersion{
    public final int index;
    public final String name;
    public final boolean stable;

    public MinecraftVersion(int index, String name, boolean stable){
        this.index = index;
        this.name = name;
        this.stable = stable;
    }
}