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

//Main method for Server.
public class Server
{
    Stage window = new Stage();

    static LinkedList<Table> linkedTables = new LinkedList<Table>();
    static Table tableOne = new Table(1);
    static Table tableTwo = new Table(2);
    static Table tableThree = new Table(3);
    static Table tableFour = new Table(4);
    static Table tableFive = new Table(5);
    static Table tableSix = new Table(6);
    static Table tableSeven = new Table(7);
    static Table tableEight = new Table(8);
    static Table tableNine = new Table(9);

    //Object used in this class:
    OrderScreen orderScreenObject = new OrderScreen();

    public void tableChooser()
    {
        //Tables
        Button table1 = new Button("Table 1"); //Table 1
        table1.setOnAction(e -> {
            handleEvent(tableOne, 1);
        });

        Button table2 = new Button("Table 2"); //Table 2
        table2.setOnAction(event -> {
            handleEvent(tableTwo, 2);
        });

        Button table3 = new Button("Table 3"); //Table 3
        table3.setOnAction(event -> {
            handleEvent(tableThree, 3);
        });

        Button table4 = new Button("Table 4"); //Table 4
        table4.setOnAction(event -> {
            handleEvent(tableFour, 4);
        });

        Button table5 = new Button("Table 5"); //Table 5
        table5.setOnAction(event -> {
            handleEvent(tableFive, 5);
        });

        Button table6 = new Button("Table 6"); //Table 6
        table6.setOnAction(event -> {
            handleEvent(tableSix, 6);
        });

        Button table7 = new Button("Table 7"); //Table 7
        table7.setOnAction(event -> {
            handleEvent(tableSeven, 7);
        });

        Button table8 = new Button("Table 8"); //Table 8
        table8.setOnAction(event -> {
            handleEvent(tableEight, 8);
        });

        Button table9 = new Button("Table 9"); //Table 9
        table9.setOnAction(event -> {
            handleEvent(tableNine, 9);
        });

        //OTHER BUTTONS:
        Button signIn= new Button("Sign in");
        signIn.setMinSize(140,70);
        signIn.setOnAction(event -> {
            handleSignIn();
        });

        Button signOut = new Button("Sign out");
        signOut.setMinSize(140, 70);

        VBox signBox = new VBox(5,signIn, signOut);
        signBox.setPadding(new Insets(200,0,0,0));

        //Styles:
        table1.getStyleClass().add("table-button");
        table2.getStyleClass().add("table-button");
        table3.getStyleClass().add("table-button");
        table4.getStyleClass().add("table-button");
        table5.getStyleClass().add("table-button");
        table6.getStyleClass().add("table-button");
        table7.getStyleClass().add("table-button");
        table8.getStyleClass().add("table-button");
        table9.getStyleClass().add("table-button");
        //table rows
        HBox tableRow1 = new HBox(80, table1, table2, table3);
        HBox tableRow2 = new HBox(80, table4, table5, table6);
        HBox tableRow3 = new HBox(80, table7, table8, table9);
        VBox tableColumns = new VBox(80, tableRow1, tableRow2, tableRow3);
        BorderPane tables = new BorderPane(tableColumns); //all tables combines into a BorderPane
        tables.setPadding(new Insets(120,100,100,100));
        //Layout
        BorderPane screen = new BorderPane();
        screen.setPadding(new Insets(30,0,30,0));
        //title
        Label screenLabel = new Label("DinerPal");
        screenLabel.getStyleClass().add("title-label");
        Line line = new Line(0, 0, 1000, 0);
        line.setStrokeWidth(2.0);
        VBox title = new VBox(10, screenLabel, line);
        title.setAlignment(Pos.CENTER);
        //sets
        screen.setTop(title);
        screen.setRight(tables);
        screen.setLeft(signBox);
        //scene
        Scene serverMain = new Scene(screen, 1000, 750);
        serverMain.getStylesheets().add("custom.css");
        window.setScene(serverMain);
        window.show();
    }

    //handles Table buttons
    public void handleEvent(Table reference, int tableNumber)
    {
        if (! (linkedTables.contains(reference)) )
            linkedTables.add(reference);

        //get ID here. Test if its present in DinerPal.IDs. If it is, allow passage, if not, deny.

        while(reference.numOfGuests < 1) {
            reference.numOfGuests = GetNumber.display("Enter number of guests");
            if(reference.numOfGuests < 1){
                Notification.display("Please enter a number greater than 0");
                return;
            }
        }

        //maybe I need a cancel button for this page.
        reference.listOrder();
        orderScreenObject.seeMenu(reference);
        window.close();
    }

    //handles Sign in button
    public void handleSignIn()
    {
        int ID = GetNumber.display("Enter employee ID");
        //For loop prevents duplicate ID
        for(int i = 0; i < DinerPal.IDs.size(); i++)
        {
            if(ID == DinerPal.IDs.get(i))
            {
                Notification.display("Already signed in");
                return;
            }
        }
            Notification.display("ID: " + ID + " signed in");
            DinerPal.IDs.add(ID);
    }

    //need a handleSignOut()


}
