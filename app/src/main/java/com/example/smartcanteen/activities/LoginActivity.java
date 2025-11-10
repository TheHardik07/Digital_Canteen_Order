package com.example.smartcanteen.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartcanteen.R;
import com.example.smartcanteen.database.DBHelper;
import com.example.smartcanteen.models.User;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPass;
    RadioGroup rgRole;
    DBHelper db;
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        rgRole = findViewById(R.id.rgRole);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            int selectedId = rgRole.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);
            String role = selectedRadioButton.getText().toString();

            User u = db.loginUser(email, pass, role);
            if(u!=null){
                Toast.makeText(this, "Welcome "+u.getName(), Toast.LENGTH_SHORT).show();
                if("Admin".equalsIgnoreCase(u.getRole())){
                    startActivity(new Intent(this, AdminDashboardActivity.class));
                } else {
                    Intent i = new Intent(this, DashboardActivity.class);
                    i.putExtra("userId", u.getId());
                    startActivity(i);
                }
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }
}
