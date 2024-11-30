plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.ktlint)
	id("com.diffplug.spotless") version "7.0.0.BETA4"
}

spotless {



	kotlin {
		target("**/*.kt")
		ktlint("1.0.0").setEditorConfigPath("$rootDir/.editorconfig")
 	}

	kotlinGradle {
		target("*.gradle.kts")
		ktlint()
	}

	format("removeTrailingSpaces") {
		target("**/*.kt", "**/*.kts", "**/*.java", "**/*.gradle")
		trimTrailingWhitespace()
	}

	format("lineEndings") {
		target("**/*.kt", "**/*.kts", "**/*.java", "**/*.gradle")
		endWithNewline()
	}


//
// 	format("xml") {
// 		target("**/*.xml")
// 		prettier()
// 	}
}

// ktlint {
//    version.set("1.1.1")
//    verbose.set(true) // Enables detailed logging
//    android.set(true) // Enables Android-specific lint rules
//    outputToConsole.set(true) // Prints lint results to the console
//    outputColorName.set("RED") // Sets the color of error messages in the console
//    ignoreFailures.set(false) // Fails the build if issues are found
//    reporters {
//         reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
//
//    }
//    filter {
//        exclude("**/generated/**")
//        exclude("**/src/test/**/*.kt")
//    }
// }

android {
	namespace = "com.example.myktlint"
	compileSdk = 35
	defaultConfig {
		applicationId = "com.example.myktlint"
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
				"proguard-rules.pro",
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
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}
