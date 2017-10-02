package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI2048 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Play2048 objPlay2048 = new Play2048();

        primaryStage.setTitle("2048");
        GridPane numberGrid = new GridPane();

        Button buttonLeft = new Button("Left");
        Button buttonRight = new Button("Right");
        Button buttonUp = new Button("Up");
        Button buttonDown = new Button("Down");

        primaryStage.setScene(new Scene(numberGrid, 300, 250));

        numberGrid.getChildren().addAll(buttonLeft, buttonRight, buttonUp, buttonDown);
        numberGrid.setConstraints(buttonLeft, 0,4);
        numberGrid.setConstraints(buttonRight, 1,4);
        numberGrid.setConstraints(buttonUp, 2,4);
        numberGrid.setConstraints(buttonDown, 3,4);


        buttonLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                objPlay2048.moveLeft();
            }
        });

        buttonRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                objPlay2048.moveRight();
            }
        });

        buttonUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                objPlay2048.moveUp();
            }
        });

        buttonDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                objPlay2048.moveDown();
            }
        });

        primaryStage.show();

    objPlay2048.placePiece();
    objPlay2048.placePiece();



    }
}