package com.example.smartcanteen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcanteen.R;
import com.example.smartcanteen.adapters.ManageMenuAdapter;
import com.example.smartcanteen.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ManageMenuActivity extends AppCompatActivity {

    private RecyclerView menuRecyclerView;
    private ManageMenuAdapter menuAdapter;
    private List<MenuItem> menuList;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_menu);

        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize with some dummy data
        menuList = new ArrayList<>();
        menuList.add(new MenuItem(1, "Pizza", 10.99, "pizza.jpg", "Main Dishes"));
        menuList.add(new MenuItem(2, "Burger", 5.99, "burger.jpg", "Main Dishes"));

        menuAdapter = new ManageMenuAdapter(this, menuList);
        menuRecyclerView.setAdapter(menuAdapter);

        btnAddItem = findViewById(R.id.btnAddItem);
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageMenuActivity.this, AddItemActivity.class));
            }
        });
    }
}
