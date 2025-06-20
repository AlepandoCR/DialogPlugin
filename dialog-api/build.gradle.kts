plugins {
    kotlin("jvm") version "1.8.20" // Or match the version from the main project
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") // Added Paper repository
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("io.papermc.paper:paper-api:1.21.6-R0.1-SNAPSHOT") // Added Paper API

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    // MockK
    testImplementation("io.mockk:mockk:1.12.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
