package com.example.bsccs;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;

public class ViewPfd extends AppCompatActivity {

    WebView pdfview;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pfd);

        pdfview= findViewById(R.id.viewpdf);
        pdfview.getSettings().setJavaScriptEnabled(true);

        String name=getIntent().getStringExtra("name");
        String fileurl=getIntent().getStringExtra("fileurl");

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle(name);
        pd.setMessage("Opening...!");

        pdfview.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });
        String url="";
        try {
            url= URLEncoder.encode(fileurl,"UTF-8");
        }catch (Exception ignored)
        {}

        pdfview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
    }
}