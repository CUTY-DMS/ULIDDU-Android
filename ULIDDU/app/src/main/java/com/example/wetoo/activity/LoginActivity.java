package com.example.wetoo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.wetoo.fragment.FragmentHome;
import com.example.wetoo.request.LoginRequest;
import com.example.wetoo.response.LoginResponse;
import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    public String password;
    public static String accesstoken;
    public static String refreshtoken;
    FragmentHome fragmentHome;

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
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void login() {
        String userId = binding.etId.getText().toString();
        password = binding.etPassword.getText().toString();

        if(userId.trim().length() == 0 || password.trim().length() == 0 || userId == null || password == null){
            Toast.makeText(LoginActivity.this, "올바른 로그인 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            loginResponse();
        }
    }

    public void loginResponse() {

        String userId = binding.etId.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        // 정보 저장
        LoginRequest loginRequest = new LoginRequest(userId,password);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        /*Bundle bundle = new Bundle();
        //1. 입력 메시지
        userId = binding.etId.getText().toString();
        //2. 데이터 담기
        bundle.putString("userid",userId);
        //3. 프래그먼트 선언
        FragmentHome fragmentHome = new FragmentHome();
        //4. 프래그먼트 데이터 넘기기
        fragmentHome.setArguments(bundle);*/


        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    if (response.code() == 200) {
                        accesstoken = "Bearer "+response.body().getAccessToken();
                        refreshtoken = response.body().getRefreshToken();

                        Toast.makeText(LoginActivity.this, "환영합니다", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), BottomNav.class);
                        startActivity(intent);
                    }
                    else if (response.code() == 404) {
                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "예기치 못한 오류가 발생하였습니다.\n아이디 또는 비밀번호를 다시 확인해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "서버 에러!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}