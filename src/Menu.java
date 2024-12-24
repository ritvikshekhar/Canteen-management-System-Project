import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Menu implements Serializable {

    // HashMaps to store different types of food items with their code
    public HashMap<Integer, FoodItem> beverages = new HashMap<>();
    public HashMap<Integer, FoodItem> meals = new HashMap<>();
    public HashMap<Integer, FoodItem> snacks = new HashMap<>();

    // HashMaps to associate item names with their unique codes
    public HashMap<String, Integer> beverageCodes = new HashMap<>();
    public HashMap<String, Integer> mealCodes = new HashMap<>();
    public HashMap<String, Integer> snackCodes = new HashMap<>();

    // TreeMaps to store items sorted by price
    public TreeMap<Integer, List<FoodItem>> beveragePriceMap = new TreeMap<>();
    public TreeMap<Integer, List<FoodItem>> mealPriceMap = new TreeMap<>();
    public TreeMap<Integer, List<FoodItem>> snackPriceMap = new TreeMap<>();

    // Method to add an item to the menu
    public void addItem(String itemType, String itemName, int price, boolean available, int code) {
        FoodItem newItem = new FoodItem(itemName, price, code, available);

        switch (itemType.toLowerCase()) {
            case "snack":
                snacks.put(code, newItem);
                snackCodes.put(itemName.toLowerCase(), code);
                addToPriceMap(snackPriceMap, price, newItem);
                break;
            case "beverage":
                beverages.put(code, newItem);
                beverageCodes.put(itemName.toLowerCase(), code);
                addToPriceMap(beveragePriceMap, price, newItem);
                break;
            case "meal":
                meals.put(code, newItem);
                mealCodes.put(itemName.toLowerCase(), code);
                addToPriceMap(mealPriceMap, price, newItem);
                break;
            default:
                System.out.println("Invalid category. Please select 'snack', 'beverage', or 'meal'.");
        }
    }

    // method to add items to the price map
    private void addToPriceMap(TreeMap<Integer, List<FoodItem>> priceMap, int price, FoodItem item) {
        if (!priceMap.containsKey(price)) {
            priceMap.put(price, new ArrayList<>());
        }
        priceMap.get(price).add(item);
    }

    // method to display all items in each category
    public void display() {
        System.out.println("Beverages: " + beverages.values());
        System.out.println("Meals: " + meals.values());
        System.out.println("Snacks: " + snacks.values());
    }

    // method to update the availability of an item
    public void updateAvailability(String itemname, boolean available) {
        FoodItem item = searchItemBYname(itemname);
        if (item != null) {
            item.availablility=(available);
        } else {
            System.out.println("Item not found.");
        }
    }

    // method to remove an item
    public void removeItem(String itemType, int code) {
        switch (itemType.toLowerCase()) {
            case "beverage":
                beverages.remove(code);
                break;
            case "meal":
                meals.remove(code);
                break;
            case "snack":
                snacks.remove(code);
                break;
            default:
                System.out.println("Invalid category.");
        }
    }

public void updatePrice(String itemname,String itemType,int newPrice) {
    FoodItem item =searchItemBYname(itemname);
    if (item != null) {
        removeFromPriceMap(getPriceMap(itemType), item.price, item);
        item.price = newPrice;
        addToPriceMap(getPriceMap(itemType), newPrice, item);
        System.out.println("Price updated successfully.");
    }
    else {
        System.out.println("Item not found.");
    }
}


    private void removeFromPriceMap(TreeMap<Integer, List<FoodItem>> priceMap, int price, FoodItem item) {
        if (priceMap.containsKey(price)) {
            List<FoodItem> itemList = priceMap.get(price);
            itemList.remove(item);
            if (itemList.isEmpty()) {
                priceMap.remove(price);
            }
        }
    }


    private TreeMap<Integer, List<FoodItem>> getPriceMap(String itemType) {
        switch (itemType.toLowerCase()) {
            case "beverage":
                return beveragePriceMap;
            case "meal":
                return mealPriceMap;
            case "snack":
                return snackPriceMap;
            default:
                return null;
        }
    }


    // method to search for an item by name
    public FoodItem searchItemBYname(String itemName) {
        int code = 0;

        if (beverageCodes.containsKey(itemName.toLowerCase())) {
            code = beverageCodes.get(itemName.toLowerCase());
            return beverages.get(code);
        } else if (mealCodes.containsKey(itemName.toLowerCase())) {
            code = mealCodes.get(itemName.toLowerCase());
            return meals.get(code);
        } else if (snackCodes.containsKey(itemName.toLowerCase())) {
            code = snackCodes.get(itemName.toLowerCase());
            return snacks.get(code);
        }

        System.out.println("Item not found.");
        return null;
    }

    // method to search for an item by type and name
    public FoodItem searchItemBytype(String itemType, String itemName) {
        int code = 0;

        switch (itemType.toLowerCase()) {
            case "beverage":
                if (beverageCodes.containsKey(itemName.toLowerCase())) {
                    code = beverageCodes.get(itemName.toLowerCase());
                    return beverages.get(code);
                }
                break;
            case "meal":
                if (mealCodes.containsKey(itemName.toLowerCase())) {
                    code = mealCodes.get(itemName.toLowerCase());
                    return meals.get(code);
                }
                break;
            case "snack":
                if (snackCodes.containsKey(itemName.toLowerCase())) {
                    code = snackCodes.get(itemName.toLowerCase());
                    return snacks.get(code);
                }
                break;
            default:
                System.out.println("Invalid category.");
        }

        System.out.println("Item not found.");
        return null;
    }

    // method to display items sorted by price
    public void priceSortedMenu() {
        System.out.println("Beverages sorted by price:");
        printPriceMap(beveragePriceMap);
        System.out.println("Snacks sorted by price:");
        printPriceMap(snackPriceMap);
        System.out.println("Meals sorted by price:");
        printPriceMap(mealPriceMap);
    }

    // method to print items from a price map
    private void printPriceMap(TreeMap<Integer, List<FoodItem>> priceMap) {
        for (Map.Entry<Integer, List<FoodItem>> entry : priceMap.entrySet()) {
            for (FoodItem item : entry.getValue()) {
                System.out.println(item);
            }
        }
    }
}
//