package com.example.smartcanteen.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartcanteen.R;
import com.example.smartcanteen.database.DBHelper;
import com.example.smartcanteen.models.MenuItem;
import com.example.smartcanteen.models.Order;
import com.example.smartcanteen.utils.CartManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class OrderConfirmActivity extends AppCompatActivity {
    DBHelper db;
    TextView tvSummary, tvTotal;
    Button btnPlace;
    int userId = 2; // For demo; ideally pass real userId from session/intent

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_order_confirm);
        db = new DBHelper(this);
        tvSummary = findViewById(R.id.tvSummary);
        tvTotal = findViewById(R.id.tvTotal);
        btnPlace = findViewById(R.id.btnPlaceOrder);

        // Build and set the order summary string
        StringBuilder summaryBuilder = new StringBuilder();
        Map<MenuItem, Integer> cart = CartManager.getInstance().getCart();
        if (cart.isEmpty()) {
            summaryBuilder.append("Your cart is empty.");
        } else {
            for (Map.Entry<MenuItem, Integer> entry : cart.entrySet()) {
                summaryBuilder.append(String.format(Locale.getDefault(), "- %d x %s\n", entry.getValue(), entry.getKey().getName()));
            }
        }
        tvSummary.setText(summaryBuilder.toString().trim());

        // Set the total price
        double total = CartManager.getInstance().getTotal();
        tvTotal.setText(String.format(Locale.getDefault(), "Total: ₹%.2f", total));

        btnPlace.setOnClickListener(v -> {
            if (CartManager.getInstance().getCart().isEmpty()) {
                Toast.makeText(this, "Cannot place an empty order.", Toast.LENGTH_SHORT).show();
                return;
            }

            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            Order o = new Order(userId, CartManager.getInstance().toJson(), CartManager.getInstance().getTotal(), "Pending", currentDate);
            long id = db.placeOrder(o);
            if (id != -1) {
                CartManager.getInstance().clear();
                Toast.makeText(this, "Order placed. ID:" + id, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to place order", Toast.LENGTH_SHORT).show();
            }
        });
    }
}