package com.example.wetoo.Board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.R;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ArrayList<Post> listData = new ArrayList<>();

    public BoardAdapter(ArrayList<Post> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public BoardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.board_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(Post data) {
        listData.add(data);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvtitle);
            content = itemView.findViewById(R.id.tvBoardContent);
            date = itemView.findViewById(R.id.tvTodoDate);
        }

        void onBind(Post data) {
            title.setText(data.getTitle());
            content.setText(data.getContent());
            date.setText(data.getDate());
        }
    }
}
