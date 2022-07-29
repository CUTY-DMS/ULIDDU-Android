package com.example.wetoo.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.R;
import com.example.wetoo.Response.MyTodoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        private ImageView ivDelete;

        public MyTodoViewHolder(@NonNull View view) {
            super(view);

            tvId = view.findViewById(R.id.tvuserid);
            tvTitle = view.findViewById(R.id.tvtitle);
            todoDate = view.findViewById(R.id.tvdate);
            iscompleted = view.findViewById(R.id.tvsuccess);
            isliked = view.findViewById(R.id.tvLike);
            ivDelete = view.findViewById(R.id.ivDelete);

        }
    }

    @NonNull
    @Override
    public MyTodoAdapter.MyTodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytodolist,parent,false);
        return new MyTodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTodoAdapter.MyTodoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

                serviceApi.delete(ActivityLogin.accesstoken, Long.parseLong(ActivityLogin.userId)).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            list.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(v.getContext(), "삭제되었습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
            }
        });

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
