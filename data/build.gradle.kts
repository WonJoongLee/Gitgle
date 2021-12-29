plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    Dep.network.forEach(::implementation)
    implementation(Dep.Libs.hilt)
    implementation(Dep.AndroidX.hiltWork)
    kapt(Dep.Libs.hiltCompiler)
    testImplementation(Dep.Test.jUnit)
}