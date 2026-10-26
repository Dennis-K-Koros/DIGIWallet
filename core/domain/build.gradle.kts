plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}


dependencies {
    implementation(libs.material)

    // Dependency Injection - Koin
    implementation(libs.bundles.koin)

    testImplementation(libs.junit)
}