import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

public class ButtonNode {

    Button button;
    ToggleButton tButton; // use for employee
    int index;
    int ID; //for EmployeePage

    public void Node(Button button, int index){
        this.button = button;
        this.index = index;
    }

    //Button alternate.
    public void Node(ToggleButton tbutton, int index){
        this.tButton = tbutton;
        this.index = index;
    }

    //used on employee
    public void Node(ToggleButton tbutton, int index, int ID){
        this.tButton = tbutton;
        this.index = index;
        this.ID = ID;
    }




}
