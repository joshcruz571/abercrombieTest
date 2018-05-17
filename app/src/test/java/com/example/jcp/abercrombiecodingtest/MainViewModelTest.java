package com.example.jcp.abercrombiecodingtest;

import android.view.View;

import com.example.jcp.abercrombiecodingtest.model.RemoteService;
import com.example.jcp.abercrombiecodingtest.model.pojo.ExploreData;
import com.example.jcp.abercrombiecodingtest.util.MockModelFabric;
import com.example.jcp.abercrombiecodingtest.viewmodel.MainViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Collections;
import java.util.List;

import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by GUEST on 5/17/2018.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainViewModelTest {

    RemoteService remoteService;
    App app;
    MainViewModel viewModel;
    MainViewModel.DataListener listener;

    @Before
    public void setUp() {
        remoteService = mock(RemoteService.class);
        listener = mock(MainViewModel.DataListener.class);
        app = (App) RuntimeEnvironment.application;
        // Mock the retrofit service so we don't call the API directly
        app.setRemoteService(remoteService);
        // Change the default subscribe schedulers so all observables
        // will now run on the same thread
        app.setScheduler(Schedulers.immediate());
        viewModel = new MainViewModel(app, listener);
    }

    @Test
    public void shouldSearchItems() {
        List<ExploreData> mockExploreData = MockModelFabric.createListOfData(10);
        doReturn(rx.Observable.just(mockExploreData)).when(remoteService).getExploreData();

        viewModel.loadData();
        verify(listener).onExploreDataChanger(mockExploreData);
        assertEquals(viewModel.progressVisibility.get(), View.INVISIBLE);
        assertEquals(viewModel.recyclerViewVisibility.get(), View.VISIBLE);
        assertEquals(viewModel.infoMessageVisibility.get(), View.INVISIBLE);
    }

    @Test
    public void shouldSearchWithNoItems() {
        when(remoteService.getExploreData())
                .thenReturn(rx.Observable.just(Collections.emptyList()));

        viewModel.loadData();
        verify(listener).onExploreDataChanger(Collections.emptyList());
        assertEquals(viewModel.progressVisibility.get(), View.INVISIBLE);
        assertEquals(viewModel.recyclerViewVisibility.get(), View.INVISIBLE);
    }
}