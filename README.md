[![Release](https://img.shields.io/badge/releases-1.0-green)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white)](https://kotlinlang.org/)

# Baekcasajeon Library

Baekcasajeon is an Android library that amplifies the functionality of TextViews. This library allows you to emphasize keywords and customize their appearance based on selection. Users can interact with keywords within the TextView, and for every keyword clicked, a specified action can be performed.

## Features

- **Keyword Highlight**: Specify the appearance of keywords according to their selected or unselected state through a simple map.
- **Clickable Keywords**: Make the highlighted keywords interactive. A callback is provided to manage actions performed on keyword clicks.
- **Customization**: Adjust the padding around the keyword, corner radius of the background, and boldness of the keyword text. Different configurations can be set for selected and unselected states.
- **Color Adjustments**: Designate colors for keywords based on their selected or unselected state.

## Installation

To begin, integrate the JitPack repository in your build file:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Then, add the dependency:
```
dependencies {
    implementation 'com.github.hyeonseongkang:baekcasajeon:1.0'
}
```

## Usage

Define settings for your keywords first:

```
val keywordOptions = mapOf(
    "android" to KeywordOptions(
        nonSelectedTextColor = Color.BLACK,
        selectedTextColor = Color.WHITE,
        nonSelectedBackgroundColor = Color.YELLOW,
        selectedBackgroundColor = Color.RED,
        padding = Rect(5, 5, 5, 5),
        cornerRadius = 8f,
        isBold = true
    ),
    "library" to KeywordOptions(
        nonSelectedTextColor = Color.WHITE,
        selectedTextColor = Color.BLACK,
        nonSelectedBackgroundColor = Color.BLUE,
        selectedBackgroundColor = Color.GREEN
    )
)
```
Then, apply these settings to your TextView:

```
yourTextView.baekcasajeon(keywordOptions) { keyword ->
    // Handle the keyword click event here.
    // For example, present a custom dialog to the user or refresh the UI based on the clicked keyword.
}
```

--- 

# 백카사전 라이브러리

백카사전은 TextView의 기능을 향상시키는 Android 라이브러리입니다. 이 라이브러리를 통해 키워드를 강조하고 선택한 상태에 따라 키워드의 외관을 맞춤 설정할 수 있습니다. 사용자는 TextView 내의 키워드와 상호작용하며, 키워드를 클릭할 때마다 원하는 작업을 수행할 수 있습니다.

## 기능
- **키워드 강조**: 선택된 상태와 선택되지 않은 상태에 따른 키워드의 모양을 간단한 맵을 통해 지정하세요.
- **클릭 가능한 키워드**: 강조된 키워드를 클릭할 수 있게 만드세요. 키워드 클릭 시 수행될 작업을 관리하는 콜백 함수를 제공합니다.
- **맞춤 설정**: 키워드 주변의 패딩, 배경의 모서리 반경 및 키워드 텍스트의 굵기 등을 지정할 수 있습니다. 선택된 상태와 선택되지 않은 상태에 따라 각각 다르게 설정할 수 있습니다.
- **색상 조절**: 선택된 상태와 선택되지 않은 상태에 따라 키워드의 색상을 별도로 지정하세요.

## 설치 방법
먼저, 빌드 파일에 JitPack 저장소를 추가하세요:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
그리고 의존성을 추가하세요:
```
dependencies {
    implementation 'com.github.hyeonseongkang:baekcasajeon:1.0'
}
```


## 사용 방법
먼저 키워드에 대한 설정을 정의하세요:
```
val keywordOptions = mapOf(
    "android" to KeywordOptions(
        nonSelectedTextColor = Color.BLACK,
        selectedTextColor = Color.WHITE,
        nonSelectedBackgroundColor = Color.YELLOW,
        selectedBackgroundColor = Color.RED,
        padding = Rect(5, 5, 5, 5),
        cornerRadius = 8f,
        isBold = true
    ),
    "library" to KeywordOptions(
        nonSelectedTextColor = Color.WHITE,
        selectedTextColor = Color.BLACK,
        nonSelectedBackgroundColor = Color.BLUE,
        selectedBackgroundColor = Color.GREEN
    )
)
```

그런 다음 해당 설정을 TextView에 적용하세요:
```
yourTextView.baekcasajeon(keywordOptions) { keyword ->
    // Handle the keyword click event here.
    // For example, present a custom dialog to the user or refresh the UI based on the clicked keyword.
}
```
---

## License

This project is licensed under the MIT License. For more details, see the [LICENSE](LICENSE) file.
