package dependencies

object PluginsVersions {
    const val kotlin = "1.3.61"
    const val androidGradlePlugin = "3.5.0"
    const val detekt = "1.1.1"
}

object BuildPlugins {

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.kotlin}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${PluginsVersions.androidGradlePlugin}"
    const val detektPlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.detekt}"

}
