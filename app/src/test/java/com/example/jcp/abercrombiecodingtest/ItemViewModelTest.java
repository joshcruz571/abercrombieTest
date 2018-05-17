package com.example.jcp.abercrombiecodingtest;

import android.databinding.Observable;

import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;
import com.example.jcp.abercrombiecodingtest.viewmodel.ItemViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by GUEST on 5/17/2018.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ItemViewModelTest {

    App app;

    @Before
    public void setUp() {
        app = (App) RuntimeEnvironment.application;
    }

    //We can test all the viewModel properties.
    @Test
    public void shouldGetTitle() {
        ExploreData data = new ExploreData();
        data.setTitle("T-SHIRT DRESSES");

        ItemViewModel viewModel = new ItemViewModel(app, data);
        assertEquals(data.getTitle(), viewModel.getTitle());
    }

    @Test
    public void shouldNotifyPropertyChangeWhenSetData() {
        ExploreData data = new ExploreData();
        ItemViewModel viewModel = new ItemViewModel(app, data);

        Observable.OnPropertyChangedCallback mockCallback =
                mock(Observable.OnPropertyChangedCallback.class);

        viewModel.addOnPropertyChangedCallback(mockCallback);
        viewModel.setExploreData(data);
        verify(mockCallback).onPropertyChanged(any(Observable.class), anyInt());
    }
}