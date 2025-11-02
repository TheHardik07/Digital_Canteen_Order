package com.example.smartcanteen.utils;

import com.example.smartcanteen.models.MenuItem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> items;

    private CartManager(){ items = new ArrayList<>(); }

    public static CartManager getInstance(){
        if(instance==null) instance = new CartManager();
        return instance;
    }

    public void addItem(MenuItem m, int qty){
        for(CartItem ci : items){
            if(ci.menuItem.getId() == m.getId()){
                ci.qty += qty;
                return;
            }
        }
        items.add(new CartItem(m, qty));
    }

    public void removeItem(int menuItemId){
        items.removeIf(ci -> ci.menuItem.getId() == menuItemId);
    }

    public void clear(){ items.clear(); }

    public List<CartItem> getItems(){ return items; }

    public double getTotal(){
        double t = 0;
        for(CartItem ci: items) t += ci.menuItem.getPrice()*ci.qty;
        return t;
    }

    public String toJson(){
        JSONArray arr = new JSONArray();
        try {
            for(CartItem ci : items){
                JSONObject o = new JSONObject();
                o.put("id", ci.menuItem.getId());
                o.put("name", ci.menuItem.getName());
                o.put("price", ci.menuItem.getPrice());
                o.put("qty", ci.qty);
                arr.put(o);
            }
        } catch (Exception e){ e.printStackTrace(); }
        return arr.toString();
    }

    public static class CartItem {
        public MenuItem menuItem;
        public int qty;
        public CartItem(MenuItem m, int qty){ this.menuItem = m; this.qty = qty; }
    }
}
