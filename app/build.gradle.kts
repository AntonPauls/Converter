
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //  Плагены для обработки анотаций Кotlin
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.testingex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testingex"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        // Включить привязку предствления функции сборки
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Библиотеки для внедрения зависимостей
    //Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    kapt ("com.google.dagger:hilt-android-compiler:2.48")


    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    //Activity KTX for viewModels()
    // Активности для моделей представления которая представляет собой функции делегирования из Kotlin которые мы можем использовать для внедрения
    implementation("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.activity:activity-ktx:1.8.2")

    // Architectural Components
    // Для можели представления всего жизненого цикла

    // Есть модификации, чтобы сделать сетевой запрос к нашему валютному API, чтобы мы получили текущие курсы
    //Чтобы понимать как мы должны конвертировать валюту
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

    // Зависимости запросов для виполнения сетевых вызовов чтобы работа смогла происходить за кулисами
    // Также для таких вещей как поток состояния которые являються нашей заменой для живых данных
    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.fragment:fragment-ktx:1.6.2")
}
kapt {
    correctErrorTypes = true
}
