package com.example.smartcanteen.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcanteen.R;
import com.example.smartcanteen.models.MenuItem;
import com.example.smartcanteen.activities.ItemDetailActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private Context context;
    private List<MenuItem> list;

    public MenuAdapter(Context ctx, List<MenuItem> items){
        this.context = ctx;
        this.list = items;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position){
        final MenuItem item = list.get(position);
        holder.name.setText(item.getName());
        holder.price.setText("₹" + String.format("%.2f", item.getPrice()));
        // If using drawable name in item.getImage()
        int resId = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());
        if(resId!=0) holder.iv.setImageResource(resId);
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, ItemDetailActivity.class);
            i.putExtra("itemId", item.getId());
            context.startActivity(i);
        });
    }

    @Override public int getItemCount(){ return list.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        ImageView iv;
        public ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.item_price);
            iv = itemView.findViewById(R.id.item_image);
        }
    }
}
