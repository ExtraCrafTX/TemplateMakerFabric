package com.kaamiljasani.templatemakerfabric.fabric;

import java.net.URL;

import com.kaamiljasani.templatemakerfabric.data.holders.FabricApiVersion;
import com.kaamiljasani.templatemakerfabric.data.holders.LoaderVersion;
import com.kaamiljasani.templatemakerfabric.data.holders.MinecraftVersion;
import com.kaamiljasani.templatemakerfabric.data.holders.YarnVersion;

import static com.kaamiljasani.templatemakerfabric.util.Util.*;

public class FabricMod {
    private MinecraftVersion mcVersion;
    private String modName;
    private String modId;
    private String modDescription;
    private String modVersion;
    private String author;
    private URL homepage;
    private URL sources;
    private String license;
    private String nameOnLicense;
    private String[] mainPackage;
    private String mainClass;
    private boolean mixin;
    private boolean fabricApi;
    private FabricApiVersion apiVersion;
    private YarnVersion yarnVersion;
    private String loomVersion;
    private LoaderVersion loaderVersion;
    private String mavenGroup;
    private String archiveName;

    public FabricMod(MinecraftVersion mcVersion, String modName, String modId, String modDescription, String modVersion,
            String author, URL homepage, URL sources, String license, String nameOnLicense, String mainPackage,
            String mainClass, boolean mixin, boolean fabricApi, FabricApiVersion apiVersion, YarnVersion yarnVersion,
            String loomVersion, LoaderVersion loaderVersion, String mavenGroup, String archiveName) {
        this.mcVersion = ensureExists(mcVersion, "Minecraft version");
        this.modName = ensureNotEmpty(modName, "Mod name");
        this.modId = ensureNotEmpty(modId, "Mod id");
        this.modDescription = ensureNotEmpty(modDescription, "Mod description");
        this.modVersion = ensureSemVer(modVersion, "Mod version");
        this.author = ensureNotEmpty(author, "Author");
        this.homepage = homepage;
        this.sources = sources;
        this.license = ensureNotEmpty(license, "License");
        this.nameOnLicense = nameOnLicense;
        this.mainPackage = ensureValidPackage(mainPackage, "Main package");
        this.mainClass = ensureValidClass(mainClass, "Main class");
        this.mixin = mixin;
        this.fabricApi = fabricApi;
        if(fabricApi)
            ensureExists(apiVersion, "Api version");
        this.apiVersion = apiVersion;
        this.yarnVersion = ensureExists(yarnVersion, "Yarn mappings");
        this.loomVersion = ensureNotEmpty(loomVersion, "Loom version");
        this.loaderVersion = ensureExists(loaderVersion, "Loader version");
        this.mavenGroup = String.join(".", ensureValidPackage(mavenGroup, "Maven group"));
        this.archiveName = ensureNotEmpty(archiveName, "Archive base name");
    }

    /**
     * @return the mcVersion
     */
    public MinecraftVersion getMcVersion() {
        return mcVersion;
    }

    /**
     * @return the modName
     */
    public String getModName() {
        return modName;
    }

    /**
     * @return the modId
     */
    public String getModId() {
        return modId;
    }

    /**
     * @return the modDescription
     */
    public String getModDescription() {
        return modDescription;
    }

    /**
     * @return the modVersion
     */
    public String getModVersion() {
        return modVersion;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the homepage
     */
    public URL getHomepage() {
        return homepage;
    }

    /**
     * @return the sources
     */
    public URL getSources() {
        return sources;
    }

    /**
     * @return the license
     */
    public String getLicense() {
        return license;
    }

    /**
     * @return the nameOnLicense
     */
    public String getNameOnLicense() {
        return nameOnLicense;
    }

    /**
     * @return the mainPackage
     */
    public String[] getMainPackage() {
        return mainPackage;
    }

    /**
     * @return the mainClass
     */
    public String getMainClass() {
        return mainClass;
    }

    /**
     * @return the mixin
     */
    public boolean isMixin() {
        return mixin;
    }

    /**
     * @return the fabricApi
     */
    public boolean isFabricApi() {
        return fabricApi;
    }

    /**
     * @return the apiVersion
     */
    public FabricApiVersion getApiVersion() {
        return apiVersion;
    }

    /**
     * @return the yarnVersion
     */
    public YarnVersion getYarnVersion() {
        return yarnVersion;
    }

    /**
     * @return the loomVersion
     */
    public String getLoomVersion() {
        return loomVersion;
    }

    /**
     * @return the loaderVersion
     */
    public LoaderVersion getLoaderVersion() {
        return loaderVersion;
    }

    /**
     * @return the mavenGroup
     */
    public String getMavenGroup() {
        return mavenGroup;
    }

    /**
     * @return the archiveName
     */
    public String getArchiveName() {
        return archiveName;
    }

}