# AutoTextSwitcher
[![](https://jitpack.io/v/masoudsaraf/androidautotextswitcher.svg)](https://jitpack.io/#masoudsaraf/androidautotextswitcher)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

This is an Android project allowing to auto change text in specific period of time with your chosen animation.

## Add dependency

- First add repository in your `build.gradle` file at project level:

```groovy
allprojects {
    repositories {
        ....
        maven { url 'https://jitpack.io' }
    }
}
```
Or with newest Gradle version (+7.2.0) do like this in `settings.gradle` file:
```groovy
pluginManagement {
    repositories {
        ....
    }
}
dependencyResolutionManagement {
    ....
    repositories {
        ....
        maven { url 'https://jitpack.io' }
    }
}
....
```
- And then add dependency to your project in the `build.gradle` file at app level:
```groovy
implementation 'com.github.masoudsaraf:androidautotextswitcher:1.0.2'
```

## How to use in XML
You can set textArray from XML or java code. In XML easiest way is add your text in strings resource and create a text array like this:
```xml
<resources>
    ....
    <string name="first_text">Show awesome first text</string>
    <string name="sec## Headingond_text">Show awesome second text</string>

    <string-array name="textArray">
        <item>@string/first_text</item>
        <item>@string/second_text</item>
    </string-array>
</resources>
```
And then use created textArray as follow:
```xml
<com.masoud.autotextswitcher.AutoTextSwitcher
    android:id="@+id/textSwitcher"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:inAnimation="@anim/text_in_animation"
    android:outAnimation="@anim/text_out_animation" 
    app:textArray="@array/textArray"
    app:changeAnimationTime="3000" 
    app:autoStart="true"/>
```
You can use following properties:<br>
`android:inAnimation` Set animation for show next text. <br>
`android:outAnimation` Set animation for hide current text.<br>
`app:textArray` Set array of string.<br>
`app:changeAnimationTime` Set time in milliseconds.<br>
`app:autoStart` Indicates auto start changing text or not

## Complete work in java code
In your Activity class access to `AutoTextSwitcher` element with `findViewById` and then set factory to create a text view with your preferred properties:
```java
public class MainActivity extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        ...
        AutoTextSwitcher textSwitcher = findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(() ->
        {
            AppCompatTextView textView = new AppCompatTextView(MainActivity.this);
            // set custom text appearance
            textView.setTextAppearance(MainActivity.this, R.style.InfoTextAppearanceStyle);
            textView.setPadding(10, 10, 10, 10);
            textView.setShadowLayer(12, 0, -5.5f, Color.parseColor("#65FFFFFF"));
            return textView;
        });
    }
}
```
## Public methods
Call `startTextAnimation()` for manually start changing texts with animation.<br>
Call `stopTextAnimation()` for stop animation and changing texts.<br>
Call `isTextAnimationIsRunning()` to determine is animation and changing text is activate or not.<br>
Call `setTextArray(List<CharSequence>)` to set new text array from code. even you can have Spannable text in array.<br>
Call `appendToTextArray(CharSequence newText)` to append a text to exists array. Just like above you can use Spannable text.
