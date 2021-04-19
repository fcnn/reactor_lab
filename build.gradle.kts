import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradle_version="7.0"

plugins {
	application
	id("org.jetbrains.kotlin.jvm") version "1.5.0-RC"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.y"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/plugins-snapshot") }
	maven ("https://dl.bintray.com/kotlin/kotlin-eap")
	maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

application {
	mainClassName="com.my.demo.MainKt"
}

dependencies {
	implementation("com.google.guava:guava:+")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("io.projectreactor:reactor-core:+")
	testImplementation("io.projectreactor:reactor-test:+")
}

dependencyManagement {
	imports {
		mavenBom("io.projectreactor:reactor-bom:Bismuth-RELEASE")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "15"
	}
}

tasks.wrapper {
	gradleVersion = gradle_version
	distributionType = Wrapper.DistributionType.ALL
}
