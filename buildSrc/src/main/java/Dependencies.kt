@file:Suppress("MayBeconstant")

object ApplicationID {
    val id = "com.example.tinderclone"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {

    val kotlin = "1.3.61"

    val gradle = "3.5.1"
    val minSDK = 23
    val targetSDK = 29
    val compileSDK = 29

    val buildToolsVersion = "29.0.2"

    val navigation = "2.3.0-alpha01"
    val appCompat = "1.1.0"
    val material = "1.1.0"
    val core_ktx = "1.2.0"
    val constraint = "1.1.3"

    val epoxy = "3.7.0"

    val gson = "2.6.0"

    val junit = "4.12"
    val ext_junit = "1.1.1"
    val espresso = "3.2.0"

    val retrofit = "2.6.0"
    val okhttp = "4.1.0"

    val leakCanary = "2.0"

    val timber = "4.7.1"

    val ktlint = "0.29.0"
    val mvrx = "2.0.0-alpha2"
    val dagger = "2.21"
    val assistedInject = "0.3.3"
    val glide = "4.11.0"
    val cardView = "1.0.0"
    val recyclerView = "1.0.0"

    val lifecycle = "2.2.0-alpha01"
    val savedState = "1.0.0-rc01"
}

object Libraries {

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx}"

    val mvrx = "com.airbnb.android:mvrx:${Versions.mvrx}"

    val epoxy = "com.airbnb.android:epoxy:${Versions.epoxy}"
    val epoxyProcessor = "com.airbnb.android:epoxy-processor:${Versions.epoxy}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    val assistedInject =
        "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assistedInject}"
    val assistedInjectProcessor =
        "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assistedInject}"

    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
    val lifecycleExtensions =  "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata:${Versions.lifecycle}"
    val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.savedState}"

    val viewModelScope = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    val material = "com.google.android.material:material:${Versions.material}"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val junit = "junit:junit:${Versions.junit}"
    val extJunit = "androidx.test.ext:junit:${Versions.ext_junit}"
    val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    //  Tinder like swipe layout
    val tinderSwipe = "com.yuyakaido.android:card-stack-view:2.3.4"

    val ktlint = "com.github.shyiko:ktlint:${Versions.ktlint}"
}