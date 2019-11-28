import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("org.jetbrains.kotlin.jvm") version "1.3.61"
}

group = "com.y"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

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
		jvmTarget = "11"
	}
}

tasks.wrapper {
	gradleVersion = "6.1-milestone-2"
	distributionType = Wrapper.DistributionType.ALL
}
