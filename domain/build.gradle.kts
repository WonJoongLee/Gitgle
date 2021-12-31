plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":data"))
    implementation(project(":shared"))
    implementation(Dep.Libs.hilt)
    implementation(Dep.AndroidX.hiltWork)
    implementation(Dep.Kotlin.coroutine)
    implementation(Dep.Kotlin.coroutineCore)

    kapt(Dep.Libs.hiltCompiler)

    testImplementation(Dep.Test.jUnit)
}