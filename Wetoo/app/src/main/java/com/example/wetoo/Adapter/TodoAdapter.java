package com.example.wetoo.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.Activity.DetailPage;
import com.example.wetoo.Activity.Edit;
import com.example.wetoo.Fragment.FragmentHome;
import com.example.wetoo.R;
import com.example.wetoo.Response.DetailResponse;
import com.example.wetoo.Response.TodoResponse;
import com.google.protobuf.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    public static List<TodoResponse> list;

    public TodoAdapter(List<TodoResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list, parent, false);
        return new TodoViewHolder(view);
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView id;
        private TextView title;
        private TextView date;
        private TextView iscompleted;
        private TextView isliked;

        public TodoViewHolder(@NonNull View itemview) {
            super(itemview);
            id = itemview.findViewById(R.id.tvid);
            title = itemview.findViewById(R.id.tvTitle);
            date = itemview.findViewById(R.id.tvdate);
            iscompleted = itemview.findViewById(R.id.tvsuccess);
            isliked = itemview.findViewById(R.id.tvLike);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.id.setText((int) list.get(position).getId());
        holder.title.setText(list.get(position).getTitle());
        holder.date.setText(list.get(position).getTodoDate());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        if (list.get(position).getIscompleted() == true) { {
            holder.iscompleted.setText("완료");
        }
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

                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("userid", list.get(position).getId());
                intent.putExtra("date", list.get(position).getTodoDate());
                intent.putExtra("iscompleted",list.get(position).getIscompleted());

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}

