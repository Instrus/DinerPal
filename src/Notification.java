import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Displays a simple notification
public class Notification {

    public static void display(String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        Button submit = new Button("Ok");
        submit.setOnAction (e -> window.close());

        //LAYOUT ::

        //Labels
        Label infoLabel = new Label(message);
        //V/HBoxes
        VBox options = new VBox(20, infoLabel,submit);
        //Main screen
        BorderPane screen = new BorderPane();
        //Sets
        screen.setCenter(options);
        screen.setPadding(new Insets(20,40,20,40));
        //Alignment/Padding
        options.setAlignment(Pos.CENTER);
        //scene
        Scene scene = new Scene(screen, 400, 150);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.showAndWait();
    }

}
