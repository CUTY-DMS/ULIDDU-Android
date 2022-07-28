package com.example.wetoo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.wetoo.Request.RegisterRequest;
import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private ServiceApi serviceApi;
    private String newPw;
    private String newId;
    private String name;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextBtSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        binding.nextBtLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register() {

        newId = binding.etId.getText().toString();
        newPw = binding.etPassword.getText().toString();
        name = binding.etName.getText().toString();
        age = Integer.parseInt(binding.etAge.getText().toString());

        if (newId.trim().length() == 0 || newPw.trim().length() == 0 || name.trim().length() == 0 || newId == null || newPw == null || name == null || age <= 0) {
            Toast.makeText(ActivityRegister.this, "올바른 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            registerResponse();
        }
    }

    public void registerResponse() {
        String name = binding.etName.getText().toString().trim();
        int age = Integer.parseInt(binding.etAge.getText().toString());
        String NewId = binding.etId.getText().toString().trim();
        String NewPw = binding.etPassword.getText().toString().trim();


        // 정보 저장
        RegisterRequest requestRegister = new RegisterRequest(name, age, NewId, NewPw);

        serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.register(requestRegister).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 204) {
                        Toast.makeText(ActivityRegister.this, "로그인을 해주세요", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(getApplicationContext(), ActivityLogin.class);
                        startActivity(myIntent);
                        finish();
                    } else {
                        Toast.makeText(ActivityRegister.this, "예기치 못한 오류가 발생했습니다.\n다시 시도해주세요!",Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() == 409) {
                    Toast.makeText(ActivityRegister.this, "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 400) {
                    Toast.makeText(ActivityRegister.this,"비밀번호는 8~15자여야 합니다.",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("TAG", "onFailure");
                Toast.makeText(ActivityRegister.this,"회원가입 에러 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }
}