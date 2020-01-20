package com.kaamiljasani.templatemakerfabric.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaamiljasani.templatemakerfabric.data.holders.FabricApiVersion;
import com.kaamiljasani.templatemakerfabric.data.holders.IndexedFabricApiVersion;
import com.kaamiljasani.templatemakerfabric.data.holders.License;
import com.kaamiljasani.templatemakerfabric.data.holders.LoaderVersion;
import com.kaamiljasani.templatemakerfabric.data.holders.MinecraftVersion;
import com.kaamiljasani.templatemakerfabric.data.holders.YarnVersion;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DataProvider {

    public static final License[] LICENSES = {
        new License("No License (Copyrighted)", "All-Rights-Reserved"),
        new License("MIT", "MIT"),
        new License("Internet Systems Consortium (ISC) License", "ISC"),
        new License("BSD 2-Clause (FreeBSD) License", "BSD-2-Clause-FreeBSD"),
        new License("BSD 3-Clause (NewBSD) License", "BSD-3-Clause"),
        new License("Apache 2.0", "Apache-2.0"),
        new License("Mozilla Public License 2.0", "MPL-2.0"),
        new License("GNU LGPL 3.0", "LGPL-3.0"),
        new License("GNU GPL 3.0", "GPL-3.0"),
        new License("GNU AGPL 3.0", "AGPL-3.0"),
        new License("Unlicense", "unlicense")
    };

    private ArrayList<MinecraftVersion> mcVersions;
    private ArrayList<FabricApiVersion> apiVersions;
    private ArrayList<IndexedFabricApiVersion> sortedApiVersions;
    private ArrayList<YarnVersion> yarnVersions;
    private HashMap<String, ArrayList<YarnVersion>> filteredYarnVersions = new HashMap<>();
    private ArrayList<String> loomVersions;
    private ArrayList<LoaderVersion> loaderVersions;

    public ArrayList<MinecraftVersion> getMinecraftVersions() throws IOException{
        if(mcVersions != null)
            return mcVersions;
        JsonArray mcVersionsData = jsonFromUrl("https://meta.fabricmc.net/v2/versions/game").getAsJsonArray();
        ArrayList<MinecraftVersion> mcVersions = new ArrayList<>(mcVersionsData.size());
        for(int i = 0; i < mcVersionsData.size(); i++){
            JsonObject versionData = mcVersionsData.get(i).getAsJsonObject();
            String name = versionData.get("version").getAsString();
            boolean stable = versionData.get("stable").getAsBoolean();
            mcVersions.add(new MinecraftVersion(i, name, stable));
        }
        this.mcVersions = mcVersions;
        return mcVersions;
    }

    public ArrayList<FabricApiVersion> getFabricApiVersions() throws IOException{
        if(apiVersions != null)
            return apiVersions;
        JsonArray apiVersionsData = jsonFromUrl("https://addons-ecs.forgesvc.net/api/v2/addon/306612/files").getAsJsonArray();
        ArrayList<FabricApiVersion> apiVersions = new ArrayList<>();
        for(int i = 0; i < apiVersionsData.size(); i++){
            JsonObject versionData = apiVersionsData.get(i).getAsJsonObject();
            String name = versionData.get("displayName").getAsString();
            apiVersions.add(new FabricApiVersion(name));
        }
        this.apiVersions = apiVersions;
        return apiVersions;
    }

    public ArrayList<IndexedFabricApiVersion> getSortedFabricApiVersions() throws IOException{
        if(sortedApiVersions != null)
            return sortedApiVersions;
        if(mcVersions == null)
            getMinecraftVersions();
        if(apiVersions == null)
            getFabricApiVersions();
        ArrayList<IndexedFabricApiVersion> sortedApiVersions = apiVersions.stream()
                .map(apiVersion -> new IndexedFabricApiVersion(apiVersion, mcVersions))
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        this.sortedApiVersions = sortedApiVersions;
        return sortedApiVersions;
    }

    public ArrayList<YarnVersion> getYarnVersions() throws IOException{
        if(yarnVersions != null)
            return yarnVersions;
        JsonArray yarnVersionsData = jsonFromUrl("https://meta.fabricmc.net/v2/versions/yarn").getAsJsonArray();
        ArrayList<YarnVersion> yarnVersions = new ArrayList<>(yarnVersionsData.size());
        for(int i = 0; i < yarnVersionsData.size(); i++){
            JsonObject version = yarnVersionsData.get(i).getAsJsonObject();
            String mcVersion = version.get("gameVersion").getAsString();
            int build = version.get("build").getAsInt();
            String maven = version.get("maven").getAsString();
            String name = version.get("version").getAsString();
            yarnVersions.add(new YarnVersion(name, maven, mcVersion, build));
        }
        this.yarnVersions = yarnVersions;
        return yarnVersions;
    }

    public ArrayList<YarnVersion> getFilteredYarnVersions(String mcVersion) throws IOException{
        if(filteredYarnVersions.containsKey(mcVersion))
            return filteredYarnVersions.get(mcVersion);
        if(yarnVersions == null)
            getYarnVersions();
        ArrayList<YarnVersion> filtered = yarnVersions.stream()
                .filter(version->version.mcVersion.equals(mcVersion))
                .collect(Collectors.toCollection(ArrayList::new));
        filteredYarnVersions.put(mcVersion, filtered);
        return filtered;
    }

    public ArrayList<String> getLoomVersions() throws IOException, SAXException, ParserConfigurationException {
        if(loomVersions != null)
            return loomVersions;
        Document loomData = xmlFromUrl("https://maven.fabricmc.net/net/fabricmc/fabric-loom/maven-metadata.xml");
        NodeList loomVersionsData = loomData.getElementsByTagName("version");
        ArrayList<String> loomVersions = new ArrayList<>(loomVersionsData.getLength());
        for(int i = 0; i < loomVersionsData.getLength(); i++){
            loomVersions.add(loomVersionsData.item(i).getTextContent());
        }
        Collections.reverse(loomVersions);
        this.loomVersions = loomVersions;
        return loomVersions;
    }

    public ArrayList<LoaderVersion> getLoaderVersions() throws IOException{
        if(loaderVersions != null)
            return loaderVersions;
        JsonArray loaderVersionsData = jsonFromUrl("https://meta.fabricmc.net/v2/versions/loader").getAsJsonArray();
        ArrayList<LoaderVersion> loaderVersions = new ArrayList<>(loaderVersionsData.size());
        for(int i = 0; i < loaderVersionsData.size(); i++){
            JsonObject version = loaderVersionsData.get(i).getAsJsonObject();
            int build = version.get("build").getAsInt();
            String maven = version.get("maven").getAsString();
            String name = version.get("version").getAsString();
            loaderVersions.add(new LoaderVersion(name, maven, build));
        }
        this.loaderVersions = loaderVersions;
        return loaderVersions;
    }

    public License[] getSupportedLicenses(){
        return LICENSES;
    }

    public static Document xmlFromUrl(String urlString) throws IOException, SAXException, ParserConfigurationException {
        URL url = new URL(urlString);
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url.openStream());
    }

    public static JsonElement jsonFromUrl(String url) throws IOException{
        return JsonParser.parseString(getUrl(url));
    }

    public static String getUrl(String urlString) throws IOException{
        URL url = new URL(urlString);
        return readAllFromStream(url.openStream());
    }
    
    public static String readAllFromStream(InputStream stream) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            result.append(line);
            result.append("\n");
        }
        return result.toString();
    }

}