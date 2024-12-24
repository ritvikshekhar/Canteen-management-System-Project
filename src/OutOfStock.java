import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class OutOfStock {

    public Menu menu;
    public orderManagement orderSystem;
    public Customer customer;
    @Before
    public void setUp() {
        // Set up the menu and order system
        menu = new Menu();
        orderSystem = new orderManagement();
        customer=new Customer("vijay");
        // Add some items to the menu
        menu.addItem("beverage", "Coffee", 5, true, 1); // In stock
        menu.addItem("beverage", "Tea", 3, false, 2); // Out of stock
    }

    @Test
    public void testOrderOutOfStockItem() {
        // Attempt to place an order for out-of-stock item
//        "Taking the food item Tea"
        order newOrder = new order("Tea", "beverage", 1, 1, "abc", "Order placed", "no");
        String actualresult=customer.addItemToCart(menu,newOrder);
        if(actualresult.equals("Cannot place into the cart as out of stock")){
            System.out.println("Test Case passed:Not able to place order");
        }

        assertEquals("Test Case Failed: able to place order out of stock","Cannot place into the cart as out of stock",customer.addItemToCart(menu,newOrder));



    }

//
}
