plugins{
    `kotlin-dsl`
}
repositories {
    gradlePluginPortal()
    mavenCentral()
}
dependencies{
    implementation(libs.pluginKotlin)
    implementation(libs.pluginComposeCopmiler)
    implementation(libs.pluginKsp)
    implementation(libs.pluginSerialization)
    implementation(libs.pluginComposeMultiplatform)
    implementation(libs.pluginChangeLog)
    implementation(libs.pluginBuildConfig)
    implementation(libs.pluginAboutLibraries)
    implementation(libs.semver)
    implementation("ir.amirab.util:platform:1")
    implementation("ir.amirab.plugin:git-version-plugin:1")
}