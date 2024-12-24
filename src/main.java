import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

public class main {
    private Menu menu; // Deserialized Menu object
    private orderManagement order_management; // Deserialized normal orders
    private queue normalqueue;
    private queue VIPqueue;

    public main() {
        deserializeData();
        createAndShowGUI();
    }

    private void deserializeData() {
        try (ObjectInputStream menuIn = new ObjectInputStream(new FileInputStream("menu.txt"));
             ObjectInputStream In = new ObjectInputStream(new FileInputStream("order_management.txt"))) {

            menu = (Menu) menuIn.readObject();

            order_management = (orderManagement) In.readObject();

            normalqueue=order_management.normalorder;
            VIPqueue=order_management.VIPorder;
            System.out.println("menu and vip order and normal order deserialized successfully.");
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error during deserialization.");
        }
    }

    private void createAndShowGUI() {
        // Main Frame
        JFrame frame = new JFrame("Menu and Orders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        // Buttons
        JButton menuButton = new JButton("View Menu");
        JButton ordersButton = new JButton("View Orders");

        // Button actions
        menuButton.addActionListener(e -> showMenuGUI());
        ordersButton.addActionListener(e -> showOrdersGUI());

        // Add buttons to frame
        frame.add(menuButton);
        frame.add(ordersButton);

        // Display frame
        frame.setVisible(true);
    }

    private void showMenuGUI() {
        JFrame menuFrame = new JFrame("Menu");
        menuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuFrame.setSize(800, 600);

        JTabbedPane menuTabbedPane = new JTabbedPane();
        menuTabbedPane.addTab("Beverages", createTable2(menu.beverages.values().toArray()));
        menuTabbedPane.addTab("Meals", createTable2(menu.meals.values().toArray()));
        menuTabbedPane.addTab("Snacks", createTable2(menu.snacks.values().toArray()));

        menuFrame.add(menuTabbedPane);
        menuFrame.setVisible(true);
    }

    private void showOrdersGUI() {
        JFrame ordersFrame = new JFrame("Orders");
        ordersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ordersFrame.setSize(800, 600);

        JTabbedPane ordersTabbedPane = new JTabbedPane();
        ordersTabbedPane.addTab("Normal Orders", createTable(normalqueue.list.toArray()));
        ordersTabbedPane.addTab("VIP Orders", createTable(VIPqueue.list.toArray()));

        ordersFrame.add(ordersTabbedPane);
        ordersFrame.setVisible(true);
    }

    private JScrollPane createTable(Object[] items) {
        String[] columnNames= new String[]{"Order ID", "Food Name", "Quantity"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Object obj : items) {
            order ORDER=(order) obj;
            model.addRow(new Object[]{ORDER.sameorderid, ORDER.nameOfItem,ORDER.quantity});

        }

        JTable table = new JTable(model);
        return new JScrollPane(table);
    }
    private JScrollPane createTable2(Object[] items){
        String[] columnNames= new String[]{"Food Name", "Price","Availability"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Object obj : items) {
            FoodItem food=(FoodItem) obj;
            model.addRow(new Object[]{food.name,food.price,food.availablility});

        }
        JTable table = new JTable(model);
        return new JScrollPane(table);
    }

    public static void main(String[] args) {
        new main();
    }
}

//