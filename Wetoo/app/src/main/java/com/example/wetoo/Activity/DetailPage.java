package com.example.wetoo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wetoo.R;
import com.example.wetoo.databinding.ActivityDetailPageBinding;

public class DetailPage extends AppCompatActivity {

    private ActivityDetailPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();

        String title = extras.getString("title");
        String userId = extras.getString("userid");
        String date = extras.getString("date");

        binding.tvTitle.setText(title);
        binding.tvUserId.setText(userId);
        binding.tvcreatedDate.setText(date);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");

        binding.tvContent.setText(content);
    }
}