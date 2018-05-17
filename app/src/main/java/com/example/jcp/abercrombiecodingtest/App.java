package com.example.jcp.abercrombiecodingtest;

import android.app.Application;
import android.content.Context;

import com.example.jcp.abercrombiecodingtest.model.RemoteService;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by GUEST on 5/16/2018.
 */
public class App extends Application {

    private RemoteService remoteService;

    private Scheduler scheduler;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public RemoteService getRemoteService() {
        if (remoteService == null)
            remoteService = RemoteService.Factory.create();
        return remoteService;
    }

    public Scheduler defaultSubscribeScheduler() {
        if (scheduler == null) scheduler = Schedulers.io();
        return scheduler;
    }

    //For setting mocks during testing
    public void setRemoteService(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

    //User to change scheduler from tests
    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}