plugins {
    id("java-library")
    id("io.papermc.paperweight.userdev")
}


val minecraftVersion = "1.19.4"


dependencies {
    paperweight.paperDevBundle("$minecraftVersion-R0.1-SNAPSHOT")

    implementation(project(":plugins:fancyholograms:fh-api"))
    implementation(project(":libraries:common"))
    compileOnly("com.viaversion:viaversion-api:5.2.1")
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

        options.release.set(17)
    }
}