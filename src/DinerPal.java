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
    static LinkedList<Integer> IDs = new LinkedList<Integer>();
    int ID = 0;

    public static void main(String[] args)
    { launch(args); }

    @Override
    public void start(Stage window)
    {
        //Server option.
        Button server = new Button("Server");
        server.setMinSize(200,60);

        server.setOnAction (e -> {
            int ID = GetNumber.display("Please enter employee ID:");
            if(ID > 0)
                IDs.add(ID);
            if(IDs.size() > 0) {
                window.close();
                serverObject.tableChooser();
            }
        });

        //Layout
        //Title
        Label DinerPalLabel = new Label("DinerPal");
        Line line = new Line(0, 0, 800, 0);
        VBox title = new VBox(DinerPalLabel, line);
        title.setAlignment(Pos.CENTER);
        //ChooseServerBar
        HBox options = new HBox(50, server);
        options.setAlignment(Pos.CENTER);
        //Main BorderPane
        BorderPane mainBP = new BorderPane();
        mainBP.setTop(title); //top
        mainBP.setCenter(options); //center
        mainBP.setPadding(new Insets(30,30,30,30));
        //scene
        Scene selectionScreen = new Scene(mainBP, 900, 500);
        window.setScene(selectionScreen);
        window.show();
    }
}