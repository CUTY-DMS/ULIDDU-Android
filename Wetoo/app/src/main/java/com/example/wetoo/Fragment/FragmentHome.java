package com.example.wetoo.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wetoo.Board.BoardAdapter;
import com.example.wetoo.Board.BoardData;
import com.example.wetoo.R;
import com.example.wetoo.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    private ArrayList<BoardData> arrayList;
    private BoardAdapter boardAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}