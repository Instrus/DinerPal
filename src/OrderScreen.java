import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//The servers order screen.
public class OrderScreen
{
    Stage window = new Stage();

    //Object used in this class:
    EntreeItems entreeObject = new EntreeItems();

    static VBox orderView = new VBox();

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

        //Menu Tabs:
        Label menuTabLabel = new Label("Menu Navigation"); //menuTabLabel
        HBox menuTabsRow1 = new HBox(entrees, sides, appetizers); //row 1
        HBox menuTabsRow2 = new HBox(cocktails, wine, shots); //row 2
        VBox menuTabsCol = new VBox(menuTabsRow1, menuTabsRow2); //rows combined
        VBox menuTabsAndLabel = new VBox(10, menuTabLabel, menuTabsCol);
        menuTabsAndLabel.setAlignment(Pos.TOP_CENTER); //column alignment (Think this is main position)
        BorderPane menuTabHolder = new BorderPane(); //menuTabHolder (BorderPane)
        menuTabHolder.setPadding(new Insets(100,100,100,100)); //padding
        menuTabHolder.setCenter(menuTabsAndLabel); //Sets in middle of BorderPane

        //Main BorderPane set / padding
        //OrderView:
        Label orderViewLabel = new Label("Orders:");
        VBox orderViewHolder = new VBox(10, orderViewLabel, orderView);
        orderViewHolder.setPadding(new Insets(100,100,100,100));
        screen.setLeft(orderViewHolder); //orderView
        screen.setRight(menuTabHolder); //menuTabHolder
        screen.setBottom(back); //back button
        //scene
        Scene scene = new Scene(screen, 1000, 750);
        window.setScene(scene);
        window.show();
    }
}

