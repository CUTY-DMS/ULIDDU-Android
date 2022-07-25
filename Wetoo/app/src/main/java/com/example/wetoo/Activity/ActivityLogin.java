package com.example.wetoo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wetoo.BottomNav;
import com.example.wetoo.LoginRegister.LoginRequest;
import com.example.wetoo.LoginRegister.LoginResponse;
import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public String userId;
    public String password;
    public static String accesstoken;
    public static String refreshtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextBtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityRegister.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void login() {
        userId = binding.etId.getText().toString();
        password = binding.etPassword.getText().toString();

        if(userId.trim().length() == 0 || password.trim().length() == 0 || userId == null || password == null){
            Toast.makeText(ActivityLogin.this, "올바른 로그인 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            loginResponse();
        }
    }

    public void loginResponse() {

        String[] userId = {binding.etId.getText().toString().trim()};
        String password = binding.etPassword.getText().toString().trim();

        // 정보 저장
        LoginRequest loginRequest = new LoginRequest(userId[0],password);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null){
                    if (response.code() == 200) {
                        accesstoken = "Bearer "+response.body().getAccessToken();
                        refreshtoken = response.body().getRefreshToken();

                        Toast.makeText(ActivityLogin.this, "환영합니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BottomNav.class);
                        startActivity(intent);
                    }
                    else if (response.code() == 404) {
                        Toast.makeText(ActivityLogin.this, "아이디 또는 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ActivityLogin.this, "예기치 못한 오류가 발생하였습니다.\n아이디 또는 비밀번호를 다시 확인해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(ActivityLogin.this, "서버 에러!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}