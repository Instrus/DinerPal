import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.LinkedList;

//The servers order screen.
public class OrderScreen
{
    Stage window = new Stage();

    //Object(s) used in this class:
    Entrees entreeObject = new Entrees();

    LinkedList<ButtonNode> menuItemButtons = new LinkedList<>();

    static VBox orderView = new VBox(5);
    static double total = 0.00; //need a decimal format for total
    static Button totalButton = new Button();
    ToggleGroup tGroup = new ToggleGroup();

    //UPDATE ORDER() updates order
    public void updateOrder(Table reference)
    {
        entreeObject.seeEntrees(reference);
    }

    //CREATEBUTTONS() goes through the cycle of creating buttons
    public void createButtons(Table reference)
    {
        int size = reference.orders.size(); //size = tables orders size.
        for(int i = 0; i < size; i++)
        {
            String menuItemName = reference.orders.get(i).item;
            newButton(menuItemName, i, reference);
        }
    }

    //NEWBUTTON() actually makes the new button
    public void newButton(String menuItem, int index, Table reference)
    {
        ButtonNode ob = new ButtonNode(); //object
        ToggleButton newButton = new ToggleButton(String.valueOf(menuItem));
        newButton.setMinSize(200, 30);

        newButton.setOnAction(event -> handleEvent(newButton, reference) ); //Working on**************
        tGroup.getToggles().add(newButton); //add to toggle group

        ob.Node(newButton, index); //create new Node
        menuItemButtons.add(ob); //add to menuItems linked List.
        orderView.getChildren().add(newButton); //adds to orderView
    }

    //CLEAR ORDER() clears orderView
    public void clearOrderView()
    {
        orderView.getChildren().clear();
    }

    //UPDATE PRICE() updates price view
    public void updatePriceView(Table reference)
    {
        for(int i = 0; i < reference.orders.size(); i++)
            total += reference.orders.get(i).price;

        String format = String.valueOf(total);
        format = format.format("%.2f", total);
        String totalString = ("Total: " + format );

        totalButton.setText(totalString);
        totalButton.setMinSize(130,50);
    }

    //CLEAR TOTAL - clears total
    public void clearTotal()
    {
        total = 0.00;
    }

    //SeeMenu - displays order screen
    public void seeOrderScreen(Table reference)
    {

        Server serverObject = new Server();
        createButtons(reference); //updates orderView.
        updatePriceView(reference);
        clearTotal();

        //ENTREES button
        Button entrees = new Button("Entrees");
        entrees.setMinSize(120,50);
        entrees.setOnAction(e -> {
            window.close();
            clearOrderView();
            updateOrder(reference);
        });

        //MENU TABS
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

        //BACK BUTTON - returns to tableChooser().
        Button home = new Button("Home");
        home.setMinSize(130,50);
        home.setOnAction(event ->
        {
            clearOrderView();
            window.close();
            serverObject.home(); //needs to go back to base order screen.
        });

        //Edits order - allows removal of items.
        Button editOrder = new Button("Remove Items");
        editOrder.setMinSize(130,50);
        editOrder.setOnAction(event ->
        {
            clearOrderView();
            EditOrder editOrderOb = new EditOrder();
            editOrderOb.editOrder(reference);
            window.close();
        });

        //CLOSE CHECK BUTTON
        Button closeCheck = new Button("Close Check");
        closeCheck.setMinSize(130,50);
        closeCheck.setOnAction(event ->
        {
            boolean answer = ConfirmBox.display("Close Check?");
            if(answer == true)
            {
                for(int i = 0; i < Server.linkedTables.size(); i++)
                {
                    if (reference == Server.linkedTables.get(i))
                    {
                        reference.employeeID = -1;
                        reference.orders.clear(); //clear orders
                        reference.numOfGuests = 0; //set guests back to 0
                        clearOrderView(); //clear orderView
                        serverObject.home(); //return home
                        window.close();
                        return;
                    }
                }
            }
        });

        //SEND ORDER BUTTON
        Button sendOrder = new Button("Send Order");
        sendOrder.setMinSize(130,50);
        sendOrder.setOnAction(event ->
        {
            Notification.display("Order sent to kitchen");
        });


        //Title
        Label screenLabel = new Label("Order Screen");
        screenLabel.getStyleClass().add("title-label");
        Line line = new Line(0, 0, 1000, 0);
        line.setStrokeWidth(2.0);
        VBox title = new VBox(10, screenLabel, line);
        title.setAlignment(Pos.CENTER);

        //LAYOUT
        BorderPane screen = new BorderPane();

        //Menu Tabs:
        Label menuTabLabel = new Label("Menu Navigation"); //menuTabLabel
        HBox menuTabsRow1 = new HBox(5, entrees, sides, appetizers); //row 1
        HBox menuTabsRow2 = new HBox(5, cocktails, wine, shots); //row 2
        VBox menuTabsCol = new VBox(5, menuTabsRow1, menuTabsRow2); //rows combined
        VBox menuTabsAndLabel = new VBox(10, menuTabLabel, menuTabsCol);
        menuTabsAndLabel.setAlignment(Pos.TOP_CENTER); //column alignment (Think this is main position)
        BorderPane menuTabHolder = new BorderPane(); //menuTabHolder (BorderPane)
        menuTabHolder.setPadding(new Insets(100,70,100,70)); //padding
        menuTabHolder.setCenter(menuTabsAndLabel); //Sets in middle of BorderPane

        //Order view
        Label orderViewLabel = new Label("Orders:");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(orderView);

        //H/VBOxes
        VBox totalAndBack = new VBox(10, totalButton, home);
        HBox options = new HBox(120, totalAndBack, editOrder, closeCheck, sendOrder);
        VBox orderViewHolder = new VBox(10, orderViewLabel, scrollPane);

        //sets
        screen.setTop(title);
        screen.setLeft(orderViewHolder); //orderView (WAS ORIGINALLY orderViewHolder)
        screen.setRight(menuTabHolder); //menuTabHolder
        screen.setBottom(options); //back button
        //padding
        orderViewHolder.setPadding(new Insets(70,0,100,70));
        options.setPadding(new Insets(0,70,10,70));
        screen.setPadding(new Insets(30,0,30,0));

        //scene
        Scene scene = new Scene(screen, 1000, 750);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.show();
    }

    //Handles button click
    public void handleEvent(ToggleButton button, Table ref )
    {
        CustomizeItem ob = new CustomizeItem();

        for(int i = 0; i < menuItemButtons.size(); i++)
        {
            if( button.equals(menuItemButtons.get(i).tButton) )
            {
                System.out.println("Index = " + i);
                System.out.println("Note: " + ref.notes.get(i));
                ob.makeNote(ref, i); //reference, index
            }
        }

    }

}

