package com.example.wetoo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.R;
import com.example.wetoo.response.UserInfoResponse;
import com.google.firebase.firestore.auth.User;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    public static List<UserInfoResponse> list;

    public SearchAdapter(List<UserInfoResponse> list) {
        this.list = list;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView userId;
        private TextView userAge;
        private TextView userName;

        public SearchViewHolder(@NonNull View view) {
            super(view);

            userId = view.findViewById(R.id.userId);
            userAge = view.findViewById(R.id.userAge);
            userName = view.findViewById(R.id.username);
        }
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {

        holder.userName.setText(list.get(position).getName());
        holder.userAge.setText(String.valueOf(list.get(position).getAge()));
        holder.userId.setText(list.get(position).getUserid());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
