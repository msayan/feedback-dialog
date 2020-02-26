[![](https://jitpack.io/#msayan/feedback-dialog.svg)](https://jitpack.io/#msayan/feedback-dialog)

# Feedback Dialog

Ready to use feedback dialog.

![sample_image](assets/demo.png)

## Usage

### Java
```kotlin 
    val dialog = FeedbackDialog.Builder(this)
        .setListener(object : FeedbackListener {
            override fun feedbackCancelled() {
                Toast.makeText(this@MainActivity, "Feedback cancelled!", Toast.LENGTH_LONG)
                    .show()
            }

            override fun feedbackSubmitted(rate: Rate, comment: String?) {
                Toast.makeText(
                    this@MainActivity,
                    "Feedback submitted! Selected is $rate and comment is $comment",
                    Toast.LENGTH_LONG
                )
                    .show()
            }

        })
        .setColors(R.color.wtg_blue, R.color.grey) // Colors for selected and unselected icons
        .setStyle(R.style.AppTheme_Dialog) // you can change anything via Theme
        .build()

    dialog.show()
```

## Download

### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groovy

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

### Step 2. Add the dependency

```groovy

	dependencies {
		implementation 'com.github.msayan:feedback-dialog:v1.0.1'
	}

```

## License

    MIT License

    Copyright (c) 2017 Mehmet Ayan

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
