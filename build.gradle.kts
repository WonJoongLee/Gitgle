// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dep.androidGradlePlugin)
        classpath(Dep.Kotlin.gradle)
        classpath(Dep.Libs.hiltGradlePlugin)
        classpath(Dep.AndroidX.navigationSafeArgs)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

subprojects {
    afterEvaluate {
        project.apply("$rootDir/gradle/common.gradle")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}