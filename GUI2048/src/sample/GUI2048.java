package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GUI2048 extends Application {

    private int gridWidth = Play2048.getGridWidth();
    private int gridHeight = Play2048.getGridHeight();

    private TextField[][] tile = new TextField[gridHeight][gridWidth];

    private GridPane numberGrid = new GridPane();
    private HBox scoreHBox = new HBox();
    private HBox buttonsHBox = new HBox();
    private VBox root = new VBox();

    private Button buttonLeft = new Button("Left");
    private Button buttonRight = new Button("Right");
    private Button buttonUp = new Button("Up");
    private Button buttonDown = new Button("Down");

    private Label labelCurrentScoreText = new Label("Score: ");
    private Label labelCurrentScore = new Label("0");

    private Label labelWinLossMessage = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("2048");

        primaryStage.setScene(new Scene(root,250,300));
        primaryStage.setMinWidth(250);
        primaryStage.setMinHeight(300);

        root.getChildren().addAll(scoreHBox, labelWinLossMessage, numberGrid, buttonsHBox);
        buttonsHBox.getChildren().addAll(buttonLeft, buttonRight, buttonUp, buttonDown);
        scoreHBox.getChildren().addAll(labelCurrentScoreText, labelCurrentScore);

        numberGrid.setAlignment(Pos.CENTER);
        scoreHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setAlignment(Pos.CENTER);

        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){

                tile[j][i] = new TextField();

                tile[j][i].setAlignment(Pos.CENTER);
                tile[j][i].setMinSize(50,50);
                tile[j][i].setPrefSize(50,50);
                tile[j][i].setMouseTransparent(true);
                tile[j][i].setFocusTraversable(false);
                tile[j][i].setEditable(false);

                numberGrid.getChildren().addAll(tile[j][i]);
                GridPane.setConstraints(tile[j][i], j, i);



            }

        }

        buttonLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveLeft();
                Play2048.placePiece();
                refreshBoard();
            }
        });

        buttonRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveRight();
                Play2048.placePiece();
                refreshBoard();
            }
        });

        buttonUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveUp();
                Play2048.placePiece();
                refreshBoard();
            }
        });

        buttonDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveDown();
                Play2048.placePiece();
                refreshBoard();
            }
        });

        Play2048.placePiece();
        Play2048.placePiece();

        refreshBoard();
        primaryStage.show();

    }

    private void refreshBoard(){

        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){

                if(Play2048.getGrid(i,j)!=0) { //If the value of the current square is not 0
                    tile[j][i].setText(String.valueOf(Play2048.getGrid(i, j))); //Put the number in the text box
                }
                else {
                    tile[j][i].setText("");
                }

                switch (Play2048.getGrid(i,j)){

                    case 0: tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf("6c7687").toString().substring(2)); break;
                    case 2: tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf("cbd0d8").toString().substring(2)); break;
                    case 4: tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf("e9f2a4").toString().substring(2)); break;
                    case 8: tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf("eacd96").toString().substring(2)); break;
                    case 16: tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf("f2c674").toString().substring(2)); break;
                    case 32: tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf("e57d49").toString().substring(2)); break;
                    case 64: tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf("d66b3e").toString().substring(2)); break;

                }

            }

        }

        labelCurrentScore.setText(String.valueOf(Play2048.getCurrentScore()));

        if(Play2048.getHighestNumber() == 2048)
            labelWinLossMessage.setText("You've reached 2048! You won!");

    }
}