package com.example.smartcanteen.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Locale;

public class Order {

    private int id;
    private int userId;
    private String customerName; // Added back for display purposes
    private String itemsJson;
    private double total;
    private String status;
    private String date;

    // Constructor for creating a new order to be inserted into DB
    public Order(int userId, String itemsJson, double total, String status, String date) {
        this.userId = userId;
        this.itemsJson = itemsJson;
        this.total = total;
        this.status = status;
        this.date = date;
        this.customerName = ""; // Can be fetched later if needed
    }

    // Constructor for fetching a complete order from DB
    public Order(int id, int userId, String itemsJson, double total, String status, String date) {
        this.id = id;
        this.userId = userId;
        this.itemsJson = itemsJson;
        this.total = total;
        this.status = status;
        this.date = date;
        this.customerName = ""; // Can be fetched later if needed
    }

    // Constructor for dummy data in ManageOrdersActivity
    public Order(int id, String customerName, String status) {
        this.id = id;
        this.customerName = customerName;
        this.status = status;
        this.userId = 0;
        this.itemsJson = "[]";
        this.total = 0.0;
        this.date = "";
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getCustomerName() { return customerName; }
    public String getItemsJson() { return itemsJson; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    public String getDate() { return date; }

    public String getSummary() {
        if (itemsJson == null || itemsJson.isEmpty()) {
            return "No items in this order.";
        }

        StringBuilder summaryBuilder = new StringBuilder();
        try {
            JSONArray items = new JSONArray(itemsJson);
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String name = item.getString("name");
                int quantity = item.getInt("quantity");
                summaryBuilder.append(String.format(Locale.getDefault(), "- %d x %s\n", quantity, name));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error parsing order items.";
        }
        return summaryBuilder.toString().trim();
    }
}
