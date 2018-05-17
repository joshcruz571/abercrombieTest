package com.example.jcp.abercrombiecodingtest;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.jcp.abercrombiecodingtest.databinding.ItemDataBinding;
import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;
import com.example.jcp.abercrombiecodingtest.viewmodel.ItemViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by GUEST on 5/16/2018.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<ExploreData> exploreData;

    public MainAdapter() {
        this.exploreData = Collections.emptyList();
    }

    public void setExploreData(List<ExploreData> exploreData) {
        this.exploreData = exploreData;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_data, parent, false);
        return new MainViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bindExploreData(exploreData.get(position));
    }

    @Override
    public int getItemCount() {
        return exploreData.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        final ItemDataBinding binding;

        public MainViewHolder(ItemDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindExploreData(ExploreData exploreData) {
            if (binding.getViewModel() == null)
                binding.setViewModel(new ItemViewModel(itemView.getContext(), exploreData));
            else binding.getViewModel().setExploreData(exploreData);
        }
    }
}