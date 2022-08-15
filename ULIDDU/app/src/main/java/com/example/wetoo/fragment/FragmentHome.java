package com.example.wetoo.fragment;

import static java.lang.String.format;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;

import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.activity.LoginActivity;
import com.example.wetoo.activity.BoardPageActivity;
import com.example.wetoo.R;
import com.example.wetoo.request.TodoRequest;
import com.example.wetoo.response.TodoResponse;
import com.example.wetoo.adapter.TodoAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TodoAdapter todoAdapter;
    private List<TodoResponse> todoResponsesList;
    private ImageView ivPost;
    private CalendarView calendarView;
    long tododate = System.currentTimeMillis();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootview.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        calendarView = rootview.findViewById(R.id.calendarView);
        ivPost = rootview.findViewById(R.id.ivPost);

        ivPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardPageActivity.class);
                startActivity(intent);
            }
        });

        Date date = new Date(tododate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String getTime = dateFormat.format(date);

        todoResponsesList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        todoAdapter = new TodoAdapter(todoResponsesList);
        recyclerView.setAdapter(todoAdapter);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.todo(LoginActivity.accesstoken, 4, getTime).enqueue(new Callback<ArrayList<TodoResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TodoResponse>> call, Response<ArrayList<TodoResponse>> response) {
                if (response.isSuccessful()) {
                    todoResponsesList.addAll(response.body());
                    todoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TodoResponse>> call, Throwable t) {
            }
        });


        return rootview;
    }
}