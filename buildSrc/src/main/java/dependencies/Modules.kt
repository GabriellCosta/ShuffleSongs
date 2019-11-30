package dependencies

object Modules {

    const val Network = ":network"
    const val Commons = ":commons"
    const val Styleguide = ":styleguide"

    object Features {
        private const val upperModuleName = ":features"

        val list = createModuleName("list")

        private fun createModuleName(targetModule: String) = "$upperModuleName:$targetModule"
    }
}
