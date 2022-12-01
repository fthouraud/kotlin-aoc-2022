plugins {
  kotlin("jvm") version "1.7.22" apply false

  id("io.gitlab.arturbosch.detekt") version "1.22.0"
  id("com.diffplug.spotless") version "6.12.0"
}

group = "me.fth"
version = "1.0"

allprojects {
  tasks {
    withType<Test> { useJUnitPlatform() }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf("-Xcontext-receivers")
      }
    }
    withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
      jvmTarget = "1.8"
    }
  }
}

detekt {
  buildUponDefaultConfig = true
  allRules = true
}

spotless {
  val editorConfigOverrides = mapOf(
    "ktlint_code_style" to "official",
    "insert_final_newline" to true,
    "indent_size" to 2,
    "indent_style" to "space",
    "max_line_length" to 140
  )
  kotlin {
    target("**/*.kt")
    ktlint().setUseExperimental(true).editorConfigOverride(editorConfigOverrides)
  }
  kotlinGradle {
    target("**/*.kts")
    ktlint().setUseExperimental(true).editorConfigOverride(editorConfigOverrides)
  }
}
