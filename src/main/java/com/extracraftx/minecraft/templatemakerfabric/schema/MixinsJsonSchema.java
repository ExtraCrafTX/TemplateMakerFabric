package com.extracraftx.minecraft.templatemakerfabric.schema;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MixinsJsonSchema {
    public boolean required = true;
    @SerializedName("package")
    public String pkg;
    public String compatibilityLevel = "JAVA_8";
    public List<String> mixins = new ArrayList<>();
    public List<String> client = new ArrayList<>();
    public List<String> server = new ArrayList<>();
    public Injectors injectors = new Injectors();
}