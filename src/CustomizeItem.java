import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomizeItem
{
    Stage window = new Stage();

    public void makeNote(Table reference, int index)
    {
        //Textfield
        TextField textField = new TextField();

        //BUTTONS::
        Button overwrite = new Button("Overwrite");
        overwrite.setOnAction(event ->
        {
            if(ConfirmBox.display("Overwrite note?")) {
                reference.notes.set(index, textField.getText()); //changes note associated with order
            } else
                return;
            window.close();
        });

        Button cancel = new Button("Cancel");
        cancel.setOnAction(event ->
        {
            window.close();
        });

        //LAYOUT::

        //Labels
        Label screenLabel = new Label("Customize order"); //header
        Label noteLabel = new Label( "Note: " + reference.notes.get(index)); //Display current note
        noteLabel.setMinSize(200, 200);
        noteLabel.setWrapText(true);
        //VBox/HBoxes
        HBox options = new HBox(10, overwrite, cancel);
        VBox noteAndText = new VBox(10, noteLabel, textField);
        //Main Screen
        BorderPane screen = new BorderPane();
        //Sets
        screen.setTop(screenLabel);
        screen.setCenter(noteAndText);
        screen.setBottom(options);
        //Alignments/Padding
        screen.setPadding(new Insets(10,10,10,10));
        noteAndText.setPadding(new Insets(40,30,40,30));
        options.setAlignment(Pos.CENTER);
        screen.setAlignment(screenLabel, Pos.CENTER);
        //Scene
        Scene scene = new Scene(screen, 500, 400);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.showAndWait();
    }

}
