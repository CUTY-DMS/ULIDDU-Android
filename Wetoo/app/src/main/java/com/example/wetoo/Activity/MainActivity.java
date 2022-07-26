package com.example.wetoo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.wetoo.BottomNav;
import com.example.wetoo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(LoginIntent);
            }
        });
        binding.btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SingUpIntent = new Intent(getApplicationContext(), ActivityRegister.class);
                startActivity(SingUpIntent);
            }
        });
    }
}