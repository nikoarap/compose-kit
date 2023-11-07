plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.dokka") version "1.4.20"
    id("maven-publish")
    id("signing")
}

//publication properties
val groupIdConst = "io.github.nikoarap"
val artifactIdConst = "compose-kit"
val versionConst = "1.2.5"
val descriptionConst = "An easy-to-use, essential toolkit for Jetpack Compose, built to help you create beautiful, consistent user interfaces following Material3 guidelines and styles"

//dokka html to attach the javadoc jar to the publication
val dokkaHtml by tasks.getting(org.jetbrains.dokka.gradle.DokkaTask::class)

android {
    namespace = "com.nikoarap.compose_kit"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/java")
        getByName("main").res.srcDirs("src/main/res")
    }
}

dependencies {
    //generic android libs
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //compose libs
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.foundation:foundation:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation("androidx.compose.animation:animation:1.5.4")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("androidx.compose.runtime:runtime-rxjava2:1.5.4")
    implementation("com.google.accompanist:accompanist-flowlayout:0.26.0-alpha")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.11-rc")
    implementation("io.coil-kt:coil-compose:2.4.0")

    //test libs
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

//source code and javadoc jars
val sourcesJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(android.sourceSets["main"].java.srcDirs)
}
val javadocJar: TaskProvider<Jar> by tasks.registering(Jar::class) {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.outputDirectory)
}

publishing {
    publications {
        create<MavenPublication>("Release") {
            groupId = groupIdConst
            artifactId = artifactIdConst
            version = versionConst
            description = descriptionConst

            //aar, sources.jar and javadoc.jar artifacts to publish
            artifacts {
                artifact("$buildDir/outputs/aar/${artifactId}-release.aar")
                artifact(sourcesJar)
                artifact(javadocJar)
            }

            pom {
                name.set(artifactIdConst)
                groupId = groupIdConst
                artifactId = artifactIdConst
                version = versionConst
                description.set(descriptionConst)
                url.set("https://github.com/nikoarap/compose-kit")

                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.html")
                    }
                }
                developers {
                    developer {
                        id.set("nikoarap")
                        name.set("Nikolaos Arapatsanis")
                        email.set("nikoarap@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/nikoarap/compose-kit")
                }
                dependencies {
                    implementation("androidx.core:core-ktx:1.12.0")
                    implementation("androidx.compose.ui:ui:1.5.4")
                    implementation("androidx.compose.ui:ui-graphics")
                    implementation("androidx.compose.ui:ui-tooling:1.5.4")
                    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
                    implementation("androidx.compose.foundation:foundation:1.5.4")
                    implementation("androidx.compose.material3:material3:1.1.2")
                    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
                    implementation("androidx.compose.animation:animation:1.5.4")
                    implementation("com.google.accompanist:accompanist-flowlayout:0.26.0-alpha")
                    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.11-rc")
                    implementation("io.coil-kt:coil-compose:2.4.0")
                }
            }
        }
    }
}

ext["signing.keyId"] = rootProject.ext["signing.keyId"]
ext["signing.password"] = rootProject.ext["signing.password"]
ext["signing.secretKeyRingFile"] = rootProject.ext["signing.secretKeyRingFile"]


signing {
    sign(publishing.publications["Release"])
}