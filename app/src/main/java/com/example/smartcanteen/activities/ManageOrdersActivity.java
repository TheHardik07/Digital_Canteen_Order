package com.example.smartcanteen.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcanteen.R;
import com.example.smartcanteen.adapters.OrderAdapter;
import com.example.smartcanteen.models.Order;

import java.util.ArrayList;
import java.util.List;

public class ManageOrdersActivity extends AppCompatActivity {

    private RecyclerView ordersRecyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_orders);

        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize with some dummy data
        orderList = new ArrayList<>();
        orderList.add(new Order(1, 1, "[]", 0.0, "Pending", ""));
        orderList.add(new Order(2, 2, "[]", 0.0, "Completed", ""));

        orderAdapter = new OrderAdapter(this, orderList);
        ordersRecyclerView.setAdapter(orderAdapter);
    }
}