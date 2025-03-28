plugins {
    id("java-library")
    id("io.papermc.paperweight.userdev")
}

val minecraftVersion = "1.20.1"

dependencies {
    paperweight.paperDevBundle("$minecraftVersion-R0.1-SNAPSHOT")

    compileOnly(project(":plugins:fancynpcs:api"))
    compileOnly("de.oliver:FancyLib:37")
    compileOnly("org.lushplugins:ChatColorHandler:5.1.3")
}


tasks {
    named("assemble") {
        dependsOn(named("reobfJar"))
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release = 17

    }
}