package com.example.wetoo.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.Activity.DetailPage;
import com.example.wetoo.Activity.Edit;
import com.example.wetoo.R;
import com.example.wetoo.Response.DetailResponse;
import com.example.wetoo.Response.MyTodoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTodoAdapter extends RecyclerView.Adapter<MyTodoAdapter.MyTodoViewHolder> {

    public static List<MyTodoResponse> list;

    public MyTodoAdapter(List<MyTodoResponse> list) {
        this.list = list;
    }

    public class MyTodoViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId;
        private CheckBox cbTitle;
        private TextView todoDate;
        private TextView iscompleted;
        private TextView isliked;
        private ImageView ivDelete;
        private ImageView ivEdit;

        public MyTodoViewHolder(@NonNull View view) {
            super(view);

            tvId = view.findViewById(R.id.tvuserid);
            cbTitle = view.findViewById(R.id.cbtitle);
            todoDate = view.findViewById(R.id.tvdate);
            iscompleted = view.findViewById(R.id.tvsuccess);
            isliked = view.findViewById(R.id.tvLike);
            ivDelete = view.findViewById(R.id.ivDelete);
            ivEdit = view.findViewById(R.id.ivEdit);

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

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Edit.class);
                v.getContext().startActivity(intent);
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

                serviceApi.delete(ActivityLogin.accesstoken, 1).enqueue(new Callback<Void>() {
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
                        Toast.makeText(v.getContext(),"삭제 되지 않았습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.tvId.setText(list.get(position).getId());
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

        if(list.get(position).getIscompleted() == true) {
            holder.iscompleted.setText("완료");
            holder.cbTitle.setChecked(true);
        }
        else {
            holder.iscompleted.setText("미완료");
            holder.cbTitle.setChecked(false);
        }
        if(list.get(position).getIsliked() == true)
            holder.isliked.setText("좋아요");
        else
            holder.isliked.setText("");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailPage.class);

                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("userid", list.get(position).getId());
                intent.putExtra("date", list.get(position).getTododate());
                intent.putExtra("iscompleted",list.get(position).getIscompleted());

                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
