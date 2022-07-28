package com.example.wetoo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.R;
import com.example.wetoo.Request.BoardRequest;
import com.example.wetoo.databinding.ActivityBoardPageBinding;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardPage extends AppCompatActivity {

    private ActivityBoardPageBinding binding;
    private String tododate;
    private Boolean ispublic;
    private CalendarView calendarView;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityBoardPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        calendarView = findViewById(R.layout.fragment_home).findViewById(R.id.calendarView);

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
        ispublic = false;
        Calendar calendar = Calendar.getInstance();
        //캘린더뷰에서 날짜값 읽어오기
        Date date = new Date(calendarView.getDate());
        //캘린더 객체에 캘린더뷰 값을 넣음
        calendar.setTime(date);

        tododate = Integer.toString(calendar.get(Calendar.YEAR)) + "-" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + "-" + Integer.toString(calendar.get(Calendar.DATE));

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

        BoardRequest boardRequest = new BoardRequest(title, content, ispublic, tododate);

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
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
            }
        });


    }

}