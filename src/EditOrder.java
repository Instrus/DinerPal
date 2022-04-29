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
    static VBox orderView = new VBox(5); //orderView (VBox)
    ToggleGroup tGroup = new ToggleGroup();
    LinkedList<ButtonNode> menuItemButtons = new LinkedList<>(); //List of ButtonNodes

    //calls for buttons to be made basically.
    public void createOrderView(Table reference){
        int size = reference.orders.size();

        for(int i = 0; i < size; i++)
            newButton(reference.orders.get(i).item, i, reference); //need name and index.
    }

    //actually makes the new button
    public void newButton(String name, int index, Table reference){
        ButtonNode ob = new ButtonNode(); //object

        ToggleButton newButton = new ToggleButton(name);
        newButton.setMinSize(150, 30); //size of button
        newButton.setOnAction(event -> handleEvent(newButton, reference) );
        tGroup.getToggles().add(newButton);

        ob.Node(newButton, index); //adding (button + index) to employees linkedList.

        menuItemButtons.add(ob);
        orderView.getChildren().add(newButton);
    }

        //clears orderView
    public void clearOrderView()
    { orderView.getChildren().clear(); }


    public void editOrder(Table reference)
    {
        //Objects used:
        OrderScreen orderOb = new OrderScreen();

        createOrderView(reference);

        //BUTTONS
        //back
        Button back = new Button("Back");
        back.setOnAction(event ->
        {
            window.close();
            clearOrderView();
            orderOb.seeMenu(reference);
        });

        //clear option (removes all)
        Button clear = new Button("Clear");
        clear.setOnAction(event ->
        {
            boolean answer = false;
            answer = ConfirmBox.display("Clear whole order?");
            if(answer == true) {
                window.close();
                clearOrderView();
                reference.orders.clear();
                createOrderView(reference);
                window.show();
            }
        });

        HBox backAndClear = new HBox(100, back, clear);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(orderView);
        scrollPane.setPadding(new Insets(10,0,10,0));

        //layout
        BorderPane screen = new BorderPane();
        screen.setPadding(new Insets(20,80,20,80));
        screen.setCenter(scrollPane);
        screen.setBottom(backAndClear);

        //alignments
        screen.setAlignment(orderView, Pos.CENTER);
        screen.setAlignment(backAndClear, Pos.BOTTOM_CENTER);
        //scene
        Scene scene = new Scene(screen, 350, 550);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.show();
    }

    //Handles button click
    public void handleEvent(ToggleButton button, Table reference){

        boolean answer = false;
        answer = ConfirmBox.display("Remove item?");
        if(answer == true)
        {
            window.close();
            for(int i = 0; i < menuItemButtons.size(); i++){
                if(button.equals(menuItemButtons.get(i).tButton)){
                    System.out.println("index = " + menuItemButtons.get(i).index);
                    reference.orders.remove(i);
                }
            }
            menuItemButtons.clear(); //clears a because we need to make a new one...
            clearOrderView(); //clear order view
            createOrderView(reference);// now make a new order
            window.show();
        }
    }

}
