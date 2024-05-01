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
    testImplementation("io.github.bonigarcia:selenium-jupiter:5.1.0")
    implementation ("org.apache.httpcomponents.client5:httpclient5:5.3.1")
    testImplementation ("io.rest-assured:rest-assured:5.4.0")
    }

tasks.test {
    useJUnitPlatform()
    useJUnitPlatform()
}
tasks.test {
    useJUnitPlatform()
}
