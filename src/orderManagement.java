import java.io.Serializable;
import java.util.HashMap;

public class orderManagement implements Serializable {
    public queue<order> normalorder;
    public HashMap<Integer,order>normalordermap;//stored with their order ids specific to food
    public queue<order> VIPorder;
    public HashMap<Integer,order>VIPordermap;//stored with the order ids specific to their food
    public orderManagement() {
        normalorder = new queue<>();
        VIPorder = new queue<>();
        normalordermap = new HashMap<>();
        VIPordermap = new HashMap<>();
    }
    public void addorder(order neworder,Boolean Vip) {
        if(Vip==true){
            VIPorder.push(neworder);
            VIPordermap.put(neworder.orderID,neworder);

        }
        else{
            normalorder.push(neworder);
            VIPordermap.put(neworder.orderID,neworder);
        }
    }
    public void deleteorder(int orderID,Boolean Vip) {
        if(Vip==true){
           VIPordermap.get(orderID).OrderStatus="Cancelled";
        }
        else{
            normalordermap.get(orderID).OrderStatus="Cancelled";
        }
    }
    public void displayorderVIPorder() {
        VIPorder.Traverse();
    }
    public void displayorderNormalorder() {
        normalorder.Traverse();
    }
    public void generateReport(Menu menu) {
        HashMap<String,Integer>frequencyOfSale=new HashMap<>();
        int total_sale=0;
        System.out.println("=== VIP Orders Report ===");
        for (HashMap.Entry<Integer, order> entry : VIPordermap.entrySet()) {
            order o = entry.getValue();
            System.out.println("Order ID: " + o.orderID + ", Item: " + o.nameOfItem + ", Quantity: " + o.quantity + ", Status: " + o.OrderStatus + ", Special Request: " + o.SpecialRequest);
            if(o.OrderStatus.equalsIgnoreCase("delivered")){
                FoodItem food=menu.searchItemBYname(o.nameOfItem);
                total_sale=total_sale+o.quantity* food.price;
                if(frequencyOfSale.get(food.name)==null){
                    frequencyOfSale.put(food.name,o.quantity);
                }

            }
        }

        System.out.println("\n=== Normal Orders Report ===");
        for (HashMap.Entry<Integer, order> entry : normalordermap.entrySet()) {
            order o = entry.getValue();
            System.out.println("Order ID: " + o.orderID + ", Item: " + o.nameOfItem + ", Quantity: " + o.quantity + ", Status: " + o.OrderStatus + ", Special Request: " + o.SpecialRequest);
            if(o.OrderStatus.equalsIgnoreCase("delivered")){
                FoodItem food=menu.searchItemBYname(o.nameOfItem);
                total_sale=total_sale+o.quantity* food.price;
                if(frequencyOfSale.get(food.name)==null){
                    frequencyOfSale.put(food.name,1);
                }
                else{
                    frequencyOfSale.put(food.name,frequencyOfSale.get(food.name)+1);
                }
            }
        }
        System.out.println("\n===Frequency of the Food items ===");
        for (HashMap.Entry<String, Integer> entry : frequencyOfSale.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Total sales= " + total_sale);
    }

}
//