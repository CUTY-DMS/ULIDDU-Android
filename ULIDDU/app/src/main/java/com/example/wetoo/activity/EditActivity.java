package com.example.wetoo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.request.EditRequest;
import com.example.wetoo.response.EditResponse;
import com.example.wetoo.databinding.ActivityEditBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
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

    private void edit() {
        String title = binding.title.getText().toString();
        String content = binding.content.getText().toString();

        if (title.length() == 0)
            Toast.makeText(getApplicationContext(), "제목을 작성해주세요", Toast.LENGTH_SHORT).show();
        if (content.length() == 0)
            Toast.makeText(getApplicationContext(), "내용을 작성해주세요", Toast.LENGTH_SHORT).show();
        else{
            editStart();
        }
    }

    private void editStart() {
        String title = binding.title.getText().toString();
        String content = binding.content.getText().toString();

        // 정보 저장
        EditRequest editRequest = new EditRequest(title,content);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);


        Call call = serviceApi.edit(LoginActivity.accesstoken, 4, editRequest);
        call.enqueue(new Callback<EditResponse>() {
            @Override
            public void onResponse(Call<EditResponse> call, Response<EditResponse> response) {
                if (response.isSuccessful())
                Toast.makeText(EditActivity.this,"게시글이 수정되었습니다",Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<EditResponse> call, Throwable t) {
                Toast.makeText(EditActivity.this, "게시글이 수정되지 않았습니다.\n다시 시도해주세요",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}