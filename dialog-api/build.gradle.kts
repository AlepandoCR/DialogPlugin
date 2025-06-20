plugins {
    kotlin("jvm") version "2.1.21"
    `java-library`
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
    id("xyz.jpenilla.run-paper") version "2.3.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
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
    compileOnly(files(rootProject.file("nms/paper-1.21.6.jar")))
    paperweight.paperDevBundle("1.21.6-R0.1-SNAPSHOT")
    implementation(platform("com.intellectualsites.bom:bom-newest:1.52"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("io.mockk:mockk:1.12.3")
}

java {
    withSourcesJar()
}

tasks.withType<Test> {
    useJUnitPlatform()
}
