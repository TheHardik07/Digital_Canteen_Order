package com.example.smartcanteen.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.smartcanteen.R;
import com.example.smartcanteen.database.DBHelper;

public class OrderHistoryActivity extends AppCompatActivity {
    DBHelper db;
    RecyclerView rv;
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_order_history);
        db = new DBHelper(this);
        rv = findViewById(R.id.recyclerOrders);
        rv.setLayoutManager(new LinearLayoutManager(this));
        // Use OrderAdapter to display orders (not included fully for brevity)
    }
}
