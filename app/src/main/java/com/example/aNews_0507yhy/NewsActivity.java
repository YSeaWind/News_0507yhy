package com.example.aNews_0507yhy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.aNews_0507yhy.utils.bean.Data;

import java.io.Serializable;

public class NewsActivity extends AppCompatActivity {
    WebView webView;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        webView = findViewById(R.id.webview);
        tv = findViewById(R.id.tv);

        Intent intent = getIntent();
        Data data = (Data) intent.getSerializableExtra("key");
        tv.setText(data.getCategory());
        final String url = data.getUrl();
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
    }

    protected void onPause() {
        super.onPause();
        //当activity失去焦点返回后台运行，webview执行onPause
        //通过onPause动作通知  webkit内核暂停所有的动作
        webView.onPause();
        //当应用程序 切换到后胎时，此方法不仅仅针对于当前webview 而是作用于整个应用程序
//        webView.pauseTimers();
    }

    @Override
    protected void onResume() {
        webView.onResume();
        //激活webview的状态能够正常加载页面
        super.onResume();
        //激活所有的webview
//        webView.resumeTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //必须先在父容器中移除  之后才能继续销毁
        ViewGroup viewById = findViewById(R.id.cainter);//找到父容器
        viewById.removeView(webView);//移除页面
        webView.destroy();
    }
}
