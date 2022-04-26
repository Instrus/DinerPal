import java.util.LinkedList;

public class Table {

    int numOfGuests = 0;
    LinkedList<ItemAndPrice> orders = new LinkedList<ItemAndPrice>();
    int tableNumber;

    //Table creation
    public Table(int tableNumber)
    {
        this.tableNumber = tableNumber;
    }

    //prints order of table
    public void listOrder()
    {
        System.out.println("Listing table " + tableNumber + "'s order: ");
        for(int i = 0; i < orders.size(); i++)
            System.out.println(orders.get(i).item);
    }

}
