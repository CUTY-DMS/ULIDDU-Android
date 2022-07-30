package com.example.wetoo.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wetoo.API.ApiProvider;
import com.example.wetoo.API.ServiceApi;
import com.example.wetoo.Activity.ActivityLogin;
import com.example.wetoo.Adapter.SearchAdapter;
import com.example.wetoo.R;
import com.example.wetoo.Response.SearchResponse;
import com.example.wetoo.Response.UserInfoResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private static List<UserInfoResponse> userInfoResponseList;
    SearchResponse searchResponse;
    private SearchAdapter searchAdapter;
    public static String userid;
    private EditText etuserId;
    private ArrayList<UserInfoResponse> arrayList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);


        etuserId = view.findViewById(R.id.etuserId);
        recyclerView = view.findViewById(R.id.searchRecyclerView);
        recyclerView.setHasFixedSize(true);

        userInfoResponseList = new ArrayList<>();
        arrayList = new ArrayList<>();
        arrayList.addAll(userInfoResponseList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        searchAdapter = new SearchAdapter(userInfoResponseList);
        recyclerView.setAdapter(searchAdapter);

        Log.e("errrrrrror", "eeeeeeee");

        etuserId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("beforeText", "eeeeeeee");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = etuserId.getText().toString();
                result(text);
            }
        });

        return view;
    }

    private void result(String text) {
        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.search(ActivityLogin.accesstoken, text).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                userid = searchResponse.getId();

                userinfo();

                userInfoResponseList.clear();

                if (text.length() == 0) {
                    userInfoResponseList.addAll(arrayList);
                } else {
                    for (int i = 0; i < arrayList.size(); i++) {
                        if (arrayList.get(i).toString().contains(text)) {
                            // 검색된 데이터를 리스트에 추가한다.
                            userInfoResponseList.add(arrayList.get(i));
                        }
                    }
                }
                searchAdapter.notifyDataSetChanged();
            }

        @Override
        public void onFailure (Call < SearchResponse > call, Throwable t){

        }
    });
}

    private void userinfo() {
        ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

        serviceApi.userInfo(ActivityLogin.accesstoken, userid).enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                userInfoResponseList.add(response.body());
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });
    }
}