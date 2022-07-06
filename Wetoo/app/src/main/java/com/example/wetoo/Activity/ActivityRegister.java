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
import com.example.wetoo.ApiProvider;
import com.example.wetoo.ServiceApi;
import com.example.wetoo.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String NewId;
    private String NewPw;
    private String name;
    private Editable age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextBtSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                register();
            }
        });

        binding.nextBtLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register() {

        NewId = binding.etId.getText().toString();
        NewPw = binding.etPassword.getText().toString();
        name = binding.etName.getText().toString();
        age = binding.etAge.getText();

        if (NewId.trim().length() == 0 || NewPw.trim().length() == 0 || name.trim().length() == 0 || NewId == null || NewPw == null || name == null || age == null) {
            Toast.makeText(ActivityRegister.this, "올바른 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            registerResponse();
        }
    }

    public void registerResponse() {
        String NewId = binding.etId.getText().toString().trim();
        String name = binding.etName.getText().toString().trim();
        String NewPw = binding.etPassword.getText().toString().trim();
        int age = Integer.parseInt(binding.etAge.getText().toString());


        // 정보 저장
        RegisterRequest requestRegister = new RegisterRequest(name, NewId, NewPw, age);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.register(requestRegister).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()){
                    if (response.code() == 200 && response.body() != null) {
                        Toast.makeText(ActivityRegister.this, "로그인을 해주세요", Toast.LENGTH_SHORT).show();
                        Intent Intent = new Intent(getApplicationContext(), ActivityLogin.class);
                        startActivity(Intent);
                        finish();
                    } else if (response.code() == 409) {
                        Toast.makeText(ActivityRegister.this,"중복된 아이디입니다.",Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 400) {
                        Toast.makeText(ActivityRegister.this,"비밀번호는 8~15자여야 합니다.",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ActivityRegister.this, "예기치 못한 오류가 발생했습니다.\n다시 시도해주세요",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(ActivityRegister.this,"회원가입 에러 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }
}