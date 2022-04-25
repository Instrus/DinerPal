import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;

public class EntreeItems
{
    Stage window = new Stage();

    //Menu items
    static ItemAndPrice burger = new ItemAndPrice("Cheeseburger", 9.99);
    static ItemAndPrice spaghetti = new ItemAndPrice("Spaghetti", 12.99);
    static ItemAndPrice chili = new ItemAndPrice("Chili", 11.99);
    static ItemAndPrice salad = new ItemAndPrice("Grilled Chicken Salad", 12.99);

    public void seeEntrees(Table reference)
    {

        //Object used in this class:
        Server serverObject = new Server();
        OrderScreen orderScreenOb = new OrderScreen();

        //should keep this because I need to be able to submit or cancel.
        LinkedList<ItemAndPrice> orderWithPrice = new LinkedList<ItemAndPrice>();

        //Buttons for menu items.--------------------------------------------------
        Button burgerButton = new Button("Cheeseburger\t$9.99"); //burger
        burgerButton.setOnAction(e -> {
            Boolean add = ConfirmBox.display("Add " + burger.item + "?");
            if (add == true)
                orderWithPrice.add(burger);
        });

        Button spaghettiButton = new Button("Spaghetti\t$12.99");//spaghetti
        spaghettiButton.setOnAction(e -> {
            Boolean add = ConfirmBox.display("Add " + spaghetti.item + "?");
            if (add == true)
                orderWithPrice.add(spaghetti);
        } );

        Button chiliButton = new Button("Chili\t$11.99"); //chili
        chiliButton.setOnAction(e -> {
            Boolean add = ConfirmBox.display("Add " + chili.item + "?");
            if (add == true)
                orderWithPrice.add(chili);
        });

        Button saladButton = new Button("Grilled Chicken Salad\t12.99");//salad
        saladButton.setOnAction(e -> {
            Boolean add = ConfirmBox.display("Add " + salad.item + "?");
            if (add == true)
                orderWithPrice.add(salad);
        } ); //----------------------------------------------------------------------


        //SUBMIT - Finalizes order from selection and adds to tables order
        Button submit = new Button("Submit");
        submit.setOnAction(e -> {
            for(int i = 0; i < orderWithPrice.size(); i++){
                reference.orders.add(orderWithPrice.get(i));
            }
            window.close();
            orderScreenOb.seeMenu(reference);
        } );


        //Layout
        BorderPane screen = new BorderPane();
        VBox menuItems = new VBox(10, burgerButton, spaghettiButton, saladButton, chiliButton);
        //Set / Padding
        screen.setCenter(menuItems);
        screen.setBottom(submit);
        screen.setPadding(new Insets(20,60,20,60));
        //Scene
        Scene scene = new Scene(screen, 350, 550);
        window.setScene(scene);
        window.show();
    }

}
