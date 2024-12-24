import java.io.*;
import java.util.*;


public class NewMain implements Serializable {

    public static void main(String[] args) {

        Map<String, Customer> link = new HashMap<>();
        try (ObjectInputStream IN = new ObjectInputStream(new FileInputStream("users.txt"))) {
            link = (Map<String, Customer>) IN.readObject();
            System.out.println("Data deserialized successfully for link.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error during deserialization for link.");
        }


        Menu menu=new Menu();
        try(ObjectInputStream IN1=new ObjectInputStream(new FileInputStream("menu.txt"))){
            menu=(Menu)IN1.readObject();
            System.out.println("Menu deserialized successfully .");
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("not found menu");
        }
//

        int commonid=1;
        int date = 1;
        IntPair pair;
        try(ObjectInputStream IN5=new ObjectInputStream(new FileInputStream("details.txt"))){
            pair=(IntPair) IN5.readObject();
            commonid=pair.x;
            date=pair.y;
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Details not found");
        }
        Customer logged_in_customer = null;

        HashMap<String, Boolean> IsVip = new HashMap<>();
        IsVip.put("ritvik", true);
        IsVip.put("david", false);

        orderManagement order_management = new orderManagement();
        try(ObjectInputStream IN1=new ObjectInputStream(new FileInputStream("order_management.txt"))){
            order_management=(orderManagement)IN1.readObject();
            System.out.println("order management deserialized successfully .");
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("not found order management");
        }

        Scanner s = new Scanner(System.in);
        boolean appRunning = true;
        while (appRunning) {
            System.out.println("Welcome to the Canteen Management System");
            System.out.println("1. Enter the Application");
            System.out.println("2. Exit the Application");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
            s.nextLine();

            if (choice == 1) {
                boolean loggedIn = false;
                while (!loggedIn) {
                    System.out.println("---------------------");
                    System.out.println("Are you a new Customer??");
                    System.out.println("0. for sign in");
                    System.out.println("-----------------");
                    System.out.println("Login as:");
                    System.out.println("1. Customer");
                    System.out.println("2. Admin");
                    System.out.print("Enter your choice: ");
                    int roleChoice = s.nextInt();
                    s.nextLine();

                    if (roleChoice == 0) {
                        if (roleChoice == 0) {
                            System.out.println("Registering a new customer...");

                            System.out.println("Enter your desired username:");
                            String userName = s.nextLine().trim();


                            Customer newCustomer = new Customer(userName);


                            link.put(userName, newCustomer);
                            System.out.println("Customer registered successfully: " + userName);

                            System.out.println("Do you want VIP membersip");
                            boolean ans=s.nextBoolean();
                            IsVip.put(userName, ans);
                            System.out.println("Current users: " + link.keySet());
                            break;
                        }

                    }

                    if (roleChoice == 1) {
                        System.out.println("Enter your username:");
                        String userName = s.nextLine();

                        logged_in_customer = link.get(userName);
                        if (logged_in_customer == null) {
                            System.out.println("User not found. Please register first.");
                            continue;
                        }

                        boolean isvip = IsVip.getOrDefault(userName, false);
                        loggedIn = true;

                        while (true) {
                            System.out.println("1. See All Menu");
                            System.out.println("2. Search Menu by Name");
                            System.out.println("3. Search Menu by Category");
                            System.out.println("4. Sort The Menu According To Price");
                            System.out.println("5. Add To cart");
                            System.out.println("6. Modify Quantity");
                            System.out.println("7. Remove From cart");
                            System.out.println("8. Check out");
                            System.out.println("9. View Order Status");
                            System.out.println("10. Cancel Order item wise");
                            System.out.println("11. Order History");
                            System.out.println("12. Give review");
                            System.out.println("13. Exit");
                            System.out.print("Please choose one option: ");
                            int choice2 = s.nextInt();
                            s.nextLine();

                            if (choice2 == 1) {
                                menu.display();
                            }
                            else if (choice2 == 2) {
                                System.out.println("Enter the name of the food item:");
                                String foodItemName = s.nextLine();
                                FoodItem searchedFood = menu.searchItemBYname(foodItemName);
                                if (searchedFood == null) {
                                    System.out.println("Item does not exist.");
                                } else {
                                    System.out.println(searchedFood);
                                }
                            }
                            else if (choice2 == 3) {
                                System.out.println("Enter the category:[beverage,snack,meal]");
                                String foodType = s.nextLine();
                                System.out.println("Enter the name of the food item:");
                                String foodItemName = s.nextLine();
                                FoodItem searchedFood = menu.searchItemBytype(foodType, foodItemName);
                                if (searchedFood == null) {
                                    System.out.println("Item does not exist.");
                                } else {
                                    System.out.println(searchedFood);
                                }
                            }
                            else if (choice2 == 4) {
                                menu.priceSortedMenu();
                            }
                            else if (choice2 == 5) {
                                System.out.println("Enter the category:");
                                String foodType = s.nextLine();
                                System.out.println("Enter the name of the food item:");
                                String foodItemName = s.nextLine();
                                System.out.println("Enter the quantity:");
                                int quantity = s.nextInt();
                                s.nextLine();
                                System.out.println("Enter the address:");
                                String address = s.nextLine();
                                System.out.println("Enter any special request:");
                                String request = s.nextLine();

                                FoodItem food = menu.searchItemBytype(foodType, foodItemName);
                                if (food != null) {
                                    int orderId = date++;
                                    order newOrder = new order(food.name, foodType, orderId, quantity, address, "Order placed", request);
                                    logged_in_customer.addItemToCart(menu,newOrder);
                                }
                                else {
                                    System.out.println("Food item not found in the menu.");
                                }
                            }
                            else if (choice2 == 6) {
                                System.out.println("Enter the name of the food item to modify its quantity:");
                                String itemName = s.nextLine();
                                System.out.println("Enter the new quantity:");
                                int newQuantity = s.nextInt();
                                s.nextLine();
                                logged_in_customer.modifyQuantity(itemName, newQuantity);
                            }
                            else if (choice2 == 7) {
                                System.out.println("Enter the name of the food item to remove from cart:");
                                String itemName = s.nextLine();
                                logged_in_customer.removeFromCart(itemName);
                            }
                            else if (choice2 == 8) {
                                int totalbill=0;
                                for (order ord : logged_in_customer.cart.values()) {
                                    int price=menu.searchItemBYname(ord.nameOfItem).price;
                                    totalbill=totalbill+(ord.quantity*price);
                                    ord.sameorderid=commonid;
                                    order_management.addorder(ord, isvip);
                                }
                                logged_in_customer.commonid = commonid;
                                commonid=commonid+1;
                                System.out.println("Your total amount billed: "+totalbill);
                                logged_in_customer.checkOut();


                            }
                            else if (choice2 == 9) {
                                logged_in_customer.viewOrderStatus(logged_in_customer.commonid);
                            }
                            else if (choice2 == 10) {
                                System.out.println("Enter the food id of the order");
                                int id=s.nextInt();
                               order od=logged_in_customer.orderHistory.get(id);
                               if(od==null){
                                   System.out.println("order not found. Please check order history to correctly fill the value");
                               }
                               else if(od.OrderStatus.equalsIgnoreCase("prepared") || od.OrderStatus.equalsIgnoreCase("out for delivery")){
                                   System.out.println("Cannot be cancelled");;
                                   break;
                               }
                               else{
                                   od.OrderStatus="cancelled";
                               }
                            }
                            else if (choice2 == 11) {
                                System.out.println("Order History:");
                                logged_in_customer.displayOrderHistory();
                                System.out.println("=========================");
                                System.out.println("Want to reorder");
                                System.out.println("Enter True/false");
                                boolean ans=s.nextBoolean();
                                if(ans==true){
                                    System.out.println("Just enter the order ID of food you want to add in the cart");
                                    int val = s.nextInt();
                                    String foodType = logged_in_customer.orderHistory.get(val).ItemType;
                                    String foodItemName = logged_in_customer.orderHistory.get(val).nameOfItem;
                                    System.out.println("Enter the quantity:");
                                    int quantity = s.nextInt();
                                    s.nextLine();
                                    System.out.println("Enter the address:");
                                    String address = s.nextLine();
                                    System.out.println("Enter any special request:");
                                    String request = s.nextLine();

                                    FoodItem food = menu.searchItemBytype(foodType, foodItemName);
                                    if (food != null) {
                                        int orderId = date++;
                                        order newOrder = new order(food.name, foodType, orderId, quantity, address, "Order placed", request);
                                        logged_in_customer.addItemToCart(menu,newOrder);
                                    } else {
                                        System.out.println("Food item not found in the menu");
                                    }
                                }

                            }
                            else if (choice2 == 12) {
                                System.out.println("Enter the item name to give a review:");
                                String itemName = s.nextLine();
                                boolean cangivereview=false;
                                for (Map.Entry<Integer,order> entry : logged_in_customer.orderHistory.entrySet()) {
                                    if(itemName==entry.getValue().nameOfItem){
                                        cangivereview=true;
                                    }
                                }
                                if(cangivereview){
                                    break;
                                }

                                System.out.println("Enter the review:");
                                String review = s.nextLine();
                                FoodItem itemToReview = menu.searchItemBYname(itemName);
                                if (itemToReview != null) {
                                    itemToReview.review.add(review);
                                }
                                else {
                                    System.out.println("Item not found.");
                                }
                            }
                            else if (choice2 == 13) {
                                break;
                            }
                            else {
                                System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } else if (roleChoice == 2) {
                        System.out.println("Enter Password:");
                        String password = s.nextLine();
                        if (password.equals("Admin123")) {
                            while (true) {
                                System.out.println("1. Add new item");
                                System.out.println("2. Remove item");
                                System.out.println("3. Modify item price");
                                System.out.println("4. Modify Availability");
                                System.out.println("5. View pending orders");
                                System.out.println("6. Update order status according to food order id");
                                System.out.println("7. Cancel order and process refund according to food order id");
                                System.out.println("8. Generate the report");
                                System.out.println("9. Exit");
                                System.out.print("Enter your choice: ");
                                int adminChoice = s.nextInt();
                                s.nextLine();

                                if (adminChoice == 1) {
                                    System.out.println("Enter the name of the food item:");
                                    String itemName = s.nextLine();
                                    System.out.println("Enter the type of food item:");
                                    String foodItemType = s.nextLine();
                                    System.out.println("Enter the price:");
                                    int price = s.nextInt();
                                    s.nextLine();
                                    System.out.println("Enter the availability (true/false):");
                                    boolean availability = s.nextBoolean();
                                    s.nextLine();
                                    System.out.println("Enter the code:");
                                    int code = s.nextInt();
                                    s.nextLine();
                                    menu.addItem(foodItemType, itemName, price, availability, code);
                                }
                                else if (adminChoice == 2) {
                                    System.out.println("Enter the name of the food item:");
                                    String itemName = s.nextLine();
                                    System.out.println("Enter the type of food item:");
                                    String foodItemType = s.nextLine();
                                    System.out.println("Enter the code of the food item:");
                                    int code = s.nextInt();
                                    s.nextLine();
                                    menu.removeItem(foodItemType, code);
                                    for (order ord : order_management.normalordermap.values()) {
                                        if (ord.nameOfItem.equals(itemName)) {
                                            ord.OrderStatus="denied and refund will be done";
                                        }
                                    }
                                    for (order ord : order_management.VIPordermap.values()) {
                                        if (ord.nameOfItem.equals(itemName)) {
                                            ord.OrderStatus="denied and refund will be done";
                                        }
                                    }
                                    Iterator<order> iterator = order_management.normalorder.list.iterator();
                                    while (iterator.hasNext()) {
                                        order ord = iterator.next();
                                        if (ord.nameOfItem.equals(itemName)) {
                                            ord.OrderStatus = "denied and refund will be done";
                                            iterator.remove();
                                        }
                                    }
                                    Iterator<order> iterato = order_management.VIPorder.list.iterator();
                                    while (iterato.hasNext()) {
                                        order ord = iterato.next();
                                        if (ord.nameOfItem.equals(itemName)) {
                                            ord.OrderStatus = "denied and refund will be done";
                                            iterato.remove();
                                        }
                                    }
                                }
                                else if (adminChoice == 3) {
                                    System.out.println("Enter the name of the food item:");
                                    String itemnype = s.nextLine();
                                    System.out.println("Enter the type of the food item:");
                                    String itemtype = s.nextLine();
                                    System.out.println("Enter the new price of the item:");
                                    int newPrice = s.nextInt();
                                    s.nextLine();
                                    menu.updatePrice(itemnype, itemtype, newPrice);
                                }
                                else if(adminChoice==4){
                                    System.out.println("Enter the name of the food item:");
                                    String itemname = s.nextLine();
                                    System.out.println("Enter the new Availability of the item:");
                                    boolean newavail=s.nextBoolean();
                                    menu.updateAvailability(itemname,newavail);
                                }
                                else if (adminChoice == 5) {
                                    System.out.println("VIP Orders:");
                                    order_management.displayorderVIPorder();
                                    System.out.println("Normal Orders:");
                                    order_management.displayorderNormalorder();
                                }

                                else if (adminChoice == 6) {
                                    boolean isvip = false;
                                    System.out.println("Enter the food order ID:");
                                    int order_id = s.nextInt();
                                    s.nextLine();
                                    System.out.println("Enter the new status:");
                                    String status = s.nextLine();

                                    order test = order_management.normalordermap.get(order_id);
                                    if (test == null) {
                                        test = order_management.VIPordermap.get(order_id);
                                        isvip = true;
                                    }

                                    if (test != null) {

                                        Iterator<order> normalIterator = order_management.normalorder.list.iterator();
                                        while (normalIterator.hasNext()) {
                                            order ord = normalIterator.next();
                                            if (ord.orderID == order_id) {
                                                ord.OrderStatus = status;
                                                if (status.equals("delivered")) {
                                                    normalIterator.remove();
                                                }
                                                break;
                                            }
                                        }


                                        Iterator<order> vipIterator = order_management.VIPorder.list.iterator();
                                        while (vipIterator.hasNext()) {
                                            order or = vipIterator.next();
                                            if (or.orderID == order_id) {
                                                or.OrderStatus = status;
                                                if (status.equals("delivered")) {
                                                    vipIterator.remove();
                                                }
                                                break;
                                            }
                                        }

                                        System.out.println("Order status updated successfully.");
                                    } else {
                                        System.out.println("Order not found.");
                                    }
                                }

                                else if (adminChoice == 7) {
                                    System.out.println("Enter the food order ID to cancel:");
                                    int order_id = s.nextInt();
                                    s.nextLine();
                                    for (order ord : order_management.normalordermap.values()) {
                                        if (ord.orderID==order_id) {
                                            ord.OrderStatus="Cancelled and refund in process. Sorry of any inconvenience !!";
                                        }
                                    }
                                    for (order ord : order_management.VIPordermap.values()) {
                                        if (ord.orderID==order_id) {
                                            ord.OrderStatus="Cancelled and refund in process. Sorry of any inconvenience !!";
                                        }
                                    }
                                    Iterator<order> iterator = order_management.normalorder.list.iterator();
                                    while (iterator.hasNext()) {
                                        order ord = iterator.next();
                                        if (ord.orderID==order_id) {
                                            ord.OrderStatus = "Cancelled and refund in process. Sorry of any inconvenience !!";
                                            iterator.remove();
                                        }
                                    }
                                    Iterator<order> iterato = order_management.VIPorder.list.iterator();
                                    while (iterato.hasNext()) {
                                        order ord = iterato.next();
                                        if (ord.orderID==order_id) {
                                            ord.OrderStatus = "Cancelled and refund in process. Sorry of any inconvenience !!";
                                            iterato.remove();
                                        }
                                    }
                                }
                                else if(adminChoice==8){
                                    order_management.generateReport(menu);
                                }
                                else if (adminChoice == 9) {
                                    break;
                                }
                                else {
                                    System.out.println("Invalid choice. Please try again.");
                                }
                            }
                        } else {
                            System.out.println("Incorrect password. Access denied.");
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if (choice == 2) {
                appRunning = false;
                System.out.println("Exiting the application. Thank you!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        s.close();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("menu.txt"))) {
            out.writeObject(menu);
        }
        catch (IOException e) {
            System.out.println("Not able to serialize menu");
        }

        try(ObjectOutputStream out1=new ObjectOutputStream(new FileOutputStream("order_management.txt"))){
            out1.writeObject(order_management);
        }
        catch (IOException e) {
            System.out.println("Not able to serialize order_management");
        }


        try(ObjectOutputStream out3=new ObjectOutputStream(new FileOutputStream("users.txt"))){
            out3.writeObject(link);
        }
        catch (IOException e){
            System.out.println("Not able to serialize link");
        }
        try(ObjectOutputStream out4=new ObjectOutputStream(new FileOutputStream("details.txt"))){
            IntPair p=new IntPair(commonid,date);
            out4.writeObject(p);
        }
        catch (IOException  e){
            System.out.println("not able to write details file");
        }
    }
}

