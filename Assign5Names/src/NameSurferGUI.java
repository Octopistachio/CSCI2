import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NameSurferGUI extends Application {

    private VBox root = new VBox(); //The root that holds each pane

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Names"); //Title of the window

        primaryStage.setScene(new Scene(root,250,300));
        primaryStage.setMinWidth(250); //Minimum width
        primaryStage.setMinHeight(300); //Minimum height

        primaryStage.show(); //Show the window

    }

}
