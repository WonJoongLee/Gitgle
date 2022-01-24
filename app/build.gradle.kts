plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    defaultConfig {
        applicationId = "com.wonjoong.gitgle"
        versionCode = Apps.versionCode
        versionName = Apps.versionName
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    Dep.compose.forEach(::implementation)

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