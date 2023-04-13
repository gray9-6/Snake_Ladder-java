package com.example.snakeladder;

public class Dice {
    // here i am generating a random value and returning it
    public int getRolledDiceValue(){
        return (int)(Math.random()*6+1);
    }
}
