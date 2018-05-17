package com.example.jcp.abercrombiecodingtest.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jcp.abercrombiecodingtest.MainAdapter;
import com.example.jcp.abercrombiecodingtest.R;
import com.example.jcp.abercrombiecodingtest.databinding.ActivityMainBinding;
import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;
import com.example.jcp.abercrombiecodingtest.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {

    ActivityMainBinding binding;
    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainViewModel = new MainViewModel(this, this);
        binding.setViewModel(mainViewModel);
        setupRecyclerView(binding.dataRecyclerView);
        mainViewModel.loadData();

    }

    private void setupRecyclerView(RecyclerView dataRecyclerView) {
        MainAdapter adapter = new MainAdapter();
        dataRecyclerView.setAdapter(adapter);
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainViewModel.destroy();
    }

    @Override
    public void onExploreDataChanger(List<ExploreData> exploreData) {
        MainAdapter adapter = (MainAdapter) binding.dataRecyclerView.getAdapter();
        adapter.setExploreData(exploreData);
        adapter.notifyDataSetChanged();
    }
}