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
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    Dep.baseImplementation.forEach(::implementation)
    Dep.compose.forEach(::implementation)
    implementation(project(":shared"))
    implementation(project(":domain"))
    kapt(Dep.Libs.hiltCompiler)
    testImplementation(Dep.Test.jUnit)
    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
    androidTestImplementation(Dep.Compose.uiTestJUnit4)
}