# Creating the first project
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

With that, you will can start your game application and you will see the DefaultState, which means that the engine is working fine.
