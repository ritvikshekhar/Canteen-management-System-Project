import java.io.*;
import java.util.HashMap;


public class Customer implements Serializable {
    public String name;
    public HashMap<String, order> cart;
    public HashMap<Integer, order> orderHistory;
    public int commonid;


    public Customer(String name) {
        this.name = name;
        this.cart = new HashMap<>();
        this.orderHistory = new HashMap<>();
        this.commonid = -1;
    }


    public String addItemToCart(Menu menu,order newOrder) {
        if(!menu.searchItemBYname(newOrder.nameOfItem).availablility){
            return "Cannot place into the cart as out of stock";
        }
        else if (cart.containsKey(newOrder.nameOfItem)) {
            System.out.println("Item already in the cart. Use 'Modify Quantity' to update it.");
            return "item already in the cart. Use 'Modify Quantity' to update it.";
        }
        else {
            cart.put(newOrder.nameOfItem, newOrder);
            System.out.println(newOrder.nameOfItem + " has been added to the cart.");
            return "Item has been added to the cart.";
        }
    }


    public void modifyQuantity(String itemName, int newQuantity) {
        if (cart.containsKey(itemName)) {
            order existingOrder = cart.get(itemName);
            existingOrder.quantity = newQuantity;
            System.out.println("Quantity of " + itemName + " has been updated to " + newQuantity);
        } else {
            System.out.println("Item not found in the cart.");
        }
    }


    public void removeFromCart(String itemName) {
        if (cart.containsKey(itemName)) {
            cart.remove(itemName);
            System.out.println(itemName + " has been removed from the cart.");
        } else {
            System.out.println("Item not found in the cart.");
        }
    }


    public void checkOut() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add items before checking out.");
        }
        else {
            for (order ord : cart.values()) {
                orderHistory.put(ord.orderID, ord);
                System.out.println("Order placed for: " + ord.nameOfItem + " (Quantity: " + ord.quantity + ")");
            }
            cart.clear();
            System.out.println("Thank you for your order! Your order has been placed.");
        }
    }


    public void viewOrderStatus(int commonid) {
        System.out.println(" ===Order Status is as follows:============");
        for (order ord : orderHistory.values()) {
            if (ord.sameorderid==commonid) {
                System.out.println("### Order Status for " + ord.nameOfItem + " : " + ord.OrderStatus);
            }
        }
    }




    public void displayOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No orders in your history.");
        }
        else {
            for (order ord : orderHistory.values()) {
                System.out.println(ord);
            }
        }
    }

//


}


