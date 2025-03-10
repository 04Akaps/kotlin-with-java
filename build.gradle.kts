plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("plugin.spring") version "1.8.0"
    kotlin("jvm") version "2.0.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // ktor
    implementation("io.ktor:ktor-client-core:2.2.3") // 최신 2.x 버전
    implementation("io.ktor:ktor-client-cio:2.2.3")

    // serializer
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.3")

    implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("io.github.resilience4j:resilience4j-spring-boot3:2.1.0")

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    compileOnly ("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")


    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.aspectj:aspectjweaver")
    implementation("org.springframework.boot:spring-boot-starter-logging")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}