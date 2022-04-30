import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//a yes or no box. returns value.
public class ConfirmBox {

    static boolean answer;

    public static boolean display(String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //blocks user from leaving window until dealt with
        window.setOnCloseRequest(Event::consume);

        Label label = new Label(message);

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox layout = new HBox(50);
        layout.getChildren().addAll(yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        VBox confirm = new VBox(10, label, layout);
        confirm.setAlignment(Pos.CENTER);

        Scene scene = new Scene(confirm, 300, 130);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;

    }


}
