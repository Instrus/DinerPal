import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;

public class EmployeePage
{


    static LinkedList<ButtonNode> employees = new LinkedList<>(); //List of ButtonNodes (button + index)
    static VBox employeeView = new VBox(5); //employeeView (Vbox)
    int ID = -1;

    //ToggleGroup (add all new buttons here)
    static ToggleGroup tGroup = new ToggleGroup();


    //goes through the cycle of creating buttons
    public void createButtons()
    {
        int size = DinerPal.IDs.size();

        for(int i = 0; i < size; i++) //creates however many buttons depending how many people clocked in.
        {
            int ID = DinerPal.IDs.get(i); //get ID (for button creation)
            newButton(ID, i);
        }
    }

    //actually makes the new button
    public void newButton(int ID, int index)
    {
        ButtonNode ob = new ButtonNode(); //object
        ToggleButton newButton = new ToggleButton(String.valueOf(ID));

        newButton.setOnAction(event -> handleEvent(newButton) );
        tGroup.getToggles().add(newButton);

        ob.Node(newButton, index, ID); //create new Node
        employees.add(ob);
        employeeView.getChildren().add(newButton);
    }

    public void clearEmployeeView()
    { employeeView.getChildren().clear(); }

    //SET ID
    public void setID(int ID)
    { this.ID = ID; }

    //GET ID
    public int getID()
    { return ID; }

    //main page
    public void page(){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL); //blocks user from leaving window until dealt with
        window.setOnCloseRequest(Event::consume); //prevents from closing out without hitting submit or close.

        //creates buttons for view
        createButtons();

        //SUBMIT BUTTON
        Button submit = new Button("Submit");
        submit.setOnAction(event -> {
            employees.clear();
            clearEmployeeView();
            window.close();
        });

        //CANCEL BUTTON
        Button cancel = new Button("Cancel");
        cancel.setOnAction(event -> {
            setID(-2);
            employees.clear();
            clearEmployeeView();
            window.close();
        });


        Label empLabel = new Label("Select your ID");
        Line line = new Line(0, 0, 350, 0);
        line.setStrokeWidth(2.0);
        VBox title = new VBox(10, empLabel, line);
        title.setAlignment(Pos.CENTER);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(employeeView);
        //scrollPane.setPadding(new Insets(40,0,40,0));
        //layout
        BorderPane screen = new BorderPane();
        screen.setPadding(new Insets(20,0,20,0));
        screen.setCenter(scrollPane);
        scrollPane.setPadding(new Insets(40, 100,40,100));
        screen.setTop(title);
        employeeView.setAlignment(Pos.TOP_CENTER);
        screen.setAlignment(employeeView, Pos.CENTER);

        HBox back_cancel = new HBox(10, submit, cancel); //testing toggle here
        back_cancel.setAlignment(Pos.CENTER);
        screen.setBottom(back_cancel);

        //scene
        Scene scene = new Scene(screen, 350, 550);
        scene.getStylesheets().add("custom.css");
        window.setScene(scene);
        window.showAndWait(); //THIS IS SUPER HELPFUL WHEN NEEDING TO KEEP A WINDOW OPEN BEFORE OPENING ANOTHER!!!
    }

    //make un x'able later.

    //handles button
    public int handleEvent(ToggleButton button){
        for(int i = 0; i < employees.size(); i++) {
            if(button.equals(employees.get(i).tButton))
            {
                System.out.println(employees.get(i).index  + ": " + employees.get(i).ID);
                setID(employees.get(i).ID);
            }
        }
        return ID;
    }

}
