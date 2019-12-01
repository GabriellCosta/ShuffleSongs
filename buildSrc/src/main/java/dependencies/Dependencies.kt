package dependencies

internal object Versions {

    const val okhttp = "4.2.2"
    const val retrofit = "2.6.2"
    const val supportLibrary = "1.1.0"
    const val material = "1.0.0"
    const val recyclerView = "1.0.0"
    const val jUnit4 = "4.12"
    const val assertJ = "2.9.1"
    const val androidJUnit = "1.0.2"
    const val espressoCore = "3.2.0"
    const val roboletric = "4.3"
    const val koin = "2.0.1"
    const val timber = "4.7.1"
    const val livedata ="2.1.0"
    const val constraintLayout= "1.1.3"
    const val gson = "2.8.6"
    const val imageFetcher = "4.10.0"
    const val rxKotlin = "2.4.0"
    const val rxAndroid = "2.1.1"
    const val rxRetrofitAdapter = "2.6.2"

    const val mockk = "1.9.2.kotlin12"

}

object Dependencies {

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${PluginsVersions.kotlin}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.supportLibrary}"
    const val cardView = "androidx.cardview:cardview:${Versions.supportLibrary}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val materialDesign = "com.google.android.material:material:${Versions.material}"

    const val koin = "org.koin:koin-androidx-ext:${Versions.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinviewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.livedata}"
    const val lifecycle = "androidx.lifecycle:lifecycle-livedata:${Versions.livedata}"
    const val viewModelExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.livedata}"

    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rxRetrofitAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxRetrofitAdapter}"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val imageFetcher = "com.github.bumptech.glide:glide:${Versions.imageFetcher}"

}

object TestDependencies {

    const val androidXArchCoreTest = "androidx.arch.core:core-testing:2.0.1"
    const val androidXLifecycle = "androidx.lifecycle:lifecycle-runtime:2.0.0"

    const val jUnit = "junit:junit:${Versions.jUnit4}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${PluginsVersions.kotlin}"
    const val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"
    const val androidTestRunner = "com.android.support.test:runner:${Versions.androidJUnit}"
    const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espressoCore}"
    const val roboletric = "org.robolectric:robolectric:${Versions.roboletric}"

    const val runner = "androidx.test:runner:1.2.0"
    const val junitExt = "androidx.test.ext:junit:1.1.1"
    const val fragmentTest = "androidx.fragment:fragment-testing:1.1.0"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
}
