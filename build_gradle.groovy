plugins {
    id 'java'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.13.1'
    implementation 'javax.xml.bind:jaxb-api:2.3.3'
    implementation 'com.sun.xml.bind:jaxb-core:2.3.0.1'
    implementation 'com.sun.xml.bind:jaxb-impl:2.3.3'
}

application {
    mainClassName = 'your.package.name.Main'
}
