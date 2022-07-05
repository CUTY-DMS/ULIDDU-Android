package com.example.wetoo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wetoo.API.LoginRequest;
import com.example.wetoo.API.LoginResponse;
import com.example.wetoo.API.RegisterResponse;
import com.example.wetoo.BottomNav;
import com.example.wetoo.Fragment.FragmentHome;
import com.example.wetoo.RetrofitClient;
import com.example.wetoo.ServiceApi;
import com.example.wetoo.databinding.ActivityLoginBinding;
import com.example.wetoo.databinding.ActivityMainBinding;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public static String username;
    public static String password;
    public static String token;

    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.NextbtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        username = binding.etId.getText().toString();
        password = binding.etPassword.getText().toString();

        if(username.trim().length() == 0 || password.trim().length() == 0 || username == null || password == null){
            Toast.makeText(ActivityLogin.this, "올바른 로그인 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            LoginResponse();
        }
    }

    public void LoginResponse() {

        String username = binding.etId.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        // 정보 저장
        LoginRequest loginRequest = new LoginRequest(username,password);

        retrofitClient = RetrofitClient.getInstance();
        serviceApi = RetrofitClient.getRetrofitInterface();

        serviceApi.Login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null){

                    LoginResponse result = response.body();

                    if (result.getCode() == 200) {
                        Toast.makeText(ActivityLogin.this, "환영합니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BottomNav.class);
                        startActivity(intent);
                    }
                    else if (result.getCode() == 404) {
                        Toast.makeText(ActivityLogin.this, "아이디 또는 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(ActivityLogin.this, "서버 에러!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}