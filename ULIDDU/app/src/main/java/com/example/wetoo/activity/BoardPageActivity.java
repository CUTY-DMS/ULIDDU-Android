package com.example.wetoo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.request.BoardRequest;
import com.example.wetoo.response.BoardResponse;
import com.example.wetoo.databinding.ActivityBoardPageBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardPageActivity extends AppCompatActivity {

    private ActivityBoardPageBinding binding;
    private Boolean ispublic;
    long tododate = System.currentTimeMillis();
    public static long id;

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
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    public void plus() {
        String title = binding.title.getText().toString();
        String content = binding.content.getText().toString();

        if (title.length() == 0)
            Toast.makeText(getApplicationContext(), "제목을 작성해주세요", Toast.LENGTH_SHORT).show();
        if (content.length() == 0)
            Toast.makeText(getApplicationContext(), "내용을 작성해주세요", Toast.LENGTH_SHORT).show();
        else
            post();
    }

    public void post() {
        String title = binding.title.getText().toString();
        String content = binding.content.getText().toString();
        ispublic = false;


        Date date = new Date(tododate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = dateFormat.format(date);

        BoardRequest boardRequest = new BoardRequest(title, content, ispublic, getTime);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        Call<BoardResponse> call = serviceApi.board(LoginActivity.accesstoken, boardRequest);

        call.enqueue(new Callback<BoardResponse>() {
            @Override
            public void onResponse(Call<BoardResponse> call, Response<BoardResponse> response) {
                if (response.isSuccessful()) {
                    id = response.body().getId();
                    Toast.makeText(BoardPageActivity.this, "게시글 등록이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                }
                /*Intent contentIntent = new Intent();
                contentIntent = new Intent(getApplicationContext(),DetailPage.class);
                contentIntent.putExtra("content",content);
                startActivity(contentIntent);*/
                finish();
            }

            @Override
            public void onFailure(Call<BoardResponse> call, Throwable t) {
            }
        });


    }

}