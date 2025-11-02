package com.example.smartcanteen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartcanteen.R;

public class DashboardActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_dashboard);
        Button btnMenu = findViewById(R.id.btnMenu);
        Button btnCart = findViewById(R.id.btnCart);
        Button btnOrders = findViewById(R.id.btnOrders);
        btnMenu.setOnClickListener(v -> startActivity(new Intent(this, MenuActivity.class)));
        btnCart.setOnClickListener(v -> startActivity(new Intent(this, CartActivity.class)));
        btnOrders.setOnClickListener(v -> startActivity(new Intent(this, OrderHistoryActivity.class)));
    }
}
