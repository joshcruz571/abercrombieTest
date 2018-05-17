package com.example.jcp.abercrombiecodingtest.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.example.jcp.abercrombiecodingtest.model.pojo.Content;
import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;

import java.util.List;

/**
 * Created by GUEST on 5/16/2018.
 */
public class ItemViewModel extends BaseObservable implements ViewModel {

    private ExploreData exploreData;
    private Context context;

    public ItemViewModel(Context context, ExploreData exploreData) {
        this.context = context;
        this.exploreData = exploreData;
    }

    public String getTitle() {
        return exploreData.getTitle();
    }

    public String getBackgroundImage() {
        return exploreData.getBackgroundImage();
    }

    public String getPromoMEssage() {
        return exploreData.getPromoMessage();
    }

    public String getTopDescription() {
        return exploreData.getTopDescription();
    }

    public String getBottomDescription() {
        return exploreData.getBottomDescription();
    }

    public List<Content> getContent() {
        return exploreData.getContent();
    }

    public void setExploreData(ExploreData exploreData) {
        this.exploreData = exploreData;
        notifyChange();
    }

    @Override
    public void destroy() {
        //In this case destroy doesn't need to do anything because there is not async calls
    }
}
