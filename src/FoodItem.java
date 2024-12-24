import java.io.Serializable;
import java.util.ArrayList;

public class FoodItem implements Serializable {
    String name;
    int price;
    int code;
    boolean availablility;
    ArrayList<String>review;
    public FoodItem(String name, int price, int code, boolean availablility) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.availablility = availablility;
        this.review = new ArrayList<>();
    }
//
    @Override
    public String toString() {
        return "FoodItem Details:\n" +
                "------------------\n" +
                "Name: " + name + "\n" +
                "Price: $" + price + "\n" +
                "Code: " + code + "\n" +
                "Availability: " + (availablility ? "Available" : "Not Available") + "\n" +
                "Review: " + review + "\n";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FoodItem foodItem = (FoodItem) obj;
        return code == foodItem.code;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(code);
//    }


}
