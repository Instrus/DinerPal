import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.LinkedList;

public class EditOrder
{
    Stage window = new Stage();
    static VBox orderView = new VBox(5); //orderView - store buttons (visually)
    LinkedList<ButtonNode> menuItemButtons = new LinkedList<>(); //List of ButtonNodes
    ToggleGroup tGroup = new ToggleGroup();

    //Calls for button creation
    public void createOrderView(Table reference)
    {
        int size = reference.orders.size();
        for(int i = 0; i < size; i++)
            createButtons(reference.orders.get(i).item, i, reference);
    }

    //Creates buttons
    public void createButtons(String name, int index, Table reference)
    {
        ButtonNode ob = new ButtonNode(); //object
        ToggleButton newButton = new ToggleButton(name); //create the button
        newButton.setMinSize(150, 30); //size of button
        newButton.setOnAction(event -> handleEvent(newButton, reference) ); //button action
        tGroup.getToggles().add(newButton); //add to ToggleGroup
        ob.Node(newButton, index); //Create node
        menuItemButtons.add(ob); //add to Button list
        orderView.getChildren().add(newButton); //add to orderView
    }

    //clearOrderView: clears orderView
    public void clearOrderView()
    {
        orderView.getChildren().clear();
    }


    public void editOrder(Table reference)
    {
        //Objects used:
        OrderScreen orderOb = new OrderScreen();

        createOrderView(reference);

        //BUTTONS::

        //BACK Button
        Button back = new Button("Back");
        back.setOnAction(event ->
        {
            window.close();
            clearOrderView();
            orderOb.seeOrderScreen(reference);
        });

        //Clear Button (removes all)
        Button clear = new Button("Clear");
        clear.setOnAction(event ->
        {
            boolean answer = ConfirmBox.display("Clear whole order?");
            if(answer == true)
            {
                window.close();
                clearOrderView();
                reference.orders.clear();
                createOrderView(reference);
                window.show();
            }
        });


        //H/VBoxes
        HBox backAndClear = new HBox(100, back, clear);
        //ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(orderView);
        scrollPane.setPadding(new Insets(10,0,10,0));
        //LAYOUT
        BorderPane screen = new BorderPane();
        //Sets
        screen.setPadding(new Insets(20,80,20,80));
        screen.setCenter(scrollPane);
        screen.setBottom(backAndClear);
        //Alignments
        screen.setAlignment(orderView, Pos.CENTER);
        screen.setAlignment(backAndClear, Pos.BOTTOM_CENTER);
        //scene
        Scene scene = new Scene(screen, 350, 550);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.show();
    }


    //Handles button click
    public void handleEvent(ToggleButton button, Table reference)
    {
        boolean answer = ConfirmBox.display("Remove item?");
        if(answer == true)
        {
            window.close();
            for(int i = 0; i < menuItemButtons.size(); i++)
            {
                if(button.equals(menuItemButtons.get(i).tButton))
                {
                    //System.out.println("index = " + menuItemButtons.get(i).index); (Keep until after testing) displays index of button
                    reference.orders.remove(i); //remove item order (at index)
                    reference.notes.remove(i); //remove note here (at index)
                }
            }
            menuItemButtons.clear(); //clears because we need to make a new one...
            clearOrderView(); //clear order view
            createOrderView(reference); //make new buttons
            window.show();
        }
    }

}
