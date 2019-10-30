[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-SvgGlidePlugin-green.svg?style=flat )]( https://android-arsenal.com/details/1/6812 )
[![JCenter Download](https://api.bintray.com/packages/kirich1409/maven/svg-glide-plugin/images/download.svg) ](https://bintray.com/kirich1409/maven/svg-glide-plugin/_latestVersion)
[![Build Status](https://travis-ci.org/kirich1409/SvgGlidePlugins.svg?branch=master)](https://travis-ci.org/kirich1409/SvgGlidePlugins)

# SvgImageLoaderPlugins
Plugin for load SVG in Glide 4. Based on [AndroidSVG](http://bigbadaboom.github.io/androidsvg/) library.
During loading SVG will converted into Bitmap. The solution was selected for better performance of drawing and smaller size of source.

Grab the latest via Gradle with Groovy DSL:
```groovy
repository {
   jcenter()
}

implementation 'com.kirich1409.svgplugin:glide4:1.2.0'
```

or with Kotlin DSL
```kotlin
repository {
   jcenter()
}

implementation("com.kirich1409.svgplugin:glide4:1.2.0")
```

Library requires Android SDK 15+

Fot library work don't forget create AppGlideModule like described [here](http://bumptech.github.io/glide/doc/generatedapi.html).

```kotlin
@GlideModule
class SampleGlideModule : AppGlideModule {
    // Any additional configuration for the SvgGlide4Plugin not required
}
```

License
=======

    Copyright 2019 Kirill Rozov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
