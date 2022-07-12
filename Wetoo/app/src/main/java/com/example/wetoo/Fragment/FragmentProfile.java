package com.example.wetoo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.UserInfo.MyInfoResponse;
import com.example.wetoo.databinding.FragmentProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {

    private FragmentProfileBinding binding;
    private String userName;
    private String userId;
    private int userAge;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater ,container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getUserInfo() {

        userName = binding.tvName.getText().toString().trim();
        userId = binding.tvId.getText().toString().trim();
        userAge = Integer.parseInt(binding.tvAge.getText().toString());

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);


        Call<MyInfoResponse> call = serviceApi.userInfo(userName, userId, userAge);

        call.enqueue(new Callback<MyInfoResponse>() {
            @Override
            public void onResponse(Call<MyInfoResponse> call, Response<MyInfoResponse> response) {
                int result = response.code();

                if(result == 200) {
                    binding.tvName.getText();
                    binding.tvId.getText();
                    binding.tvAge.getText();
                }
            }

            @Override
            public void onFailure(Call<MyInfoResponse> call, Throwable t) {
            }
        });

    }
}