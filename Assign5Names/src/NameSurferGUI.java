import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class NameSurferGUI extends Application {

    /*Panes*/
    private VBox root = new VBox(); //The root that holds each pane
    private HBox userPane = new HBox(); //Holds the text field and buttons

    /*Text Boxes*/
    private TextField searchText = new TextField(); //The name that is being searched

    /*Buttons*/
    private Button searchButton = new Button("Search"); //Searches for a name
    private Button clearButton = new Button("Clear"); //Clears the graph

    /*Gramphics*/
    private Canvas canvas = new Canvas(); //What is being drawn onto
    private GraphicsContext g = canvas.getGraphicsContext2D();

    private int canvasHeight = 600; //The height of the canvas
    private int canvasWidth = 800; //The width of the canvas


    private double sidePadding = 50; //The padding between the left-most line and the left-most side of the canvas
    private double hLinePadding = 50; //The padding between the bottom of the canvas, and the line that dissects the key
    private double vLinePadding = ( canvasWidth - (sidePadding/2) ) / 12; //The padding between each vertical line

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Name Surfer"); //Title of the window

        primaryStage.setScene(new Scene(root));

        root.getChildren().addAll(canvas, userPane);
        userPane.getChildren().addAll(searchText, searchButton, clearButton);

        canvas.setHeight(canvasHeight);
        canvas.setWidth(canvasWidth);


        DrawGrid(); //Draw the grid

        searchButton.setOnAction(event -> { //When searched, draw a graph of the name rankings
            DrawGraph();
        });

        clearButton.setOnAction(event -> { //Clear the graph
            ClearGraph();
        });

        primaryStage.show(); //Show the window

    }

    private void DrawGrid() {

        g.setStroke(Paint.valueOf("BLACK"));
        g.strokeLine(0, canvas.getHeight()-hLinePadding, canvas.getWidth(), canvas.getHeight()-hLinePadding); //Draw a line along the bottom

        for(int i = 0; i < 12; i++) { //For each year

            double x = sidePadding + vLinePadding * i;
            double yBottom = canvas.getHeight() - (hLinePadding/1.5);
            g.strokeLine(x, yBottom, x, 0); //Draw a line from the bottom to the top

            int decade = 1900 + 10 * i;
            g.fillText(Integer.toString(decade), x - 12, yBottom + 20); //And print the decade below it
        }


    }

    private void DrawGraph() {

        ClearGraph(); //First clear the graph

        try {
            NameRecord nameRecordObj = new NameRecord(searchText.getText());
            g.setStroke(Paint.valueOf("BLUE"));

            int errors = 0;
            for(int i = 0; i < 12; i++) //For each decade
                if(nameRecordObj.getRank(i) >= 1000 || nameRecordObj.getRank(i) <= 0 ) //Make sure each each ranking is between 0 and 1000
                    errors++;

            if(errors == 12) throw new RuntimeException(); //If there are 12 errors, throw an exception

            double lastX = 0;
            double lastY = 0;

            for(int i = 0; i < 12; i++) {

                int currentRanking = nameRecordObj.getRank(i); //The name's ranking during the currently indexed year
                double x = sidePadding + vLinePadding * i;
                double y = currentRanking * (canvas.getHeight() -50) / 1000;
                if(i != 0) //If the ranking is not 0
                    g.strokeLine(lastX, lastY, x, y); //Draw a line

                if(currentRanking < 1000 && currentRanking > 0) { //If the ranking is between 0 and 1000
                    g.fillText(Integer.toString(currentRanking), x, y<10?y+10:y); //Write the ranking
                    lastX = x;
                    lastY = y;
                }
                else {
                    g.fillText("N/A", x, y); //Else, write N/A
                    lastX = sidePadding + vLinePadding * i;
                    lastY = canvas.getHeight() - hLinePadding;
                }
            }
        }
        catch (RuntimeException e) {
            ClearGraph(); //Clear the graph, but don't write to the console
        }
    }

    private void ClearGraph() {
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); //Erase the entire canvas
        DrawGrid(); //And redraw the grid
    }


}
