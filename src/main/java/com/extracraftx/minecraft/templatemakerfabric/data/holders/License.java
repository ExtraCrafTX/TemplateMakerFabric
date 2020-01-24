package com.extracraftx.minecraft.templatemakerfabric.data.holders;

public class License{
    public final String name;
    public final String value;
    public final boolean requiresName;

    public License(String name, String value, boolean requiresName){
        this.name = name;
        this.value = value;
        this.requiresName = requiresName;
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