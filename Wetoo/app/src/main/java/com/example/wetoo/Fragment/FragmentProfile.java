package com.example.wetoo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.Board.BoardRequest;
import com.example.wetoo.R;
import com.example.wetoo.UserInfo.MyInfoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {

    private TextView userName;
    private TextView userId;
    private TextView userAge;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        userName = rootview.findViewById(R.id.tvName);
        userId = rootview.findViewById(R.id.tvId);
        userAge = rootview.findViewById(R.id.tvAge);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.MyInfo(ActivityLogin.accesstoken).enqueue(new Callback<MyInfoResponse>() {
            @Override
            public void onResponse(Call<MyInfoResponse> call, Response<MyInfoResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    userName.setText(response.body().getUserName());
                    userId.setText(response.body().getUserId());
                    userAge.setText(String.valueOf(response.body().getUserAge()));
                }
            }

            @Override
            public void onFailure(Call<MyInfoResponse> call, Throwable t) {
                Log.e("Tag","error");
                System.out.println("오류발생");
            }
        });
        return rootview;
    }
}