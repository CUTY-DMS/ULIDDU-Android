package com.example.wetoo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.R;
import com.example.wetoo.Response.SearchResponse;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<SearchResponse> list;


    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView id;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvid);
        }
    }

    public void filterList(List<SearchResponse> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    public SearchAdapter(List<SearchResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.id.setText(list.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
