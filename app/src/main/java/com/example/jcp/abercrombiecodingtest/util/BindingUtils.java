package com.example.jcp.abercrombiecodingtest.util;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jcp.abercrombiecodingtest.R;
import com.example.jcp.abercrombiecodingtest.model.pojo.Content;
import com.example.jcp.abercrombiecodingtest.view.WebViewActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by GUEST on 5/16/2018.
 */
public class BindingUtils {

    public static final String INTENT_KEY = "INTENT_KEY";

    @BindingAdapter("isVisible")
    public static void isVisible(TextView textView, String input) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (input == null || input.isEmpty()) textView.setVisibility(View.GONE);
        else textView.setVisibility(View.VISIBLE);
    }

    @BindingAdapter("loadImageToPicasso")
    public static void loadImageToPicasso(ImageView imageView, String input) {
        Context context = imageView.getContext();
        Picasso.with(context).load(input).into(imageView);
    }

    @BindingAdapter("inflateContent")
    public static void inflateContent(LinearLayout layout, List<Content> contents) {
        layout.removeAllViews();
        if (contents != null && contents.size()>0)
        for (Content content : contents) {
            layout.addView(generateRow(layout,content.getTitle(), content.getTarget()));
        }
    }

    @BindingAdapter("loadUrl")
    public static void loadUrl(WebView webView, String input) {
        if (input != null) webView.loadUrl(input);
    }

    public static Button generateRow(LinearLayout layout, String title, final String target) {
        final Context context = layout.getContext();
        Button button = (Button) LayoutInflater.from(context)
                .inflate(R.layout.row_content, layout, false);
        button.setText(title);
        button.setOnClickListener(v->showWebView(context, target));
        return button;
    }

    public static void showWebView(Context context, String input) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(INTENT_KEY, input);
        context.startActivity(intent);
    }
}