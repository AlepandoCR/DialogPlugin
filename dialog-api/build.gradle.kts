plugins {
    kotlin("jvm") version "2.1.21"
    `java-library`
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
    id("xyz.jpenilla.run-paper") version "2.3.1"

    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
    mavenCentral()
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.opencollab.dev/main/") {
        name = "opencollab-snapshot"
    }
}

dependencies {
    compileOnly(files("nms/paper-1.21.6.jar"))
    implementation(kotlin("stdlib-jdk8"))
    paperweight.paperDevBundle("1.21.6-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib"))

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    // MockK
    testImplementation("io.mockk:mockk:1.12.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
