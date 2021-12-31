plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Dep.Libs.hilt)
    implementation(Dep.AndroidX.hiltWork)
    implementation(Dep.Kotlin.coroutine)
    implementation(Dep.Kotlin.coroutineCore)
    implementation(project(mapOf("path" to ":data")))
    implementation(project(":shared"))
    kapt(Dep.Libs.hiltCompiler)
    testImplementation(Dep.Test.jUnit)
}