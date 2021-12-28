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
    implementation(Dep.AndroidX.core)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.material)
    implementation(Dep.AndroidX.constraintLayout)
    implementation(Dep.Libs.hilt)
    implementation(Dep.Libs.hiltViewModel)
    implementation(project(mapOf("path" to ":shared")))
    kapt(Dep.Libs.hiltCompiler)
    kapt(Dep.Libs.hiltViewModelCompiler)
    testImplementation(Dep.Test.jUnit)
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
}