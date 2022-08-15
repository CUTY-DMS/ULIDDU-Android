package com.example.wetoo.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.activity.BoardPageActivity;
import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.activity.LoginActivity;
import com.example.wetoo.activity.DetailActivity;
import com.example.wetoo.activity.EditActivity;
import com.example.wetoo.R;
import com.example.wetoo.response.DetailResponse;
import com.example.wetoo.response.MyTodoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTodoAdapter extends RecyclerView.Adapter<MyTodoAdapter.MyTodoViewHolder> {

    public static ArrayList<MyTodoResponse> list;

    public MyTodoAdapter(ArrayList<MyTodoResponse> list) {
        this.list = list;
    }

    public class MyTodoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private CheckBox cbTitle;
        private TextView todoDate;
        private TextView iscompleted;
        private ImageView isliked;
        private ImageView ivDelete;
        private ImageView ivEdit;

        public MyTodoViewHolder(@NonNull View view) {
            super(view);

            tvId = view.findViewById(R.id.tvmytodoid);
            cbTitle = view.findViewById(R.id.cbtitle);
            todoDate = view.findViewById(R.id.tvdate);
            iscompleted = view.findViewById(R.id.tvsuccess);
            isliked = view.findViewById(R.id.ivLike);
            ivDelete = view.findViewById(R.id.ivDelete);
            ivEdit = view.findViewById(R.id.ivEdit);

        }
    }

    @NonNull
    @Override
    public MyTodoAdapter.MyTodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytodolist, parent, false);
        return new MyTodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTodoAdapter.MyTodoViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

                serviceApi.delete(LoginActivity.accesstoken, 12).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(v.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(v.getContext(), "삭제 되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.tvId.setText(String.valueOf(list.get(position).getId()));
        holder.cbTitle.setText(list.get(position).getTitle());
        holder.todoDate.setText(list.get(position).getTododate());


        holder.cbTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cbTitle.isChecked()) {
                    holder.iscompleted.setText("완료");
                } else {
                    holder.iscompleted.setText("미완료");
                }
            }
        });

        if (list.get(position).getIscompleted() == true) {
            holder.iscompleted.setText("완료");
            holder.cbTitle.setChecked(true);
        } else {
            holder.iscompleted.setText("미완료");
            holder.cbTitle.setChecked(false);
        }
        holder.isliked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getIsliked() == true) {
                    holder.isliked.setImageResource(R.drawable.ic_baseline_favorite_24);
                } else {
                    holder.isliked.setImageResource(R.drawable.ic_favorite);
                }
            }
        });

        if (list.get(position).getIsliked() == true) {
            holder.isliked.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            holder.isliked.setImageResource(R.drawable.ic_favorite);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);

                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("date", list.get(position).getTododate());
                intent.putExtra("iscompleted", list.get(position).getIscompleted());


                ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

                serviceApi.detail(LoginActivity.accesstoken, BoardPageActivity.id).enqueue(new Callback<DetailResponse>() {
                    @Override
                    public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<DetailResponse> call, Throwable t) {

                    }
                });

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
