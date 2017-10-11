import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GUI2048 extends Application {

    private int gridWidth = Play2048.getGridWidth(); //Width of the grid holding the numbers
    private int gridHeight = Play2048.getGridHeight(); //Height of the grid holding the numbers

    private String emptyColourHex = "878787"; //Colour of an empty tile
    private String baseColourHex ="ffeeb2"; //The base colour of a filled tile. Will change as the numbers get bigger
    private int baseColourDec = Integer.parseInt(baseColourHex, 16); //The base color converted to integer form

    private TextField[][] tile = new TextField[gridHeight][gridWidth]; //The tiles that hold the numbers

    /*Panes*/
    private GridPane numberGrid = new GridPane(); //The grid that holds the numbers
    private HBox scoreHBox = new HBox(); //The box that holds the score
    private HBox buttonsHBox = new HBox(); //The box that holds the buttons
    private VBox root = new VBox(); //The root that holds each pane

    /*Buttons*/
    private Button buttonLeft = new Button("Left");
    private Button buttonRight = new Button("Right");
    private Button buttonUp = new Button("Up");
    private Button buttonDown = new Button("Down");

    /*Labels*/
    private Label labelCurrentScoreText = new Label("Score: "); //Message before the current score
    private Label labelCurrentScore = new Label("0"); //The player's current score
    private Label labelPlayerMessage = new Label(); //Inform on important info

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Insets rootInset = new Insets(10,10,10,10); //The padding for the root

        primaryStage.setTitle("2048"); //Title of the window

        primaryStage.setScene(new Scene(root,250,300));
        primaryStage.setMinWidth(250); //Minimum width
        primaryStage.setMinHeight(300); //Minimum height

        root.getChildren().addAll(scoreHBox, labelPlayerMessage, numberGrid, buttonsHBox); //Add all of the panes to the root
        root.setPadding(rootInset); //Set the root's padding
        buttonsHBox.setPadding(rootInset); //Set a padding to the button's box
        buttonsHBox.getChildren().addAll(buttonLeft, buttonRight, buttonUp, buttonDown); //Add all buttons to their respective pane
        scoreHBox.getChildren().addAll(labelCurrentScoreText, labelCurrentScore); //Add the labels to their respective pane

        //Set the alignment of each pane
        root.setAlignment(Pos.CENTER);
        numberGrid.setAlignment(Pos.CENTER);
        scoreHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setAlignment(Pos.CENTER);

        //Set the gap between grid tiles
        numberGrid.setHgap(0);
        numberGrid.setVgap(0);

        numberGrid.setBorder(Border.EMPTY); //Remove the grid border

        //For each tile in the grid
        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){

                tile[j][i] = new TextField(); //Create a text field object

                tile[j][i].setAlignment(Pos.CENTER); //Center it
                tile[j][i].setMinSize(50,50); //Set the minimum size
                tile[j][i].setPrefSize(50,50); //Set the minimum size to the preferred size
                tile[j][i].setMouseTransparent(true); //Set the mouse cursor to be transparent inside the textbox
                tile[j][i].setFocusTraversable(false); //Remove mouse focus
                tile[j][i].setEditable(false); //Don't allow editing

                numberGrid.getChildren().addAll(tile[j][i]); //Add the textbox to the grid
                GridPane.setConstraints(tile[j][i], j, i);

            }
        }


        buttonLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveLeft();
                StartNewTurn();
            }
        });

        buttonRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveRight();
                StartNewTurn();
            }
        });

        buttonUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveUp();
                StartNewTurn();
            }
        });

        buttonDown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Play2048.moveDown();
                StartNewTurn();
            }
        });

        //Place two pieces into the grid
        Play2048.placePiece();
        Play2048.placePiece();

        //Show the pieces on the board
        refreshBoard();

        //Start the window
        primaryStage.show();

    }

    /**
     * Take all of the values from the grid in Play2048
     * and put them into the GUI version of the grid
     */
    private void refreshBoard(){

        //For each tile
        for(int i = 0; i < gridHeight; i++){
            for(int j = 0; j < gridWidth; j++){

                if(Play2048.getGrid(i,j)!=0) { //If the value of the current square is not 0
                    tile[j][i].setText(String.valueOf(Play2048.getGrid(i, j))); //Put the number in the text box
                }
                else { //If the value of the current square IS 0
                    tile[j][i].setText(""); //Remove the text
                }

                if(Play2048.getGrid(i,j) == 0){ //If the current number is 0
                    tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf(emptyColourHex).toString().substring(2)); //Set the tile colour to gray
                }
                else if(Play2048.getGrid(i,j) == 2){ //If the current number is 2
                    tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf(baseColourHex).toString().substring(2)); //Set the tile colour to the base colour
                }
                else{
                    //Darken the tile colour
                    int altColourDec = baseColourDec - (Play2048.getGrid(i,j) * 1114112);
                    String altColourHex = Integer.toHexString(altColourDec);
                    tile[j][i].setStyle("-fx-control-inner-background: #"+ Paint.valueOf(altColourHex).toString().substring(2));

                }

            }

        }

        labelCurrentScore.setText(String.valueOf(Play2048.getCurrentScore())); //Update the score label

        if(Play2048.getHighestNumber() == 2048) //If any tile is 2048
            labelPlayerMessage.setText("You've reached 2048! You won!"); //Write a win message

    }

    /**
     * When a new turn starts place a new piece
     * and refresh the board
     */
    private void StartNewTurn(){

        //If the new grid and the old grid are not identical, place a new piece.
        //This forces the player to select a direction where the numbers will move
        if(!Play2048.DoArraysEqual(Play2048.getGrid(), Play2048.getPreviousGrid())) {
            Play2048.placePiece();
            labelPlayerMessage.setText("");
        }
        else {
            labelPlayerMessage.setText("Pick another direction!");
        }

        Play2048.MakeArraysEqual(Play2048.getGrid(), Play2048.getPreviousGrid()); //Set the arrays equal to eachother

        refreshBoard(); //Refresh the board



    }
}