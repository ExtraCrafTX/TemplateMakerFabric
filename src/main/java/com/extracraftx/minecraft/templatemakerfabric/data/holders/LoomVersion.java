package com.extracraftx.minecraft.templatemakerfabric.data.holders;

public class LoomVersion{
    public final String name;
    public final int index;

    public LoomVersion(String name, int index){
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return name;
    }
}