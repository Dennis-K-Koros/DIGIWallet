import java.util.Properties
import java.io.FileInputStream

val keystorePropertiesFile = rootProject.file("local.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.digiwallet"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.digiwallet"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            // Safely retrieve properties, fallback to empty string if missing
            storeFile = file(keystoreProperties.getProperty("KEYSTORE_PATH") ?: "")
            storePassword = keystoreProperties.getProperty("KEYSTORE_PASSWORD") ?: ""
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS") ?: ""
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD") ?: ""
        }
    }

    buildTypes {
        // 1. DEBUG: For everyday local development
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            isDebuggable = true
            // Point to your local or development server
            buildConfigField("String", "BASE_URL", "\"[https://dev.api.yourdomain.com/](https://dev.api.yourdomain.com/)\"")
        }

        // 2. STAGING: A replica of production for final testing before release
        create("staging") {
            initWith(getByName("debug")) // Inherit debug properties to start
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-STAGING"
            isDebuggable = true // Often kept true to catch crashes during QA
            // Point to a staging server that mirrors production data
            buildConfigField("String", "BASE_URL", "\"[https://staging.api.yourdomain.com/](https://staging.api.yourdomain.com/)\"")

            // Optional: Use ProGuard here to ensure your obfuscation doesn't break things
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        // 3. RELEASE: The polished version that goes to the Google Play Store
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            // Point to your live, production server
            buildConfigField("String", "BASE_URL", "\"[https://api.yourdomain.com/](https://api.yourdomain.com/)\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    //Module
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":feature:auth"))


    // Standard Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // BOMs
    implementation(platform(libs.androidx.compose.bom))

    // Bundles
    implementation(libs.bundles.compose)

    // Dependency Injection - Koin
    implementation(libs.bundles.koin)

    // Debug
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

}