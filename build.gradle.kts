plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.diffplug.spotless") version "6.22.0"
    val kotlinVersion = "1.9.20"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    val coroutinesVersion = "1.7.3"
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutinesVersion")


    val droolsVersion = "9.44.0.Final"
    implementation("org.drools:drools-core:$droolsVersion")
    implementation("org.drools:drools-compiler:$droolsVersion")
    implementation("org.drools:drools-decisiontables:$droolsVersion")
    implementation("org.drools:drools-mvel:$droolsVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
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

spotless {
    kotlin {
        target(
            fileTree(projectDir) {
                include("**/*.kt")
                exclude("**/.gradle/**")
            }
        )
        // see https://github.com/shyiko/ktlint#standard-rules
        ktlint()
    }

    kotlinGradle {
        target(
            fileTree(projectDir) {
                include("**/*.kt")
                exclude("**/.gradle/**")
            }
        )
        // see https://github.com/shyiko/ktlint#standard-rules
        ktlint()
    }
}

val tasksDependencies = mapOf(
    "spotlessKotlinGradle" to listOf("spotlessKotlin"),
    "spotlessKotlin" to listOf("compileKotlin", "processResources", "compileTestKotlin")
)

tasksDependencies.forEach { (task, dependencies) ->
    dependencies.forEach { dependsOn ->
        tasks.findByName(task)!!.mustRunAfter(dependsOn)
    }
}
