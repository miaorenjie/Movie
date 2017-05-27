package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.miaojie.ptest.R;

/**
 * Created by miaojie on 2017/5/24.
 */

public class WebViewActivity extends Activity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        webView= (WebView) findViewById(R.id.WebView);
        
        String url=getIntent().getStringExtra("url");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }
}
