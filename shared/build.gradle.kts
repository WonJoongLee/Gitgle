plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(Dep.AndroidX.material)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.core)
    implementation(Dep.AndroidX.roomKtx)
    implementation(Dep.AndroidX.browser)
    implementation(Dep.Libs.glide)
    implementation(Dep.Libs.gson)
    implementation(Dep.Libs.gsonConverter)

    testImplementation(Dep.Test.jUnit)

    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)

    api(Dep.AndroidX.roomRuntime)

    kapt(Dep.AndroidX.roomCompiler)
}