package com.example.wetoo.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.Activity.BoardPage;
import com.example.wetoo.Adapter.MyTodoAdapter;
import com.example.wetoo.R;
import com.example.wetoo.Request.MyTodoRequest;
import com.example.wetoo.Request.TodoRequest;
import com.example.wetoo.Response.MyInfoResponse;
import com.example.wetoo.Response.MyTodoResponse;
import com.example.wetoo.Response.TodoResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {

    private TextView userName;
    private TextView userId;
    private TextView userAge;
    List<MyTodoResponse> myTodoResponses;
    private MyTodoAdapter myTodoAdapter;
    long tododate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        tododate = System.currentTimeMillis();
        Date date = new Date(tododate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String getTime = dateFormat.format(date);

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

        serviceApi.MyInfo(ActivityLogin.accesstoken).enqueue(new Callback<MyInfoResponse>() {
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

        MyTodoRequest myTodoRequest = new MyTodoRequest(getTime);


        serviceApi.myTodo(ActivityLogin.accesstoken, myTodoRequest).enqueue(new Callback<List<MyTodoResponse>>() {
            @Override
            public void onResponse(Call<List<MyTodoResponse>> call, Response<List<MyTodoResponse>> response) {
                if (response.code() == 200) {
                    myTodoResponses.addAll(response.body());
                    myTodoAdapter.notifyDataSetChanged();
                } else if (response.isSuccessful()) {
                    myTodoResponses.addAll(response.body());
                    myTodoAdapter.notifyDataSetChanged();
                } else
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<MyTodoResponse>> call, Throwable t) {
            }
        });

        myTodoResponses.add(new MyTodoResponse(BoardPage.id,"title","2022-07-30",false,false));

        return rootview;
    }
}