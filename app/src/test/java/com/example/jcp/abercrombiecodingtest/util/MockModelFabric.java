package com.example.jcp.abercrombiecodingtest.util;

import com.example.jcp.abercrombiecodingtest.model.pojo.Content;
import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by GUEST on 5/17/2018.
 */
public class MockModelFabric {

    public static List<ExploreData> createListOfData(int num) {
        List<ExploreData> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(newExploreData());
        }
        return list;
    }

    public static ExploreData newExploreData() {
        ExploreData data = new ExploreData();

        data.setBackgroundImage("http://anf.scene7.com/is/image/anf/anf-20160527-app-m-shirts?$anf-ios-fullwidth-3x$");
        data.setPromoMessage("USE CODE: 12345");
        data.setTopDescription("A&F ESSENTIALS");
        data.setBottomDescription("*In stores & online");
        data.setContent(newContent());
        return data;
    }

    public static List<Content> newContent() {
        Content content = new Content();
        content.setTarget("https://www.abercrombie.com/shop/us/mens-new-arrivals");
        content.setTitle("Shop Men");
        return Arrays.asList(content);
    }


}
