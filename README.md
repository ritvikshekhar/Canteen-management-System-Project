# Please first run the loader 
    - It loads all the values 
    - It loads intial menu and other stuff

# When you have run all your cli then you can run gui
    -You can run GUI using main.java

# I/O STREAM MANAGEMENT
# All the details are stored
    -menu
    -order Management 
    -coustomers are stored
    -if NewMain you run it will be desrialised and restored
    -if your program NewMain ends then your details are stored

#   JUnit testing
    -"OutOfStock.java"
        -This is a file for testing out of stock 
        -It has menu in which Tea is not present. It tries to place
         order of the tea and will not able to place it into the cart.
         Thus the test case passed.
        -If it is able to place order of item which is not present 
         then the test case will fail.
    -"LoginTest.java"
        -This file is to check that the code does not allow those users
         which have not logged in.
        -To run this Test you must have loaded the data by running "loader.java"
         Or users.txt should be present which contain all the data of users

# Menu class
    - In Menu class I have used HashMap to store information of beverage meal and snack
    - for prices to be in sorted manner i have used TreeMap 

# Customer class
    - Each customer have a cart HashMap in which item is added 
    - After check out all orders are stored in the order history

# orderManagement class
    - Have Queue for normal users and VIP users


# Sign in as new customer    

    -On entering to the system you can either sign in as new customer
    -Or you can sign in as username-ritvik or david
    -no password needed
    -ritvik is a VIP user
    -david is not a Vip user
    -if you want vip just sign in again

# for the coustomer interface
    1. See All Menu-Can see all the items in the menu
    2. Search Menu by Name 
    3. Search Menu by Category
    4. Sort The Menu According To Price
    5. Add To cart
    6. Modify Quantity
    7. Remove From cart
      -when you remove from cart then you do it by removing each food item
    8. Check out-To place the order
               -After checkout cart will become empty
    9. View Order Status-View the Status of current order placed
    10. Cancel Order item wise-Cancel the order of the specific item 
                              -If "prepared" or "out for delivery" cannot cancel 
                              -If you cancel the order then the order will be finally cancelled by admin
    11. Order History-Contains all orders. Even those which are in process.
    12. Give review-You can give review of item if its in your
    13. Exit


# for Admin interface
    1. Admin Can ADD new items
    2. Admin can remove item
    And if item is removed and there are some orders they will be denied and removed from order queue  

    3. Modify item price
    4. Modify Availability
    5. View pending order
    -Staff can see order in priority with vip orders at top and normal below with orders places 
    according to their priority
    6. Update order status
    -when you update then update according to food order id
    7. cancel order and process refund
    -when you update then update according to food order id
    -can cancel the order cancelled by coustmer
    8. Generate the report

//
