BmpWatermark
------------
------------
An Android Library built to watermark a bitmap given to it.

![Demo](https://github.com/Divya0319/BmpWatermark/tree/master/demo/watermarking_demo.gif)

Download
--------
You can download a jar from GitHub's [release page](https://github.com/Divya0319/BmpWatermark/releases)

Or use Gradle
```
repositories {
    mavenCentral()
    google()
}

implementation 'io.github.divya0319:BmpWatermark:1.1.0'
```
Or Maven:
```
<dependency>
  <groupId>io.github.divya0319</groupId>
  <artifactId>BmpWatermark</artifactId>
  <version>1.1.0</version>
  <type>aar</type>
</dependency>
```

Usage
------
Create builder instance first, passing context, bitmap and watermark text.
```
WatermarkProvider.Builder watermarkBuilder = new WatermarkProvider.Builder(context, selectedBitmap, "This is a watermark");
```
Then build watermark
```
WatermarkProvider wmp = watermarkBuilder.build();
```


To customise watermark
```
WatermarkProvider wmp = watermarkBuilder
                        .setColor(R.color.red_for_watermark)
                .setAlpha(200)  // (takes value 0-255) - O being invisible, 255 being fully visible
                .setxCoordinate(0)
                .setyCoordinate(120)
                .setTextSize(78)
                .setRotationAngle(45)  // in degrees
                .build();
```
