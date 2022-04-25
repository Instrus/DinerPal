import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//Gets a number from the user. Must be int.
public class GetNumber {

    static int num;

    public static int display(String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //cannot leave page but can x it out.

        Label label = new Label(message);

        //textField.
        TextField textField = new TextField();
        textField.setAlignment(Pos.CENTER);

        Button close = new Button("Ok");
        close.setOnAction (e -> {
            num = Integer.parseInt(textField.getText());
            window.close(); }
        );

        //LAYOUT
        VBox layout = new VBox(20, label, textField, close);
        layout.setAlignment(Pos.CENTER);

        //Main Border pane
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,10,10,10));
        bp.setCenter(layout); //center

        //scene
        Scene scene = new Scene(bp, 350, 150);
        window.setScene(scene);
        window.showAndWait();

        return num;
    }
}