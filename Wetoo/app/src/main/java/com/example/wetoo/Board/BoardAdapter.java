package com.example.wetoo.Board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.R;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.CustomViewHolder> {

    private ArrayList<Post> postArrayList;
    private Context context;

    public BoardAdapter(ArrayList<Post> postArrayList, Context context) {
        this.postArrayList = postArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BoardAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.CustomViewHolder holder, int position) {
        holder.tvTitle.setText(postArrayList.get(position).getTitle());
        holder.tvUserId.setText(postArrayList.get(position).getUserId());
        holder.tvTimestamp.setText(postArrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return (postArrayList != null ? postArrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserId;
        private TextView tvTimestamp;
        private TextView tvTitle;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvUserId = itemView.findViewById(R.id.tvUserId);
            this.tvTimestamp = itemView.findViewById(R.id.tvTimestamp);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
