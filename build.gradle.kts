import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	application
}

group = "com.just-ai"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("jakarta.validation:jakarta.validation-api:3.1.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.slf4j:slf4j-api:2.0.13")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("ch.qos.logback:logback-classic:1.5.6")
	testImplementation("ch.qos.logback:logback-core:1.5.6")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
	mainClass = "com.just_ai.test_assignment.BotApplicationKt"
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
