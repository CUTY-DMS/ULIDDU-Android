package com.example.wetoo.Board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.R;
import com.example.wetoo.databinding.ActivityBoardPageBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardPage extends AppCompatActivity {

    private ActivityBoardPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityBoardPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plus();
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    public void plus() {
        String title = binding.title.getText().toString();
        String content = binding.content.getText().toString();

        if(title.length() == 0)
            Toast.makeText(getApplicationContext(),"제목을 작성해주세요", Toast.LENGTH_SHORT).show();
        if(content.length()==0)
            Toast.makeText(getApplicationContext(),"내용을 작성해주세요",Toast.LENGTH_SHORT).show();
        else
            post();
    }
    public void post() {
        String title = binding.title.getText().toString();
        String content = binding.content.getText().toString();
        String ispublic = binding.ispublic.getText().toString();

        BoardRequest boardRequest = new BoardRequest(title,content,ispublic);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        Call<BoardRequest> call = serviceApi.board(ActivityLogin.accesstoken, boardRequest);
        call.enqueue(new Callback<BoardRequest>() {
            @Override
            public void onResponse(Call<BoardRequest> call, Response<BoardRequest> response) {
                Toast.makeText(BoardPage.this, "게시글 등록이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<BoardRequest> call, Throwable t) {
                finish();
            }
        });


    }

}