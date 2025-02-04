plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

//    id("com.android.application")
    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
//    id("io.ktor.plugin") version "3.0.3"
}

android {
    namespace = "com.example.tokitokiinventorymanagementandroid"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tokitokiinventorymanagementandroid"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.library)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    implementation("androidx.databinding:databinding-runtime:8.8.0")
    implementation("androidx.databinding:databinding-common:8.8.0")

    /* Firebase stuff */
    implementation(platform("com.google.firebase:firebase-bom:33.8.0"))  // Import the Firebase BoM (Bill of Materials)
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-database-ktx")


    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.google.gms:google-services:4.4.2")
    implementation("com.google.android.material:material:1.8.0")

//    implementation("io.ktor:ktor-server-core:3.0.3")
//    implementation("io.ktor:ktor-server-netty:3.0.3")
}

configurations.all {
    resolutionStrategy {
        force("androidx.databinding:databinding-runtime:8.8.0")
        force("androidx.databinding:databinding-common:8.8.0")
    }
}
