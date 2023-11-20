plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    val droolsVersion = "9.44.0.Final"
    implementation("org.drools:drools-core:$droolsVersion")
    implementation("org.drools:drools-compiler:$droolsVersion")
    implementation("org.drools:drools-decisiontables:$droolsVersion")
    implementation("org.drools:drools-mvel:$droolsVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    test {
        useJUnitPlatform()
    }
}
