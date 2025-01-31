plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // This dependency is used by the application.
    implementation(libs.guava)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

application {
    mainClass.set("AlexisUi.Alexis")  // Use .set() for Kotlin DSL
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
}
