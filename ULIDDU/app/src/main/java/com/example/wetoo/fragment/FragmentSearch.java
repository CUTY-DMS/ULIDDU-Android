package com.example.wetoo.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wetoo.R;
import com.example.wetoo.activity.LoginActivity;
import com.example.wetoo.api.ApiProvider;
import com.example.wetoo.api.ServiceApi;
import com.example.wetoo.response.SearchResponse;
import com.google.protobuf.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSearch extends Fragment {

    public static long id;
    private EditText search;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        search.findViewById(R.id.etuserId);


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ServiceApi serviceApi = ApiProvider.getInstance().create(ServiceApi.class);

                serviceApi.search(LoginActivity.accesstoken, String.valueOf(s)).enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        id = response.body().getId();
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {

                    }
                });
            }
        });



        return view;
    }
}