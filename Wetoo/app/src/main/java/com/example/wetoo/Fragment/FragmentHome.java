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
import com.example.wetoo.R;
import com.example.wetoo.Request.TodoRequest;
import com.example.wetoo.Response.TodoResponse;
import com.example.wetoo.Adapter.TodoAdapter;

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
    private CalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);


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

        Calendar calendar = Calendar.getInstance();
        //캘린더뷰에서 날짜값 읽어오기
        Date date = new Date(calendarView.getDate());
        //캘린더 객체에 캘린더뷰 값을 넣음
        calendar.setTime(date);

        String todoyearmonth = Integer.toString(calendar.get(Calendar.YEAR)) + "-0" + Integer.toString(calendar.get(Calendar.MONTH) + 1);

        TodoRequest todoRequest = new TodoRequest(todoyearmonth);

        todoResponsesList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        todoAdapter = new TodoAdapter((ArrayList<TodoResponse>) todoResponsesList);
        recyclerView.setAdapter(todoAdapter);

        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.todo(ActivityLogin.accesstoken, todoRequest).enqueue(new Callback<List<TodoResponse>>() {
            @Override
            public void onResponse(Call<List<TodoResponse>> call, Response<List<TodoResponse>> response) {
                    Log.e("error","errrrrrorrr");
                    todoResponsesList.addAll(response.body());
                    todoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TodoResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });


        return rootview;
    }
}