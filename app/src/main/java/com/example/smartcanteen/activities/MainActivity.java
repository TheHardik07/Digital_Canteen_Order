package com.example.smartcanteen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.smartcanteen.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView menuCard, cartCard, orderHistoryCard, profileCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuCard = findViewById(R.id.menuCard);
        cartCard = findViewById(R.id.cartCard);
        orderHistoryCard = findViewById(R.id.orderHistoryCard);
        profileCard = findViewById(R.id.profileCard);

        menuCard.setOnClickListener(this);
        cartCard.setOnClickListener(this);
        orderHistoryCard.setOnClickListener(this);
        profileCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        if (v.getId() == R.id.menuCard) {
            i = new Intent(this, MenuActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.cartCard) {
            i = new Intent(this, CartActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.orderHistoryCard) {
            i = new Intent(this, OrderHistoryActivity.class);
            startActivity(i);
        } else if (v.getId() == R.id.profileCard) {
            i = new Intent(this, ProfileActivity.class);
            startActivity(i);
        }
    }
}
