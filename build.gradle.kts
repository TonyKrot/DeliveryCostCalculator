plugins {
    id("java")
    id("io.qameta.allure") version "2.10.0"
}

group = "org.DeliveryCostCalculator"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("io.qameta.allure:allure-testng:2.14.0")
    implementation ("org.seleniumhq.selenium:selenium-java:4.18.1")
    testImplementation ("org.assertj:assertj-core:3.25.3")
    }

tasks.test {
    useJUnitPlatform()
    useJUnitPlatform()
}
tasks.test {
    useJUnitPlatform()
}
