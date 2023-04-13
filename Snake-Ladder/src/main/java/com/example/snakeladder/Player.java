package com.example.snakeladder;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {

    private Circle coin;
    private int currentPosition;
    private String name;

    private static Board gameBoard = new Board();

    /*constructor*/
    public Player(int tileSize, Color coinColor, String playerName){
        coin = new Circle(tileSize/2);
        coin.setFill(coinColor);
        currentPosition = 0; // you can set 1, out of board or anything
      /* as soon as player created and assigned its position, i will call move function,,
       it will initially place the coin at the position,, provided in move player function */
        movePlayer(1);
        name = playerName;
    }

    /*Method to move players*/
    public  void movePlayer(int diceValue){
        /*now the player move in two situation ,, from current position to dice value and from there onwards  move
         * to the next position(be the end of the ladder or tail of a snake)*/
        // dice roll hoga or uspe jo value aayi hai wo current position mein add ho jaayegi
        if(currentPosition + diceValue <= 100){
            currentPosition += diceValue;

            TranslateTransition firstMove = translateAnimation(diceValue);
            TranslateTransition secondMove = null;

            // ye case snake and ladder ke effect ke liye hai
            int newPosition = gameBoard.getNewPosition(currentPosition);
            if(newPosition != currentPosition && newPosition != -1){
                // make a movement
                currentPosition = newPosition;
                secondMove = translateAnimation(6);
            }

            if(secondMove == null){
                firstMove.play();
            }
            else{
                SequentialTransition sequentialTransition = new SequentialTransition(firstMove,
                        new PauseTransition(Duration.millis(1000)), secondMove);
                sequentialTransition.play();
            }
        }

        /* now i will comment this part becose i want to move the players with slow movements
        // with this we get the x and y coordinates of player
        int x = gameBoard.getXCoordinate(currentPosition);
        int y = gameBoard.getYCoordinate(currentPosition);
        // with this i am moving the coin of that player(basically setting the position of coin)
        coin.setTranslateX(x);
        coin.setTranslateY(y);
                     */


    }

    /* I want my players to move slowly*/
    private TranslateTransition translateAnimation(int diceValue){
        // hume kitni slowly move karna hai and kis object ko move karna hai
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*diceValue),coin);
        // now from which place i want to move
        animate.setToX(gameBoard.getXCoordinate(currentPosition));
        animate.setToY(gameBoard.getYCoordinate(currentPosition));
        // Auto reverse  so that it can't move backwards
        animate.setAutoReverse(false);
        return  animate;
    }

    // after winning player should start from starting position
    public void startingPosition(){
        currentPosition = 0;
        movePlayer(1);
    }
    /*Winning conditions*/
    boolean isWinner(){
        if(currentPosition == 100){
            return true;
        }
        else{
            return false;
        }
    }

    /*made getter methods to get the values of circle, current position, name,,, because we made them private*/
    public Circle getCoin() {
        return coin;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public String getName() {
        return name;
    }


}
