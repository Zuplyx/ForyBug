plugins {
    id("java")
    id("application")
}


repositories {
    mavenCentral()
}

dependencies {
    runtimeOnly(libs.derby)
    implementation(libs.bundles.fory)
    implementation(libs.schemacrawler)
}

application{
    mainClass.set("com.zuplyx.BugTest")
}

java {
    // set Java 21 as default
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

// ensure JavaExec tasks use the project's toolchain JVM instead of the build jvm
tasks.withType<JavaExec>().configureEach {
    javaLauncher.set(javaToolchains.launcherFor(java.toolchain))
}