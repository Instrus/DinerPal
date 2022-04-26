import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

//The servers order screen.
public class OrderScreen
{
    Stage window = new Stage();

    //Object used in this class:
    EntreeItems entreeObject = new EntreeItems();

    static VBox orderView = new VBox(5);

    //updates order
    public void updateOrder(Table reference)
    { entreeObject.seeEntrees(reference); }

    //displays order (creates buttons)
    public void orderView(Table reference)
    {
        for(int i = 0; i < reference.orders.size(); i++){
            String item = reference.orders.get(i).item;
            Button newItem = new Button(item);
            newItem.setMinSize(150, 30);
            orderView.getChildren().add(newItem);
        }
    }

    //displays menu
    public void seeMenu(Table reference)
    {

        Server serverObject = new Server();
        orderView(reference); //updates orderView.

        Button entrees = new Button("Entrees");
        entrees.setMinSize(120,50);
        entrees.setOnAction(e -> {
            window.close();
            //maybe clear when click on entree.
            //then update the orderView again after getting the order
            orderView.getChildren().clear(); //clear orderView when we go to entrees.
            updateOrder(reference);
            //orderView(reference); //here (THIS WAS THE ISSUE BUT KEEP FOR NOW)
        });

        //MENU TABS---------------------------------------------------------
        Button sides = new Button("Sides"); //sides
        sides.setMinSize(120,50);
        sides.setOnAction(event -> Notification.display("Coming soon") );
        Button appetizers = new Button("Appetizers"); //appetizers
        appetizers.setMinSize(120,50);
        appetizers.setOnAction(event -> Notification.display("Coming soon") );
        Button cocktails = new Button("Cocktails");//cocktails
        cocktails.setMinSize(120,50);
        cocktails.setOnAction(event -> Notification.display("Coming soon"));
        Button wine = new Button("Wine");//wine
        wine.setMinSize(120,50);
        wine.setOnAction(event -> Notification.display("Coming soon") );
        Button shots = new Button("Shots");//shots
        shots.setMinSize(120,50);
        shots.setOnAction(event -> Notification.display("Coming soon"));
        //MENU TABS---------------------------------------------------------

        //BACK - returns to tableChooser().
        Button back = new Button("Back");
        back.setOnAction(event -> {
            //clear orderView (when back is hit)
            orderView.getChildren().clear();
            window.close();
            serverObject.tableChooser(); //needs to go back to base order screen.
        });

        //layout
        BorderPane screen = new BorderPane();
        //screen.setPadding(new Insets(100, 100, 100, 100));


        //new close table button
        //current and only known issue: tables order isn't cleared with the clearing of the order.
        Button closeTable = new Button("Close table");
        closeTable.setOnAction(event -> {
            //I'll need to get the index the table is at.
            //when I find the index, however, i can make a new table there (null value)
            for(int i = 0; i < Server.linkedTables.size(); i++){
                if( reference == Server.linkedTables.get(i) ){
                    System.out.println("Removed"); //keep for testing
                    reference.orders.clear();
                    reference.numOfGuests = 0;
                    orderView.getChildren().clear();
                    serverObject.tableChooser();
                    window.close();
                    return;
                }
            }
        });

        //Menu Tabs:
        Label menuTabLabel = new Label("Menu Navigation"); //menuTabLabel
        HBox menuTabsRow1 = new HBox(5, entrees, sides, appetizers); //row 1
        HBox menuTabsRow2 = new HBox(5, cocktails, wine, shots); //row 2
        VBox menuTabsCol = new VBox(5, menuTabsRow1, menuTabsRow2); //rows combined
        VBox menuTabsAndLabel = new VBox(10, menuTabLabel, menuTabsCol);
        menuTabsAndLabel.setAlignment(Pos.TOP_CENTER); //column alignment (Think this is main position)
        BorderPane menuTabHolder = new BorderPane(); //menuTabHolder (BorderPane)
        menuTabHolder.setPadding(new Insets(100,100,100,100)); //padding
        menuTabHolder.setCenter(menuTabsAndLabel); //Sets in middle of BorderPane

        //need a send order button

        //need to pair up send order and close table (less distance apart)

        //then pair up back with (closer table + send order) (more distance)

        //adjust later.
        HBox backAndRemove = new HBox(400, back, closeTable);
        backAndRemove.setPadding(new Insets(0,100,10,100));

        //Main BorderPane set / padding
        //OrderView:
        Label screenLabel = new Label("Order Screen");
        screenLabel.getStyleClass().add("title-label");
        Line line = new Line(0, 0, 1000, 0);
        line.setStrokeWidth(2.0);
        VBox title = new VBox(10, screenLabel, line);
        title.setAlignment(Pos.CENTER);

        Label orderViewLabel = new Label("Orders:");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(orderView);

        VBox orderViewHolder = new VBox(10, orderViewLabel, scrollPane);
        orderViewHolder.setPadding(new Insets(100,0,100,100));

        //sets
        screen.setTop(title);
        screen.setLeft(orderViewHolder); //orderView (WAS ORIGINALLY orderViewHolder)
        screen.setRight(menuTabHolder); //menuTabHolder
        screen.setBottom(backAndRemove); //back button

        screen.setPadding(new Insets(30,0,30,0));

        //scene
        Scene scene = new Scene(screen, 1000, 750);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.show();
    }
}

