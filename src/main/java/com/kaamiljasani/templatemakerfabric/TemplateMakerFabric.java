package com.kaamiljasani.templatemakerfabric;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kaamiljasani.templatemakerfabric.versions.FabricApiVersion;
import com.kaamiljasani.templatemakerfabric.versions.IndexedFabricApiVersion;
import com.kaamiljasani.templatemakerfabric.versions.MinecraftVersion;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TemplateMakerFabric {

    private ArrayList<MinecraftVersion> mcVersions;
    private ArrayList<FabricApiVersion> apiVersions;
    private ArrayList<IndexedFabricApiVersion> sortedApiVersions;

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
        ArrayList<IndexedFabricApiVersion> sortedApiVersions = new ArrayList<>(apiVersions.size());
        apiVersions.stream()
                .map(apiVersion -> new IndexedFabricApiVersion(apiVersion, mcVersions))
                .sorted()
                .forEachOrdered(version -> sortedApiVersions.add(version));
        this.sortedApiVersions = sortedApiVersions;
        return sortedApiVersions;
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