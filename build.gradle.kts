import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
	id("java")
	application
  	kotlin("jvm") version("1.6.20")
	id("com.github.johnrengelman.shadow") version "7.1.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.my"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	gradlePluginPortal()
	maven ("https://repo.spring.io/milestone")
	maven {
		url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
		mavenContent { snapshotsOnly() }
	}
}

application {
	mainClass.set("com.my.demo.MainKt")
}

dependencies {
	implementation("com.google.guava:guava:+")
	implementation(kotlin("stdlib-jdk8"))
	implementation("io.projectreactor:reactor-core:+")
	testImplementation("io.projectreactor:reactor-test:+")
}

dependencyManagement {
	imports {
		//mavenBom("io.projectreactor:reactor-bom:2022.0.0-M1")
	}
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
//  manifest {
//    attributes(mapOf("mainClassName" to "com.my.demo.MainKt"))
//  }
  mergeServiceFiles()
}


tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.wrapper {
	gradleVersion = "7.4.2"
	distributionType = Wrapper.DistributionType.ALL
}
