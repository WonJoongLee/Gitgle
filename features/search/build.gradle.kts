plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    Dep.baseImplementation.forEach(::implementation)
    implementation(Dep.Libs.glide)
    implementation(Dep.AndroidX.browser)
    implementation(Dep.AndroidX.lifecycleRuntimeKtx)
    implementation(project(mapOf("path" to ":shared")))
    implementation(project(":data"))
    kapt(Dep.Libs.hiltCompiler)
    kapt(Dep.Libs.hiltViewModelCompiler)
    testImplementation(Dep.Test.jUnit)
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
}