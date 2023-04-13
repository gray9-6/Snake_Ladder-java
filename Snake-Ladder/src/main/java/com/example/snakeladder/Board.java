package com.example.snakeladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {

    ArrayList<Pair<Integer,Integer>> positionCoordinates;  // this is to for coordinate positions
    ArrayList<Integer> snakeLadderPosition;         // this is to bring  snake and ladder effect on board

    public Board(){
        positionCoordinates = new ArrayList<>();
        populatePositionCoordinates();
        populateSnake_Ladder();
    }

    private  void  populatePositionCoordinates(){
        positionCoordinates.add(new Pair<>(0,0));  // dummy values
        for (int i = 0; i < SnakeLadder.height; i++) {
            for (int j = 0; j <SnakeLadder.width ; j++) {
                // x coordinates
                int xCord;
                if(i%2 == 0){
                    xCord = j*SnakeLadder.tileSize + SnakeLadder.tileSize/2;
                }
                else{
                    xCord = SnakeLadder.tileSize*SnakeLadder.height - (j*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                }
                // y coordinates
                int yCord = SnakeLadder.tileSize*SnakeLadder.height - (i*SnakeLadder.tileSize) - SnakeLadder.tileSize/2;
                positionCoordinates.add(new Pair<>(xCord,yCord));
            }
        }
    }

    // this method is to bring snake and ladder effect to game/ board
    private  void populateSnake_Ladder(){
        snakeLadderPosition = new ArrayList<>();
        // first lets add all the number to its indices
        for (int i = 0; i <101 ; i++) {
            snakeLadderPosition.add(i);
        }
        // now we just to need to map snake and ladder,,, that we will do manually
        // Ladders
        snakeLadderPosition.set(4,25);
        snakeLadderPosition.set(13,46);
        snakeLadderPosition.set(33,49);
        snakeLadderPosition.set(42,63);
        snakeLadderPosition.set(50,69);
        snakeLadderPosition.set(62,81);
        snakeLadderPosition.set(74,92);
        // snakes
        snakeLadderPosition.set(27,5);
        snakeLadderPosition.set(40,3);
        snakeLadderPosition.set(43,18);
        snakeLadderPosition.set(54,31);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(76,58);
        snakeLadderPosition.set(89,53);
        snakeLadderPosition.set(99,41);

    }

    public int getNewPosition(int currentPosition){
        if(currentPosition >0 && currentPosition <= 100 ){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }


    /* i have written these two getter methods to get the position of x and y coordinates,,
       so i can move them,, in player class*/
    int getXCoordinate(int position){
        if(position >= 1 && position <= 100){
            return  positionCoordinates.get(position).getKey();
        }
        return -1;
    }

    int getYCoordinate(int position){
        if(position >= 1 && position <= 100){
            return  positionCoordinates.get(position).getValue();
        }
        return -1;
    }

}
