dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Gitgle"
include(":app")
include(":features")
include(":shared")
include(":features:favorite")
include(":features:search")
include(":domain")
