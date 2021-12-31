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
    implementation(Dep.AndroidX.roomKtx)
    api(Dep.AndroidX.roomRuntime)
    kapt(Dep.Libs.hiltCompiler)
    kapt(Dep.AndroidX.roomCompiler)
    testImplementation(Dep.Test.jUnit)
}