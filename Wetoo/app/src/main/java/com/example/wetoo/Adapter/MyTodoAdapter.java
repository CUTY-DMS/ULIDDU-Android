package com.example.wetoo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.R;
import com.example.wetoo.Response.MyTodoResponse;

import java.util.List;

public class MyTodoAdapter extends RecyclerView.Adapter<MyTodoAdapter.MyTodoViewHolder> {

    private List<MyTodoResponse> list;

    public MyTodoAdapter(List<MyTodoResponse> list) {
        this.list = list;
    }

    public class MyTodoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private TextView tvTitle;
        private TextView todoDate;
        private TextView iscompleted;
        private TextView isliked;

        public MyTodoViewHolder(@NonNull View view) {
            super(view);

            tvId = view.findViewById(R.id.tvuserid);
            tvTitle = view.findViewById(R.id.tvtitle);
            todoDate = view.findViewById(R.id.tvdate);
            iscompleted = view.findViewById(R.id.tvsuccess);
            isliked = view.findViewById(R.id.tvLike);

        }
    }

    @NonNull
    @Override
    public MyTodoAdapter.MyTodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list,parent,false);
        return new MyTodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTodoAdapter.MyTodoViewHolder holder, int position) {
        holder.tvId.setText(list.get(position).getId());
        holder.tvTitle.setText(list.get(position).getTitle());
        holder.todoDate.setText(list.get(position).getTododate());
        if(list.get(position).getIscompleted() == true)
            holder.iscompleted.setText("완료");
        else
            holder.iscompleted.setText("미완료");
        if(list.get(position).getIsliked() == true)
            holder.isliked.setText("좋아요");
        else
            holder.isliked.setText("");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
