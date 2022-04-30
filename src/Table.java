import java.util.LinkedList;

public class Table {

    int employeeID = -1; //Default (Nobody took table yet)

    int numOfGuests = 0;
    int tableNumber;

    LinkedList<ItemAndPrice> orders = new LinkedList<>(); //Table holds a list of menu items ordered
    LinkedList<String> notes = new LinkedList<>(); //Used in accordance with orders. Notes for each item.

    //Table creation
    public Table(int tableNumber)
    {
        this.tableNumber = tableNumber;
    }
}
