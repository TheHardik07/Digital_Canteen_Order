package com.example.smartcanteen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcanteen.R;
import com.example.smartcanteen.models.Order;

import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderId.setText(String.format(Locale.getDefault(), "Order #%d", order.getId()));
        holder.orderDate.setText(order.getDate());
        holder.orderSummary.setText(order.getSummary()); // Assuming Order model has getSummary()
        holder.orderStatus.setText(String.format("Status: %s", order.getStatus()));
        holder.orderTotal.setText(String.format(Locale.getDefault(), "Total: ₹%.2f", order.getTotal()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderId, orderDate, orderSummary, orderStatus, orderTotal;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderSummary = itemView.findViewById(R.id.orderSummary);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            orderTotal = itemView.findViewById(R.id.orderTotal);
        }
    }
}
