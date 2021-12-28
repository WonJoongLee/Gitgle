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
include(":features:search")
include(":features:favorite")
include(":shared")
include(":features:settings")
