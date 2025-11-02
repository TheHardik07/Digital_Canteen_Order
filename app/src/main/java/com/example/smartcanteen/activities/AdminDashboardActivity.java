package com.example.smartcanteen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.smartcanteen.R;

public class AdminDashboardActivity extends AppCompatActivity {

    private CardView manageMenuCard, manageOrdersCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        manageMenuCard = findViewById(R.id.manageMenuCard);
        manageOrdersCard = findViewById(R.id.manageOrdersCard);

        manageMenuCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboardActivity.this, ManageMenuActivity.class));
            }
        });

        manageOrdersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashboardActivity.this, ManageOrdersActivity.class));
            }
        });
    }
}