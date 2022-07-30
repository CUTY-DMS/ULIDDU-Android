package com.example.wetoo.Fragment;

import static java.lang.String.format;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.Activity.BoardPage;
import com.example.wetoo.Activity.Edit;
import com.example.wetoo.R;
import com.example.wetoo.Request.TodoRequest;
import com.example.wetoo.Response.TodoResponse;
import com.example.wetoo.Adapter.TodoAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TodoAdapter todoAdapter;
    List<TodoResponse> todoResponsesList;
    private ImageView ivPost;
    public static CalendarView calendarView;
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
                Intent intent = new Intent(getActivity(), BoardPage.class);
                startActivity(intent);
            }
        });

        Date date = new Date(tododate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        String getTime = dateFormat.format(date);

        TodoRequest todoRequest = new TodoRequest(getTime);

        todoResponsesList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        todoAdapter = new TodoAdapter(todoResponsesList);
        recyclerView.setAdapter(todoAdapter);

        /*todoResponsesList.add(new TodoResponse("haeun","안녕하세요","2022-07-29",false,true));
        todoResponsesList.add(new TodoResponse("rlaisqls","안녕하세요","2022-07-29",false,true));
        todoResponsesList.add(new TodoResponse("rlaisqls","안녕하세요","2022-07-29",true,true));
        todoResponsesList.add(new TodoResponse("haeun","안녕하세요","2022-07-29",false,true));
        todoResponsesList.add(new TodoResponse("haeun","안녕하세요","2022-07-29",false,true));
        todoResponsesList.add(new TodoResponse("haeun","안녕하세요","2022-07-29",true,true));
        todoResponsesList.add(new TodoResponse("haeun","안녕하세요","2022-07-29",true,true));
        todoResponsesList.add(new TodoResponse("haeun","안녕하세요","2022-07-29",false,true));*/

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.todo(ActivityLogin.accesstoken, 6, todoRequest).enqueue(new Callback<List<TodoResponse>>() {
            @Override
            public void onResponse(Call<List<TodoResponse>> call, Response<List<TodoResponse>> response) {
                    Log.e("error","errrrrrorrr");
                    todoResponsesList.addAll(response.body());
                    todoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TodoResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "ToDoList 를 불러오지 못했습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        return rootview;
    }
}