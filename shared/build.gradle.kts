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
    implementation(Dep.AndroidX.core)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.material)
    implementation(Dep.AndroidX.constraintLayout)
    implementation(Dep.Libs.glide)
    testImplementation(Dep.Test.jUnit)
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
}