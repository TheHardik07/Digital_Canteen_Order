package com.example.smartcanteen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import com.example.smartcanteen.models.MenuItem;
import com.example.smartcanteen.models.Order;
import com.example.smartcanteen.models.User;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "smartcanteen.db";
    private static final int DB_VERSION = 1;

    // Tables
    private static final String TABLE_USERS = "users";
    private static final String TABLE_MENU = "menu_items";
    private static final String TABLE_ORDERS = "orders";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsers = "CREATE TABLE " + TABLE_USERS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT UNIQUE, password TEXT, role TEXT)";
        String createMenu = "CREATE TABLE " + TABLE_MENU +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price REAL, image TEXT, category TEXT)";
        String createOrders = "CREATE TABLE " + TABLE_ORDERS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, items TEXT, total REAL, status TEXT, date TEXT)";

        db.execSQL(createUsers);
        db.execSQL(createMenu);
        db.execSQL(createOrders);

        // Insert a default admin
        ContentValues cv = new ContentValues();
        cv.put("name", "admin");
        cv.put("email", "admin@canteen.com");
        cv.put("password", "admin123");
        cv.put("role", "Admin");
        db.insert(TABLE_USERS, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    // ---------- Users ----------
    public boolean registerUser(String name, String email, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("role", role);
        long id = db.insert(TABLE_USERS, null, cv);
        return id != -1;
    }

    public User loginUser(String email, String password, String role) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE email=? AND password=? AND role=?", new String[]{email, password, role});
        if (c.moveToFirst()) {
            User u = new User(c.getInt(0), c.getString(1), c.getString(2), c.getString(4));
            c.close();
            return u;
        }
        c.close();
        return null;
    }

    // ---------- Menu CRUD ----------
    public long addMenuItem(MenuItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", item.getName());
        cv.put("price", item.getPrice());
        cv.put("image", item.getImage());
        cv.put("category", item.getCategory());
        return db.insert(TABLE_MENU, null, cv);
    }

    public boolean updateMenuItem(MenuItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", item.getName());
        cv.put("price", item.getPrice());
        cv.put("image", item.getImage());
        cv.put("category", item.getCategory());
        int rows = db.update(TABLE_MENU, cv, "id=?", new String[]{String.valueOf(item.getId())});
        return rows > 0;
    }

    public boolean deleteMenuItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_MENU, "id=?", new String[]{String.valueOf(id)});
        return rows > 0;
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MENU, null);
        if (c.moveToFirst()) {
            do {
                MenuItem m = new MenuItem(c.getInt(0), c.getString(1), c.getDouble(2), c.getString(3), c.getString(4));
                list.add(m);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    // ---------- Orders ----------
    public long placeOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", order.getUserId());
        cv.put("items", order.getItemsJson());
        cv.put("total", order.getTotal());
        cv.put("status", order.getStatus());
        cv.put("date", order.getDate());
        return db.insert(TABLE_ORDERS, null, cv);
    }

    public List<Order> getOrdersByUser(int userId) {
        List<Order> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_ORDERS + " WHERE user_id=?", new String[]{String.valueOf(userId)});
        if (c.moveToFirst()) {
            do {
                Order o = new Order(c.getInt(0), c.getInt(1), c.getString(2), c.getDouble(3), c.getString(4), c.getString(5));
                list.add(o);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_ORDERS + " ORDER BY id DESC", null);
        if (c.moveToFirst()) {
            do {
                Order o = new Order(c.getInt(0), c.getInt(1), c.getString(2), c.getDouble(3), c.getString(4), c.getString(5));
                list.add(o);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }

    public boolean updateOrderStatus(int id, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("status", status);
        int rows = db.update(TABLE_ORDERS, cv, "id=?", new String[]{String.valueOf(id)});
        return rows > 0;
    }
}
