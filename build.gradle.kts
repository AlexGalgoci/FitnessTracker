plugins {
    kotlin("jvm") version "1.8.10" apply false
    id("com.android.application") version "8.4.2" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}
