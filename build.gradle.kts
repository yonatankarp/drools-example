plugins {
    alias(libs.plugins.springboot)
    alias(libs.plugins.springboot.dependency.management)
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.springboot.webflux.starter)

    implementation(libs.bundles.kotlin.all)
    implementation(libs.bundles.drools.all)

    testImplementation(libs.bundles.test.all)
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = libs.versions.jvmTarget.get()
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
    "spotlessKotlin" to listOf("compileKotlin", "processResources", "compileTestKotlin"),
    "test" to listOf("spotlessKotlinGradle")
)

tasksDependencies.forEach { (task, dependencies) ->
    dependencies.forEach { dependsOn ->
        tasks.findByName(task)!!.mustRunAfter(dependsOn)
    }
}
