# Datastore
## Ejemplo de uso de Datastore con Kotlin

Ejemplo de uso de Datastore en un proyecto Android con Kotlin.

## Caracteristicas

En este ejemplo se ha utilizado:

- Inyección de dependencias con Dagger Hilt
- Corrutinas 
- ViewModel y LiveData
- MVVM y Clean Arquitecture
- DataBinding y ViewBinding
- Fragments y NavGraph

## Instalación de gradle de aplicacion

Instalar las siguientes dependencias

```sh
dependencies {

    implementation "androidx.core:core-ktx:$core"
    implementation "androidx.appcompat:appcompat:$appcompat"
    implementation "com.google.android.material:material:$material"
    implementation "androidx.constraintlayout:constraintlayout:$constraint"
    implementation 'androidx.preference:preference-ktx:1.2.0'

    testImplementation "junit:junit:$junit"
    androidTestImplementation "androidx.test.ext:junit:$test"
    androidTestImplementation "androidx.test.espresso:espresso-core:$espresso"

    // Fragment
    implementation "androidx.fragment:fragment-ktx:$fragment_version"

    // Kotlin - Activity
    implementation "androidx.activity:activity-ktx:$activity_version"

    // ViewModel
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$viewmodel_version"

    // LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$livedata_version"

    //Corrutinas: Entre otras muchas cosas nos permitirá hacer las peticiones de Retrofit
    // en segundo plano para no bloquear la interfaz del usuario
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

    //Datastore
    implementation "androidx.datastore:datastore-preferences:$datastore_version"

    //Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    //Dagger - Hilt
    implementation 'com.google.dagger:hilt-android:2.44.2'
    implementation 'androidx.hilt:hilt-navigation-fragment:1.0.0'
    kapt 'com.google.dagger:hilt-compiler:2.44.2'
}
```

Habilitar ViewBinding y DataBinding

```sh
buildFeatures {
        viewBinding true
        dataBinding true
    }
```
Habilitar la ultima version de Java

```sh
compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
```
Incorporar plugin de Dagger Hilt

```sh
plugins {
    id 'com.android.application'
    id 'kotlin-kapt'
    id 'kotlin-android'
    id("com.google.dagger.hilt.android")
}
```
Incorporar error de kapt
```sh
kapt{
    correctErrorTypes = true
}
```
## Instalación de gradle de proyecto

Incorporar error de kapt
```sh
plugins {
    id 'com.android.application' version '7.3.1' apply false
    id 'com.android.library' version '7.3.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.7.10' apply false
    id 'com.google.dagger.hilt.android' version '2.44.2' apply false
}
```
## License

MIT
**Free Software, Hell Yeah!**
