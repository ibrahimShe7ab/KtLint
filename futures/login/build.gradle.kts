import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
	id("io.gitlab.arturbosch.detekt") version "1.23.7"

}

detekt {
	buildUponDefaultConfig = true // preconfigure defaults
	allRules = true // activate all available (even unstable) rules.
	config.setFrom("$rootDir/detekt/detekt.yml") // point to your custom config defining rules to run, overwriting default behavior
	baseline = file("${projectDir}/detekt/baseline.xml") // a way of suppressing issues before introducing detekt
}

tasks.withType<Detekt>().configureEach {
	reports {
		html.required.set(true) // observe findings in your browser with structure and code snippets
		xml.required.set(true) // checkstyle like format mainly for integrations like Jenkins

	}
}




android {
    namespace = "com.example.login"
    compileSdk = 34

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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
	detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.7")

	androidTestImplementation(libs.androidx.espresso.core)
}
