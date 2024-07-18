# Vertical Stepper
[![Release](https://jitpack.io/v/com.github.rajdeepvaghela/CircularList.svg)](https://jitpack.io/#com.github.rajdeepvaghela/CircularList)
[![Release](https://img.shields.io/github/v/release/rajdeepvaghela/CircularList)](https://github.com/rajdeepvaghela/CircularList/releases)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

![image](https://github.com/user-attachments/assets/89774f90-1064-4a94-b0f1-7e9bc26c274c)

## Installation
Add it in your root build.gradle or settings.gradle at the end of repositories:
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
      mavenCentral()
      maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency
```gradle
dependencies {
    implementation 'com.github.rajdeepvaghela:CircularList:1.0.0'
}
```
## Usage
```kotlin
    var year by remember {
        mutableIntStateOf(2024)
    }

    val yearList = remember {
        (2020..2025).toList()
    }

    CircularList(
        width = 100.dp,
        itemHeight = 40.dp,
        items = yearList,
        initialItem = year,
        textColor = Color.LightGray,
        selectedTextColor = Color.Black,
        onItemSelected = { index, item ->
            year = item
        },
        prepareDisplayItem = {
            "2K ${it % 2000}"
        }
    )
```
## License
```
Copyright 2024 Rajdeep Vaghela

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
