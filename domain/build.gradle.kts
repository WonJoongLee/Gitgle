plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(Dep.Libs.hilt)
    implementation(Dep.AndroidX.hiltWork)
    implementation(project(mapOf("path" to ":data")))
    kapt(Dep.Libs.hiltCompiler)
    testImplementation(Dep.Test.jUnit)
}