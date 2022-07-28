package com.example.wetoo.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.Activity.DetailPage;
import com.example.wetoo.R;
import com.example.wetoo.Response.DetailResponse;
import com.example.wetoo.Response.TodoResponse;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private ArrayList<TodoResponse> list;

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView title;
        private TextView date;
        private TextView iscompleted;
        private TextView isliked;

        public TodoViewHolder(@NonNull View view) {
            super(view);
            id = view.findViewById(R.id.tvuserid);
            title = view.findViewById(R.id.tvtitle);
            date = view.findViewById(R.id.tvDate);
            iscompleted = view.findViewById(R.id.tvsuccess);
            isliked = view.findViewById(R.id.ivLike);
        }
    }

    public TodoAdapter(ArrayList<TodoResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list, parent, false);

        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {

        holder.id.setText(list.get(position).getId());
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getTodoDate());

        if (list.get(position).getIscompleted() == true) {
            holder.iscompleted.setText("완료");
        } else {
            holder.iscompleted.setText("미완료");
        }

        if (list.get(position).getIsliked() == true) {
            holder.isliked.setText("좋아요");
        } else {
            holder.isliked.setText("");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailPage.class);

                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("userid", list.get(position).getId());
                intent.putExtra("date", list.get(position).getTodoDate());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

