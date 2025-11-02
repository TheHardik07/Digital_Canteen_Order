package com.example.smartcanteen.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.smartcanteen.R;
import com.example.smartcanteen.adapters.MenuAdapter;
import com.example.smartcanteen.database.DBHelper;
import com.example.smartcanteen.models.MenuItem;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    DBHelper db;
    RecyclerView rv;
    MenuAdapter adapter;
    @Override protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.activity_menu);
        db = new DBHelper(this);
        rv = findViewById(R.id.recyclerMenu);
        rv.setLayoutManager(new LinearLayoutManager(this));
        refresh();
    }

    private void refresh(){
        List<MenuItem> items = db.getAllMenuItems();
        adapter = new MenuAdapter(this, items);
        rv.setAdapter(adapter);
    }
}
