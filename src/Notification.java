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

        Label infoLabel = new Label(message);
        Button submit = new Button("Ok");
        submit.setOnAction (e -> window.close());

        //layout
        VBox layout = new VBox(20, infoLabel,submit);
        layout.setAlignment(Pos.CENTER);
        BorderPane bp = new BorderPane();
        bp.setCenter(layout);
        bp.setPadding(new Insets(20,40,20,40));
        //scene
        Scene scene = new Scene(bp, 400, 150);
        window.setScene(scene);
        window.showAndWait();
    }

}
