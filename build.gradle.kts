plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.2" apply false
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0-rc-1" apply true
}

ext["ossrhUsername"] = System.getenv("OSSRH_USER")
ext["ossrhPassword"] = System.getenv("OSSRH_PASS")
ext["sonatypeStagingProfileId"] = System.getenv("SONATYPE_STAGING_PROFILE_ID")
ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")

nexusPublishing {
    repositories {
        create("maven") {
            stagingProfileId.set(System.getenv("SONATYPE_STAGING_PROFILE_ID"))
            username.set(System.getenv("OSSRH_USER"))
            password.set(System.getenv("OSSRH_PASS"))
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
    clientTimeout.set(java.time.Duration.ofSeconds(30))
}

