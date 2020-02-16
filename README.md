# TemplateMakerFabric [![Maven][maven-badge]][maven-url]

This is a library provides data for:
* Supported Minecraft versions
* Fabric API versions
* Yarn mappings versions
* Loom versions
* Fabric Loader versions

It also allows filtering some of these by compatibility and can provide default versions based on selections

It also allows building a skeleton of a mod by providing information which can then be output to a given path.

### Maven
```gradle
repositories{
    maven { url "https://maven.extracraftx.com" }
}
...
dependencies{
    implementation "com.extracraftx.minecraft:TemplateMakerFabric:{VERSION}"
}
```

##### Documentation Coming Soon™

### GeneratorFabricMod
This library is not intended to be user-facing.  
*If you are wanting to simply create a skeleton mod as a user, take a look at [GeneratorFabricMod](https://github.com/ExtraCrafTX/GeneratorFabricMod).*

[maven-badge]: https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fmaven.extracraftx.com%2Fcom%2Fextracraftx%2Fminecraft%2FTemplateMakerFabric%2Fmaven-metadata.xml
[maven-url]: https://maven.extracraftx.com/com/extracraftx/minecraft/TemplateMakerFabric/