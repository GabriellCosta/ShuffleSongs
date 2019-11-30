package dependencies

object Modules {

    const val Network = ":network"
    const val Commons = ":commons"

    object Features {
        private const val upperModuleName = ":features"

        val list = createModuleName("list")

        private fun createModuleName(targetModule: String) = "$upperModuleName:$targetModule"
    }
}
