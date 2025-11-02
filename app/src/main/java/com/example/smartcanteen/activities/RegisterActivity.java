package com.example.smartcanteen.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartcanteen.R;
import com.example.smartcanteen.database.DBHelper;

public class RegisterActivity extends AppCompatActivity {
    DBHelper db;
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_register);
        db = new DBHelper(this);
        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPass = findViewById(R.id.etPass);
        RadioGroup rg = findViewById(R.id.rgRole);
        Button btn = findViewById(R.id.btnRegister);
        btn.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            String role = rg.getCheckedRadioButtonId() == R.id.rbAdmin ? "Admin" : "Student";
            if(name.isEmpty()||email.isEmpty()||pass.isEmpty()){ Toast.makeText(this,"Fill all",Toast.LENGTH_SHORT).show(); return; }
            boolean ok = db.registerUser(name,email,pass,role);
            if(ok){ Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show(); finish(); }
            else Toast.makeText(this,"Register failed (maybe email exists)",Toast.LENGTH_SHORT).show();
        });
    }
}