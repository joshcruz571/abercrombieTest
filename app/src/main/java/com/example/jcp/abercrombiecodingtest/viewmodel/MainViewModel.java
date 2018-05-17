package com.example.jcp.abercrombiecodingtest.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.example.jcp.abercrombiecodingtest.App;
import com.example.jcp.abercrombiecodingtest.model.RemoteService;
import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by GUEST on 5/16/2018.
 */
public class MainViewModel implements ViewModel {

    private Context context;
    private DataListener dataListener;
    private Subscription subscription;
    private List<ExploreData> exploreDataList;
    public ObservableInt progressVisibility;
    public ObservableInt recyclerViewVisibility;


    public MainViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
        progressVisibility = new ObservableInt(View.INVISIBLE);
        recyclerViewVisibility = new ObservableInt(View.INVISIBLE);
    }
    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        subscription = null;
        context = null;
        dataListener = null;
    }

    public void loadData() {
        progressVisibility.set(View.VISIBLE);
        recyclerViewVisibility.set(View.INVISIBLE);
        if (subscription != null && !subscription.isUnsubscribed()) subscription.unsubscribe();
        App app = App.get(context);
        RemoteService remoteService = app.getRemoteService();
        subscription = remoteService.getExploreData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.defaultSubscribeScheduler())
                .subscribe(new Subscriber<List<ExploreData>>() {
                    @Override
                    public void onCompleted() {
                        Log.i("JACP", "Completed ");
                        if (dataListener != null) dataListener.onExploreDataChanger(exploreDataList);
                        progressVisibility.set(View.INVISIBLE);
                        if (!exploreDataList.isEmpty()) recyclerViewVisibility.set(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<ExploreData> exploreData) {
                        Log.i("JACP", "Repos loaded " + exploreData);
                        MainViewModel.this.exploreDataList = exploreData;
                    }
                });
    }

    public interface DataListener {
        void onExploreDataChanger(List<ExploreData> exploreData);
    }
}