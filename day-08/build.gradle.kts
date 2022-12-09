plugins {
  kotlin("jvm")
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))

  implementation(project(":common"))

  val kotestVersion: String by project
  testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
  testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
  testImplementation("io.kotest:kotest-property:$kotestVersion")
}
