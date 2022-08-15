package com.example.wetoo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.response.DetailResponse;
import com.example.wetoo.databinding.ActivityDetailPageBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();

        String title = extras.getString("title");
        String userId = extras.getString("id");
        String date = extras.getString("date");
        String iscompleted = extras.getString("iscompleted");

        binding.cbTitle.setText(title);
        if (binding.cbTitle.isChecked()) {
            binding.tviscompleted.setText("완료");
        } else {
            binding.tviscompleted.setText("미완료");
        }

        binding.tviscompleted.setText(iscompleted);
        binding.tvUserId.setText(userId);
        binding.tvcreatedDate.setText(date);

        content();



        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.detail(LoginActivity.accesstoken, 6).enqueue(new Callback<DetailResponse>() {
            @Override
            public void onResponse(Call<DetailResponse> call, Response<DetailResponse> response) {

            }
            @Override
            public void onFailure(Call<DetailResponse> call, Throwable t) {

            }
        });
    }
    private void content() {
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");

        binding.tvContent.setText(content);
    }
}