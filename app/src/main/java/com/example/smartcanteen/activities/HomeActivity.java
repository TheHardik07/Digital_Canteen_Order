package com.example.smartcanteen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.smartcanteen.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView menuCard = findViewById(R.id.menuCard);
        CardView cartCard = findViewById(R.id.cartCard);
        CardView ordersCard = findViewById(R.id.ordersCard);
        CardView profileCard = findViewById(R.id.profileCard);

        menuCard.setOnClickListener(this);
        cartCard.setOnClickListener(this);
        ordersCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.menuCard:
                i = new Intent(this, MenuActivity.class);
                startActivity(i);
                break;
            case R.id.cartCard:
                i = new Intent(this, CartActivity.class);
                startActivity(i);
                break;
            case R.id.ordersCard:
                i = new Intent(this, OrderHistoryActivity.class);
                startActivity(i);
                break;
            case R.id.profileCard:
                i = new Intent(this, ProfileActivity.class);
                startActivity(i);
                break;
        }
    }
}
