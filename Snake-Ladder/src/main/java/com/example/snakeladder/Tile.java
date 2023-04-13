package com.example.snakeladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    /*Constructor of Tile class */
    public Tile(int tileSize){
        setWidth(tileSize);
        setHeight(tileSize);
        /*set fill is to fill the each rectangle size tile*/
        setFill(Color.AZURE);
        /*set stroke is to set the boundary color*/
        setStroke(Color.BLACK);
    }
}
