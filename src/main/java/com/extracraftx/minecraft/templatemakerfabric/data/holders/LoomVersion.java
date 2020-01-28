package com.extracraftx.minecraft.templatemakerfabric.data.holders;

public class LoomVersion{
    public final String name;
    public final int index;
    public final int gradle;

    public LoomVersion(String name, int index){
        this.name = name;
        this.index = index;
        this.gradle = index >= 3 ? 4 : 5;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the gradle major version
     */
    public int getGradle() {
        return gradle;
    }
}