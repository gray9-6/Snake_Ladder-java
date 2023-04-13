package com.example.snakeladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {
    public static final int tileSize = 40, width = 10, height = 10;
    public static int buttonLine = height*tileSize + 50, infoLine = buttonLine - 30;
    private static Dice dice = new Dice();
    private Player playerOne,playerTwo;
    private boolean gameStarted = false, playerOneTurn = false, playerTwoTurn = false;

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height * tileSize + 100);

        /*Loop which sets the tile on board i.e. on root*/
        for (int i = 0; i < height ; i++) {
            for (int j = 0; j < width; j++) {

                /*adding tile to our pane object i.e. root */
                Tile tile = new Tile(tileSize);
                /*by using setTranslate method we can choose where to place the tiles(or in general any components)*/
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

        /*we have created an object of image class in which we have stored the path of the image from our laptop */
        Image image = new Image("D:\\java project\\Minor Projects\\Intellije\\Snake-Ladder\\src\\main\\snakes_Ladders.jpg");
        /*we have created an object of image view to view the image*/
        ImageView board = new ImageView(image);
        /*and now setting the image*/
        board.setImage(image);
        /*now making the image to set as per our pane(i.e. root)*/
        board.setFitHeight(height*tileSize);
        board.setFitWidth(width*tileSize);

        /*Creating buttons*/
        Button playerOneButton = new Button("Player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");
        /*setting the button locations*/
        playerOneButton.setTranslateY(buttonLine);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);
        startButton.setTranslateX(170);

        /*Information Display*/
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");
        /*setting the labels location*/
        playerOneLabel.setTranslateY(infoLine);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoLine);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoLine);
        diceLabel.setTranslateX(155);

        /*Inititialized the players*/
        playerOne = new Player(tileSize, Color.BLACK,"Ajay");
        playerTwo = new Player(tileSize-5, Color.WHITE,"Amit");

        /*Player Action ( Making players moveable )*/
        /*on the click of button i wanted to move the player , i wanted to roll the dice so for that we have
          setOnAction function */
        // when we click on player one button

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(gameStarted){
                    if(playerOneTurn){
                          /*i want three things roll the dice,,, display the rolled dice value,,
                           move the player according to that rolled dice  value*/
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerOne.movePlayer(diceValue);
                        // after rolling the dice we need to check the winning condition
                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is " + playerOne.getName());

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText(" ");



                            startButton.setDisable(false);
                            startButton.setText("Restart the Game!!");
                            gameStarted = false;
                        }
                        else {
                            // player one have moved so now we don't want him to move again but now player 2 can move
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText(" ");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn! " + playerTwo.getName());
                        }
                    }
                }
            }
        });

        // When we click on player two button
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(playerTwoTurn){
                          /*i want three things roll the dice,,, display the rolled dice value,,
                           move the player according to that rolled dice  value*/
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : " + diceValue);
                        playerTwo.movePlayer(diceValue);
                        // after rolling the dice we need to check the winning condition
                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is " + playerTwo.getName());

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText(" ");

                            startButton.setDisable(false);
                            startButton.setText("Restart the Game!!");
                       //   gameStarted = false;
                        }
                        else{
                            // player two have moved so now we don't want him to move again but now player one can move
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn! " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText(" ");

                        }

                    }
                }
            }
        });


        /*writing event handler for start button*/
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                gameStarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn " + playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });

        /*to view the image we have to add the board object to root,,similarly for buttons,,adding them to see
         * basically we are adding all the components to our pane(i.e. root)*/
        root.getChildren().addAll(board,
                playerOneButton,playerTwoButton,startButton,
                playerOneLabel,playerTwoLabel,diceLabel,
                playerOne.getCoin(),playerTwo.getCoin()
        );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder !");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}