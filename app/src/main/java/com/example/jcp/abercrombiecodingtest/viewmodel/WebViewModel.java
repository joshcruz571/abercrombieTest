package com.example.jcp.abercrombiecodingtest.viewmodel;

import android.databinding.ObservableField;

/**
 * Created by GUEST on 5/17/2018.
 */
public class WebViewModel implements ViewModel {

    public ObservableField<String> url;

    public void setUrl(String url) {
        this.url.set(url);
    }

    public WebViewModel() {
        url = new ObservableField<>();
    }
    @Override
    public void destroy() {

    }
}