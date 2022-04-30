import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

//Used in creation of making buttons. Buttons created are associated with an index.
public class ButtonNode {

    Button button;
    ToggleButton tButton;
    int index;
    int ID;


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

    public void Node(Button button, int index){
        this.button = button;
        this.index = index;
    }

}
