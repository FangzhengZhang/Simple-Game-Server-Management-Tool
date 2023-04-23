
import org.gradle.api.tasks.Copy

tasks.register<Copy>("copyScripts") {
    from("../scripts")
    into("build/scripts")
}

tasks.named("bootJar") {
    dependsOn("copyScripts")
}

plugins {
	java
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "cat.frank"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation ("mysql:mysql-connector-java:8.0.32")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.apache.logging.log4j:log4j-core:2.20.0")
	implementation ("org.springframework.boot:spring-boot-starter-mail")
	implementation ("org.quartz-scheduler:quartz")
}

// tasks.withType<Test> {
// 	useJUnitPlatform()
// }
