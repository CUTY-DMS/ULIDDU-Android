package com.example.wetoo.Activity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wetoo.API.RegisterRequest;
import com.example.wetoo.API.RegisterResponse;
import com.example.wetoo.BottomNav;
import com.example.wetoo.RetrofitClient;
import com.example.wetoo.ServiceApi;
import com.example.wetoo.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private RetrofitClient retrofitClient;
    private ServiceApi serviceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.NextbtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                Register();
            }
        });
    }

    private void Register() {
        String NewId = binding.etId.getText().toString();
        String NewPw = binding.etPassword.getText().toString();
        String name = binding.etName.getText().toString();
        int age = Integer.parseInt(binding.etAge.getText().toString());

        if (NewId.trim().length() == 0 || NewPw.trim().length() == 0 || name.trim().length() == 0 || age <= 0) {
            Toast.makeText(ActivityRegister.this, "올바른 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            RegisterResponse();
        }
    }

    public void RegisterResponse() {
        String NewId = binding.etId.getText().toString().trim();
        String name = binding.etName.getText().toString().trim();
        String NewPw = binding.etPassword.getText().toString().trim();
        int age = Integer.parseInt(binding.etAge.getText().toString());


        // 정보 저장
        RegisterRequest requestRegister = new RegisterRequest(name, NewId, NewPw, age);

        serviceApi.Register(requestRegister).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200) {
                        Toast.makeText(ActivityRegister.this, "로그인을 해주세요", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                        startActivity(intent);
                    }
                }  else if (response.code() == 409) {
                    Toast.makeText(ActivityRegister.this,"중복된 아이디입니다.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(ActivityRegister.this,"회원가입 에러 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }
}