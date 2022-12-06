rootProject.name = "aoc-2022"

dependencyResolutionManagement {
  repositories {
    mavenCentral()
  }
}

include("common")
include("day-01", "day-02", "day-03", "day-04", "day-05", "day-06")
