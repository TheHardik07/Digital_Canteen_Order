package com.example.smartcanteen.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.smartcanteen.R;
import com.example.smartcanteen.database.DBHelper;
import com.example.smartcanteen.models.MenuItem;
import com.example.smartcanteen.utils.CartManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemDetailActivity extends AppCompatActivity {
    DBHelper db;
    TextView tvName, tvPrice;
    ImageView iv;
    EditText etQty;
    FloatingActionButton btnAdd;
    MenuItem item;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_item_detail);
        db = new DBHelper(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        tvName = findViewById(R.id.tvItemName);
        tvPrice = findViewById(R.id.tvItemPrice);
        iv = findViewById(R.id.ivItem);
        etQty = findViewById(R.id.etQty);
        btnAdd = findViewById(R.id.btnAddCart);

        int itemId = getIntent().getIntExtra("itemId", -1);
        if(itemId!=-1){
            for(MenuItem m : db.getAllMenuItems()){
                if(m.getId() == itemId){ item = m; break;}
            }
        }

        if(item!=null){
            collapsingToolbarLayout.setTitle(item.getName());
            tvName.setText(item.getName());
            tvPrice.setText("₹"+String.format("%.2f", item.getPrice()));

            Glide.with(this)
                .load(item.getImage()) // Assumes item.getImage() returns a URL
                .placeholder(R.drawable.canteen_placeholder)
                .error(R.drawable.canteen_placeholder) // Shows placeholder on error
                .into(iv);

        }

        btnAdd.setOnClickListener(v -> {
            int qty = 1;
            try{ qty = Integer.parseInt(etQty.getText().toString()); }catch(Exception e){}
            if(qty<1) qty = 1;
            CartManager.getInstance().addItem(item, qty);
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
