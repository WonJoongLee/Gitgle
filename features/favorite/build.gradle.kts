plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    Dep.baseImplementation.forEach(::implementation)
    Dep.compose.forEach(::implementation)
    implementation(project(":shared"))
    implementation(project(":domain"))
    implementation(Dep.AndroidX.lifecycleRuntimeKtx)
    implementation(Dep.Compose.coil)
    implementation(Dep.Compose.constraintLayout)
    kapt(Dep.Libs.hiltCompiler)
    testImplementation(Dep.Test.jUnit)
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
    androidTestImplementation(Dep.Compose.uiTestJUnit4)
}