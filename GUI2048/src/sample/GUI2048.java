package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI2048 extends Application {

    private int gridWidth = Play2048.getGridWidth();
    private int gridHeight = Play2048.getGridHeight();

    private TextField[][] tile = new TextField[gridHeight][gridWidth];
    private Play2048 objPlay2048 = new Play2048();

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Play2048.placePiece();
        Play2048.placePiece();

        primaryStage.setTitle("2048");




        primaryStage.setScene(new Scene(root, 300, 250));

        root.getChildren().addAll(scoreHBox, numberGrid, buttonsHBox);
        buttonsHBox.getChildren().addAll(buttonLeft, buttonRight, buttonUp, buttonDown);
        scoreHBox.getChildren().addAll(labelCurrentScoreText, labelCurrentScore);

        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){

                tile[i][j] = new TextField();

                numberGrid.getChildren().addAll(tile[i][j]);
                GridPane.setConstraints(tile[i][j], i, j);

                tile[i][j].setEditable(false);

            }

        }

        GridPane.setConstraints(labelCurrentScoreText, 0,6);
        GridPane.setConstraints(labelCurrentScore, 1,6);

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

        refreshBoard();
        primaryStage.show();

    }

    private void refreshBoard(){

        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){

                tile[j][i].setText(String.valueOf(Play2048.getGrid(i,j)));

            }

        }

        labelCurrentScore.setText(String.valueOf(Play2048.getCurrentScore()));

    }
}