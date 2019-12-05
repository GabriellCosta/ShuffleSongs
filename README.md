# ShuffleSongs

> This is a simple app as a chance to try RxJava with other stuff

[![CircleCI](https://circleci.com/gh/GabriellCosta/ShuffleSongs/tree/master.svg?style=svg)](https://circleci.com/gh/GabriellCosta/ShuffleSongs/tree/master)

## Why I'm doing this?

I want to learn more about RxJava and try different things and create a efficient way to start
testing a app without need to create Ui tests

## How ?

This is single "interaction" app, we have only one screen

![Portrait Screenshot](.github/PORTRAIT.png) ![Landscape Screenshot](.github/LANDSCAPE.png)

This app uses the following stack ->

- 100% written in Kotlin
- Android Arch Components (LiveData, ViewModel)
- Unit tests with Mockk
- Koin for DI
- Multi Modules

## Recommended setup

This application uses:
- Android Studio 3.5.2
- Kotlin 1.3.61

## Building and running tests

To build the application you can use the following command in CLI

```
./gradlew assemble
```

or

```
./gradlew assembleDebug
```

This will generate both debug and release apk (the last one will build only Debug apk,
the release apk was not tested, so I recommend for now to use only Debug version)

Debug app location -> `app/build/outputs/apk/debug/app-debug.apk`

Release app location -> `app/build/outputs/apk/release/app-release-unsigned.apk`

## Installing

It's possible to just move the generate apk to some device and install, or use a built in task 

```
./gradlew installDebug
```

## QA ##

To run the application tests use

```
./gradlew check jacocoTestReport
```

This will run both unit and instrumented tests and generate a report
that will be located here -> 

`features/list/build/reports/jacoco/jacocoTestReport/html/index.html`

`commons/build/reports/jacoco/index.html`

## Special thanks

I have to thanks to ->

* David Eisenstat at StackOverflow and everyone in [this thread](https://stackoverflow.com/questions/25285792/generate-all-permutations-of-a-list-without-adjacent-equal-elements/25291640#25291640)
for helping with the permutation logic
