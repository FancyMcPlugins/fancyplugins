plugins {
    id("java-library")
    id("maven-publish")

    id("xyz.jpenilla.run-paper")
    id("com.gradleup.shadow")
    id("net.minecrell.plugin-yml.paper")
}

runPaper.folia.registerTask()

repositories {
    mavenLocal()
    mavenCentral()
    maven(url = "https://repo.papermc.io/repository/maven-public/")
    maven(url = "https://repo.fancyplugins.de/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    implementation(project(":libraries:packets:api"))
    implementation(project(":libraries:packets:factories"))
    implementation(project(":libraries:packets:implementations:1_20_6"))
    implementation(project(":libraries:packets:implementations:1_21_3"))
    implementation("de.oliver.FancyAnalytics:logger:0.0.6")
}

paper {
    name = "FancySitulaTestPlugin"
    main = "de.oliver.fancysitula.FancySitulaPlugin"
    bootstrapper = "de.oliver.fancysitula.loaders.FancySitulaPluginBootstrapper"
    loader = "de.oliver.fancysitula.loaders.FancySitulaPluginLoader"
    foliaSupported = true
    version = "1.0.0"
    description = "Test plugin for FancySitula"
    apiVersion = "1.19"
}

tasks {
    runServer {
        minecraftVersion("1.21.4")
    }

    shadowJar {
        archiveClassifier.set("")
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 21
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}