package com.example.wetoo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.PluralsRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.R;
import com.example.wetoo.Response.SearchResponse;
import com.example.wetoo.Response.UserInfoResponse;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<UserInfoResponse> userInfoResponses;


    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView age;
        private TextView name;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tvid);
            age = itemView.findViewById(R.id.tvage);
            name = itemView.findViewById(R.id.tvsearchname);
        }
    }

    public SearchAdapter(List<UserInfoResponse> userInfoResponses) {
        this.userInfoResponses = userInfoResponses;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.name.setText(userInfoResponses.get(position).getName());
        holder.id.setText(userInfoResponses.get(position).getUserId());
        holder.age.setText(userInfoResponses.get(position).getAge());
    }

    @Override
    public int getItemCount() {
        return userInfoResponses.size();
    }
}
