package com.example.jcp.abercrombiecodingtest.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jcp.abercrombiecodingtest.R;
import com.example.jcp.abercrombiecodingtest.databinding.ActivityWebViewBinding;
import com.example.jcp.abercrombiecodingtest.viewmodel.WebViewModel;

import static com.example.jcp.abercrombiecodingtest.util.BindingUtils.INTENT_KEY;

public class WebViewActivity extends AppCompatActivity {

    private ActivityWebViewBinding binding;
    private WebViewModel viewModel;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        viewModel = new WebViewModel();
        Bundle extra = getIntent().getExtras();
        url = "https://www.abercrombie.com/shop/us";
        if (extra != null) {
            url = extra.getString(INTENT_KEY);
        }
        viewModel.setUrl(url);
        binding.mainWebView.getSettings().setJavaScriptEnabled(true);
        binding.setViewModel(viewModel);

    }
}