plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("checkstyle")
}

checkstyle {
    toolVersion = "10.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val javaFxVersion = "17.0.7"
val osName = System.getProperty("os.name").toLowerCase()
val arch = System.getProperty("os.arch")

val javaFxPlatform = when {
    osName.contains("mac") && arch.contains("aarch64") -> "mac-aarch64"
    osName.contains("mac") -> "mac"
    osName.contains("win") -> "win"
    osName.contains("linux") -> "linux"
    else -> throw GradleException("Unsupported OS: $osName")
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    // This dependency is used by the application.
    implementation(libs.guava)

    // JavaFX dependencies for multiple platforms
    implementation("org.openjfx:javafx-base:$javaFxVersion:$javaFxPlatform")
    implementation("org.openjfx:javafx-controls:$javaFxVersion:$javaFxPlatform")
    implementation("org.openjfx:javafx-fxml:$javaFxVersion:$javaFxPlatform")
    implementation("org.openjfx:javafx-graphics:$javaFxVersion:$javaFxPlatform")

}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

application {
    //mainClass.set("alexis.ui.Alexis")  // for terminal alexis
    mainClass.set("alexis.fx.Launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

sourceSets {
    main {
        java {
            // Use configure() to set srcDirs as a mutable set
            setSrcDirs(files("src"))  // Use the `files()` method to wrap paths as File objects
        }
    }
    test {
        java {
            srcDir("test/java")  // Add this to include the custom test folder
        }
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL  // Use the correct enum
        showCauses = true
        showStackTraces = true
        showStandardStreams = false
    }
}

tasks.shadowJar {
    archiveFileName.set("alexis.jar")
    destinationDirectory.set(file("$buildDir/libs"))
}

tasks.withType<JavaExec> {
    enableAssertions = true
}



