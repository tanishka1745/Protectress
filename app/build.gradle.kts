plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.protectress"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.protectress"
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
    buildFeatures{
        viewBinding = true;
        dataBinding= true;
        buildConfig= true;
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.tbuonomo:dotsindicator:4.3")
    implementation ("com.google.android.gms:play-services-maps:17.0.0")
    implementation ("com.google.android.gms:play-services-location:21.1.0")
    implementation ("com.squareup.retrofit2:retrofit:2.7.2")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.android.material:material:1.3.0-alpha03")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.hendraanggrian.material:collapsingtoolbarlayout-subtitle:1.5.0")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    implementation("com.google.firebase:firebase-analytics:21.5.0")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}