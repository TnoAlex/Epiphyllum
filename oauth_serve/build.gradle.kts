import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.7.0"
    kotlin("plugin.spring") version "1.6.21"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.7.0"
}

group = "team.jtq.auth"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

noArg {
    annotation("team.jtq.auth.oauth_serve.annotation.NoArg")
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
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.security.oauth:spring-security-oauth2:2.5.2.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.alibaba:fastjson:1.2.60")
    implementation("org.springframework.session:spring-session-data-redis")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("junit:junit:4.13.1")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.2")
    implementation("com.alibaba:druid-spring-boot-starter:1.2.11")
    // https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
//    implementation("org.glassfish.jaxb:jaxb-core:4.0.0")
    implementation("org.glassfish.jaxb:jaxb-runtime:4.0.0")
    // https://mvnrepository.com/artifact/jakarta.xml.bind/jakarta.xml.bind-api
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    // https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.7.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.3")
    // https://mvnrepository.com/artifact/org.thymeleaf.extras/thymeleaf-extras-springsecurity5
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5:3.0.4.RELEASE")


    // https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
//    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
//// https://mvnrepository.com/artifact/javax.xml.bind/activation
//    implementation("javax.xml.bind:activation:1.0.2")
//    // https://mvnrepository.com/artifact/com.sun.xml.bind/jaxb-impl
//    implementation("com.sun.xml.bind:jaxb-impl:4.0.0")


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
