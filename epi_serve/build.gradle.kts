import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.spring") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.7.0"
}

group = "team.jtq"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

noArg {
    annotation("team.jtq.epi_serve.annotation.NoArg")
    invokeInitializers = true
}
repositories {
    mavenLocal()
    mavenCentral()
    maven {
        setUrl("https://plugins.gradle.org/m2/")
    }
    maven {
        setUrl("https://maven.aliyun.com/nexus/content/repositories/google")
    }
    maven {
        setUrl("https://maven.aliyun.com/nexus/content/groups/public")
    }
    maven {
        setUrl("https://maven.aliyun.com/nexus/content/repositories/jcenter")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.session:spring-session-data-redis")
    implementation("org.projectlombok:lombok:1.18.22")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.2")
    implementation("com.alibaba:druid-spring-boot-starter:1.2.11")
    implementation("com.github.penggle:kaptcha:2.3.2")
    // https://mvnrepository.com/artifact/com.alibaba/fastjson
    implementation("com.alibaba:fastjson:2.0.12")
    implementation("org.springframework.boot:spring-boot-configuration-processor:2.7.3")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")


}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
