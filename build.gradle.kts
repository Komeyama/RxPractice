//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
//
//plugins {
//    java
//    kotlin("jvm") version "1.3.41"
//}
//
//group = "com.komeyama.rxpractice"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    implementation(kotlin("stdlib-jdk8"))
//
//    // rx
//    implementation("io.reactivex.rxjava3:rxjava:3.1.1")
//
//    testCompile("junit", "junit", "4.12")
//}
//
//configure<JavaPluginConvention> {
//    sourceCompatibility = JavaVersion.VERSION_1_8
//}
//tasks.withType<KotlinCompile> {
//    kotlinOptions.jvmTarget = "1.8"
//}

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    application
}

group = "me.yoneyama"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("io.reactivex.rxjava3:rxjava:3.1.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}