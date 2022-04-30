import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.LinkedList;

public class DinerPal extends Application
{
    //Object used in this class:
    Server serverObject = new Server();

    //LinkedList that will hold all sign-ins.
    static LinkedList<Integer> IDs = new LinkedList<>();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage window)
    {
        //Server option.
        Button server = new Button("Server");
        server.setMinSize(200,60);

        server.setOnAction (e -> {
                window.close();
                serverObject.home();
        });

        //LAYOUT::

        //Title
        Label dinerPalLabel = new Label("DinerPal");
        dinerPalLabel.getStyleClass().add("title-label");
        Line line = new Line(0, 0, 900, 0); line.setStrokeWidth(2.0);
        //V/HBoxes
        VBox title = new VBox(10, dinerPalLabel, line);
        HBox options = new HBox(50, server);
        //Main screen
        BorderPane screen = new BorderPane();
        //Sets
        screen.setTop(title); //top
        screen.setCenter(options); //center
        screen.setPadding(new Insets(30,0,30,0));
        //Alighment/Padding
        title.setAlignment(Pos.CENTER);
        options.setAlignment(Pos.CENTER);
        //scene
        Scene startScreen = new Scene(screen, 900, 500);
        startScreen.getStylesheets().add("custom.css");
        window.setScene(startScreen);
        window.show();
    }
}