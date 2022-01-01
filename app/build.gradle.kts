plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    defaultConfig {
        applicationId = "com.wonjoong.gitgle"
        versionCode = Apps.versionCode
        versionName = Apps.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(project(":features:favorite"))
    implementation(project(":features:search"))

    implementation(Dep.AndroidX.core)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.material)
    implementation(Dep.AndroidX.constraintLayout)
    implementation(Dep.AndroidX.navigationUIKtx)
    implementation(Dep.AndroidX.navigationFragmentKtx)

    implementation(Dep.Libs.hilt)
    implementation(Dep.Libs.hiltViewModel)

    kapt(Dep.Libs.hiltCompiler)
    kapt(Dep.Libs.hiltViewModelCompiler)

    testImplementation(Dep.Test.jUnit)

    androidTestImplementation(Dep.Test.ext)
    androidTestImplementation(Dep.Test.espresso)
}