package com.example.wetoo.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.activity.LoginActivity;
import com.example.wetoo.activity.BoardPageActivity;
import com.example.wetoo.adapter.MyTodoAdapter;
import com.example.wetoo.R;
import com.example.wetoo.request.MyTodoRequest;
import com.example.wetoo.response.MyInfoResponse;
import com.example.wetoo.response.MyTodoResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {

    private TextView userName;
    private TextView userId;
    private TextView userAge;
    ArrayList<MyTodoResponse> myTodoResponses;
    private MyTodoAdapter myTodoAdapter;
    long tododate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);



        myTodoResponses = new ArrayList<>();
        RecyclerView recyclerView = rootview.findViewById(R.id.myRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);


        myTodoAdapter = new MyTodoAdapter(myTodoResponses);

        recyclerView.setAdapter(myTodoAdapter);

        userName = rootview.findViewById(R.id.tvName);
        userId = rootview.findViewById(R.id.tvId);
        userAge = rootview.findViewById(R.id.tvAge);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.MyInfo(LoginActivity.accesstoken).enqueue(new Callback<MyInfoResponse>() {
            @Override
            public void onResponse(Call<MyInfoResponse> call, Response<MyInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userName.setText("이름 : " + response.body().getUserName());
                    userId.setText("아이디 : " + response.body().getUserId());
                    userAge.setText("나이 : " + (String.valueOf(response.body().getUserAge())));
                }
            }

            @Override
            public void onFailure(Call<MyInfoResponse> call, Throwable t) {
                Log.e("Tag", "error");
                System.out.println("오류발생");
            }
        });

        tododate = System.currentTimeMillis();
        Date date = new Date(tododate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String getTime = dateFormat.format(date);

        myTodoResponses.add(new MyTodoResponse(1,"title","2022-08-16",false,true));

        serviceApi.myTodo(LoginActivity.accesstoken, getTime).enqueue(new Callback<ArrayList<MyTodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<MyTodoResponse>> call, Response<ArrayList<MyTodoResponse>> response) {
                if (response.code() == 200) {
                    myTodoResponses.addAll(response.body());
                    myTodoAdapter.notifyDataSetChanged();
                } else if (response.isSuccessful()) {
                    myTodoResponses.addAll(response.body());
                    myTodoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MyTodoResponse>> call, Throwable t) {
                Log.e("eeeeee","error");
            }
        });

        return rootview;
    }
}