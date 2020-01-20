package com.kaamiljasani.templatemakerfabric.data.holders;

public class MinecraftVersion{
    public final int index;
    public final String name;
    public final boolean stable;

    public MinecraftVersion(int index, String name, boolean stable){
        this.index = index;
        this.name = name;
        this.stable = stable;
    }

    @Override
    public String toString() {
        return name;
    }
}