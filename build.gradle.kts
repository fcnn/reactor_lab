import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("org.jetbrains.kotlin.jvm") version "1.3.70"
}

group = "com.y"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_14

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
	maven { url = uri("https://repo.spring.io/plugins-snapshot") }
	maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

application {
	mainClassName="com.my.demo.MainKt"
}

dependencies {
	implementation("com.google.guava:guava:+")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	//implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.60")
	implementation("io.projectreactor:reactor-core")
	testImplementation("io.projectreactor:reactor-test")
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
		jvmTarget = "13"
	}
}

tasks.wrapper {
	gradleVersion = "6.3-rc-1"
	distributionType = Wrapper.DistributionType.ALL
}
