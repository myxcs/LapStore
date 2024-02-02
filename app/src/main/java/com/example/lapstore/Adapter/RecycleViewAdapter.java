package com.example.lapstore.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapstore.R;

import java.util.List;
public class RecycleViewAdapter extends  RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    private List<String> dataList;

    public RecycleViewAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String data = dataList.get(position);
        holder.textViewItem.setText(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.textViewItem);
        }
    }
}
