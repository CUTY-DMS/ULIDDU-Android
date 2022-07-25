package com.example.wetoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.Board.BoardRequest;
import com.example.wetoo.Board.BoardResponse;
import com.example.wetoo.Fragment.FragmentHome;
import com.example.wetoo.databinding.ActivityEditPageBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPage extends AppCompatActivity {

    private ActivityEditPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHome fragmentHome = new FragmentHome();
                board();
            }
        });
    }
    public void board() {
        String title = binding.etTitle.getText().toString();
        String content = binding.etContent.getText().toString();

        if (binding.etTitle.getText().toString().length() == 0) {
            Toast.makeText(EditPage.this,"제목을 입력해주세요",Toast.LENGTH_SHORT).show();
        } if (binding.etContent.getText().toString().length() == 0) {
            Toast.makeText(EditPage.this, "내용을 입력해주세요",Toast.LENGTH_SHORT).show();
        } else {
            boardStart();
        }
    }

    public void boardStart() {
        String title = binding.etTitle.getText().toString();
        String content = binding.etContent.getText().toString();
        String ispublic = binding.ispublic.getText().toString();

        BoardRequest boardRequest = new BoardRequest(title, content, ispublic);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        Call<BoardResponse> call = serviceApi.board(ActivityLogin.accesstoken, boardRequest);

        call.enqueue(new Callback<BoardResponse>() {
            @Override
            public void onResponse(Call<BoardResponse> call, Response<BoardResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditPage.this,"글이 등록되었습니다",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), FragmentHome.class);
                    intent.putExtra("new",true);
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<BoardResponse> call, Throwable t) {
                Toast.makeText(EditPage.this, "글이 등록되지 않았습니다",Toast.LENGTH_SHORT).show();
            }
        });
    }
}