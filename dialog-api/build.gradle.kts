plugins {
    kotlin("jvm") version "2.1.21"
    `java-library`
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"

}

group = "alepando.dev"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.opencollab.dev/main/") {
        name = "opencollab-snapshot"
    }
}

dependencies {
    compileOnly("io.netty:netty-all:4.1.114.Final")
    paperweight.paperDevBundle("1.21.6-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.21.6-R0.1-SNAPSHOT")
    compileOnly(files(rootProject.file("nms/paper-1.21.6.jar")))

    implementation(kotlin("stdlib-jdk8"))
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

java {
    withSourcesJar()
}
