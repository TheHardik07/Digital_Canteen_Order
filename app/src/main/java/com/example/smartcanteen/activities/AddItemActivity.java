package com.example.smartcanteen.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcanteen.R;
import com.example.smartcanteen.database.DBHelper;
import com.example.smartcanteen.models.MenuItem;

public class AddItemActivity extends AppCompatActivity {

    private EditText itemName, itemDescription, itemPrice, itemCategory;
    private Button addButton;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        itemName = findViewById(R.id.itemName);
        itemDescription = findViewById(R.id.itemDescription);
        itemPrice = findViewById(R.id.itemPrice);
        itemCategory = findViewById(R.id.itemCategory);
        addButton = findViewById(R.id.addButton);
        dbHelper = new DBHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemName.getText().toString();
                String description = itemDescription.getText().toString();
                String priceStr = itemPrice.getText().toString();
                String category = itemCategory.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description) || TextUtils.isEmpty(priceStr) || TextUtils.isEmpty(category)) {
                    Toast.makeText(AddItemActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                double price = Double.parseDouble(priceStr);
                MenuItem menuItem = new MenuItem(name, price, "", category);

                // TODO: You will need to implement the addMenuItem method in your DBHelper
                // long id = dbHelper.addMenuItem(menuItem);
                // if (id != -1) {
                //     Toast.makeText(AddItemActivity.this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
                //     finish();
                // } else {
                //     Toast.makeText(AddItemActivity.this, "Error adding item", Toast.LENGTH_SHORT).show();
                // }
                Toast.makeText(AddItemActivity.this, "Add item logic to be implemented", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
