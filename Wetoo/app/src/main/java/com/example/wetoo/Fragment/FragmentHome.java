package com.example.wetoo.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wetoo.Board.BoardAdapter;
import com.example.wetoo.Board.BoardResponse;
import com.example.wetoo.Board.Post;
import com.example.wetoo.EditPage;
import com.example.wetoo.R;
import com.example.wetoo.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BoardAdapter boardAdapter;
    private ArrayList<Post> arrayList;
    List<BoardResponse> boardResponseList;
    private ImageView ivPost;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        ivPost = rootview.findViewById(R.id.ivPost);
        recyclerView = rootview.findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        ivPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditPage.class);
                startActivity(intent);
            }
        });
        return rootview;
    }
}