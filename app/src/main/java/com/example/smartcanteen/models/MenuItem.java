package com.example.smartcanteen.models;
public class MenuItem {
    private int id;
    private String name;
    private double price;
    private String image; // local drawable name or URL
    private String category;

    public MenuItem(int id, String name, double price, String image, String category){
        this.id = id; this.name = name; this.price = price; this.image = image; this.category = category;
    }
    public MenuItem(String name, double price, String image, String category){
        this.name = name; this.price = price; this.image = image; this.category = category;
    }
    // getters & setters
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public double getPrice(){return price;}
    public String getImage(){return image;}
    public String getCategory(){return category;}
}