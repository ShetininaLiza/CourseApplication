plugins {
    alias(libs.plugins.android.library)
    //alias(libs.plugins.devtoolsKsp)
}

android {
    namespace = "com.example.interaction"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(project(":data"))
    //попытка добавить Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.room.ktx)
    implementation(libs.androidx.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.androidx.room.rxjava2)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    /*
    //implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":data"))
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.room.ktx)//+
    implementation(libs.androidx.room.compiler)//+
    implementation(libs.room.runtime)//+
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.screenshot.validation.junit.engine)
    //implementation(libs.compose.bom)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.commons.codec)
    //+
    //?
    //implementation(libs.support.vector.drawable)

    //implementation(libs.androidx.compose.ui)
    //implementation(libs.androidx.compose.ui.graphics)
    //implementation(libs.androidx.compose.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    //debugImplementation(libs.androidx.compose.ui.test.manifest)
    */

}