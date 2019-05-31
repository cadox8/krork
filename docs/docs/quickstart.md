# Getting started

## Getting the Engine
You have three options to start with Krork: **Maven**, **Gradle** and **Direct Download**.

### Maven
You only have to add this to your **pom.xml**:
```xml
<repository> 
    <id>krork-repo</id> 
    <url>https://cadox8.github.io/krork/repo/</url> 
</repository>
```
```xml
<dependency> 
    <groupId>net.athonedevs</groupId> 
    <artifactId>Krork</artifactId> 
    <version>VERSION</version> 
</dependency>
```
<small>**Note:** You have to replace `VERSION` for the version you want to use (See [versions](https://cadox8.github.io/krork/versions.html))</small>

### Gradle
Not yet...

### Direct Download
Simple, just [download the version you want to use](https://cadox8.github.io/krork/versions.html) and add it as dependency in your favourite IDE.
<br>
You can download it from [here](https://cadox8.github.io/krork/versions.html).

## Adding the engine to your project
First of all, you need to create the **Main class** which will contains the main method to start your game.

Next (and with the engine as dependency) you will need to write the following:
```java
public static void main(String... args) {
    final Krork engine = new Krork(Game_Name, Screen_Title, width, height);
    engine.start();
}
```
Variable Name | Type         | Description
------------ | ------------- | -------------
Game_Name    | String        | The name of your game
Screen_Title | String        | The name you want to be displayed on the screen title
width        | int           | The width you of the screen
height       | int           | The height you of the screen