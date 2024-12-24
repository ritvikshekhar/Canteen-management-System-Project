import java.io.Serializable;

public class order implements Serializable {
    public String nameOfItem;
    public String ItemType;
    public int orderID;
    public int quantity;
    public String address;
    public String OrderStatus;
    public String SpecialRequest;
    public int sameorderid;
    public order(String name, String itemType, int orderID, int quantity, String address, String orderStatus,String specialRequest) {
        this.nameOfItem = name;
        this.ItemType = itemType;
        this.orderID = orderID;
        this.quantity = quantity;
        this.address = address;
        this.OrderStatus=orderStatus;
        this.SpecialRequest=specialRequest;
        this.sameorderid=-1;
    }
    public String getStatus(){
        return OrderStatus;
    }
    @Override
    public String toString() {
        return "Order Details:\n" +
                "---------------\n" +
                "Address: " + address + "\n" +
                "Item Name: " + nameOfItem + "\n" +
                "Item Type: " + ItemType + "\n" +
                "Order ID: " + sameorderid + "\n" +
                "Quantity: " + quantity + "\n" +
                "Order Status: " + OrderStatus + "\n" +
                "Special Request: " + SpecialRequest + "\n"+
                "Order ID of food:" + orderID;
    }

}
//