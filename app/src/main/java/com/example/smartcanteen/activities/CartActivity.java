package com.example.smartcanteen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.smartcanteen.R;
import com.example.smartcanteen.utils.CartManager;

public class CartActivity extends AppCompatActivity {
    RecyclerView rv;
    TextView tvTotal;
    Button btnCheckout;
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_cart);
        rv = findViewById(R.id.recyclerCart);
        tvTotal = findViewById(R.id.tvTotal);
        btnCheckout = findViewById(R.id.btnCheckout);

        rv.setLayoutManager(new LinearLayoutManager(this));
        // Implement a simple CartAdapter or reuse MenuAdapter concept. For brevity, show total and checkout.
        double total = CartManager.getInstance().getTotal();
        tvTotal.setText("Total: ₹"+String.format("%.2f", total));
        btnCheckout.setOnClickListener(v -> {
            startActivity(new Intent(this, OrderConfirmActivity.class));
        });
    }
}
