plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.kerolosmagdy.imageproccessing"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kerolosmagdy.imageproccessing"
        minSdk = 24
        targetSdk = 34
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.palette:palette-ktx:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // room
    implementation("androidx.room:room-common:2.6.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("android.arch.persistence.room:compiler:1.1.1")
//    annotationProcessor("android.arch.persistence.room:compiler:1.1.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    kapt("androidx.lifecycle:lifecycle-compiler:2.6.2")
    kapt("androidx.room:room-compiler:2.6.1")

    // Navigation
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.10")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")

    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    // For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Coil image loader
    implementation("io.coil-kt:coil:2.5.0")

    // viewModels
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // swipe refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

    // glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")

    //Pick Image From Camera And Gallery
    implementation("com.github.dhaval2404:imagepicker:2.1")

}

kapt {
    correctErrorTypes = true
}