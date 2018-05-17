package com.example.jcp.abercrombiecodingtest.model;

import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

/**
 * Created by GUEST on 5/16/2018.
 */
public interface RemoteService {

    @Headers("User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3)")
    @GET("/anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    Observable<List<ExploreData>> getExploreData();

    class Factory {
        public static RemoteService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.abercrombie.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RemoteService.class);
        }
    }
}