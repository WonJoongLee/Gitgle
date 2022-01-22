plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    Dep.baseImplementation.forEach(::implementation)

    implementation(project(":domain"))
    implementation(Dep.Libs.glide)
    implementation(Dep.AndroidX.browser)
    implementation(Dep.AndroidX.lifecycleRuntimeKtx)
    implementation(project(mapOf("path" to ":shared")))

    kapt(Dep.Libs.hiltCompiler)
    kapt(Dep.Libs.hiltViewModelCompiler)

    testImplementation(Dep.Test.jUnit)

    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
}