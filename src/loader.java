import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class loader {
    public static void main(String[] args) {
        Map<String,Customer>link=new HashMap<String,Customer>();
        link.put("ritvik", new Customer("ritvik"));
        link.put("david", new Customer("david"));

        Menu menu=new Menu();
        menu.addItem("beverage","dodh",20,true,11);
        menu.addItem("beverage","tea",10,true,12);
        menu.addItem("beverage","coffee",20,true,13);
        menu.addItem("meal","liti chokha",50,true,21);
        menu.addItem("meal","poha",30,true,22);
        menu.addItem("meal","roti",10,true,23);
        menu.addItem("meal","lauki ki sabji",60,true,24);
        menu.addItem("snack","samosa",10,true,31);
        menu.addItem("snack","fried makhana",30,true,32);
//
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("users.txt"))){
            out.writeObject(link);
            System.out.println("LOADED USERS");
        }
        catch (IOException e) {
            System.out.println("not able to write to user file");
        }

        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("menu.txt"))){
            out.writeObject(menu);
            System.out.println("LOADED MENU");
        }
        catch(IOException e){
            System.out.println("not able to write to menu file");
        }

        orderManagement order_management=new orderManagement();
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("order_management.txt"))){
            out.writeObject(order_management);
            System.out.println("LOADED ORDER MANAGEMENT");
        }
        catch(IOException e){
            System.out.println("not able to write order_management file");
        }

        int commonid=1;
        int date = 1;
        IntPair pair=new IntPair(commonid,date);
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("details.txt"))){
            out.writeObject(pair);
        }
        catch (IOException  e){
            System.out.println("not able to write details file");
        }
    }
}
