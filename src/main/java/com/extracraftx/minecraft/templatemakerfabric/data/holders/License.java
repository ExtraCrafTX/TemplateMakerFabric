package com.extracraftx.minecraft.templatemakerfabric.data.holders;

public class License{
    public final String name;
    public final String value;

    public License(String name, String value){
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }
}