package com.megvii.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.megvii.ui.activity.ApiListActivity;
import com.megvii.ui.datasource.ApiDataSource2;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;

public class MainActivity extends XActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.centerTitle)
    TextView txtCenterTitle;

    @BindView(R.id.video_view)
    WebView videoView;

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar();
        initVideoView();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        txtCenterTitle.setVisibility(View.VISIBLE);
        txtCenterTitle.setText("Face++ Api");
    }

    private void initVideoView() {
        videoView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
        videoView.setWebViewClient(new WebViewClient());
        videoView.getSettings().setBuiltInZoomControls(false);
        videoView.getSettings().setJavaScriptEnabled(true);
        videoView.getSettings().setDomStorageEnabled(true);
        videoView.getSettings().setDatabaseEnabled(true);
        videoView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        videoView.getSettings().setAppCacheEnabled(true);

        videoView.loadUrl("file:///android_asset/video.html");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //videoView.onResume();
        playVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        videoView.onPause();
        pauseVideo();
    }

    @OnClick({
            R.id.facepp,
            R.id.humanbody,
            R.id.ocr,
            R.id.image
    })
    public void clickEvent(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {

            case R.id.facepp:
                bundle.putString("api_group", ApiDataSource2.ApiGroups.Face.getGroupName());
                ApiListActivity.launch(this, bundle);
                break;

            case R.id.humanbody:
                bundle.putString("api_group", ApiDataSource2.ApiGroups.Body.getGroupName());
                ApiListActivity.launch(this, bundle);
                break;

            case R.id.ocr:
                bundle.putString("api_group", ApiDataSource2.ApiGroups.OCR.getGroupName());
                ApiListActivity.launch(this, bundle);
                break;

            case R.id.image:
                bundle.putString("api_group", ApiDataSource2.ApiGroups.Image.getGroupName());
                ApiListActivity.launch(this, bundle);
                break;
            default:
                break;
        }
    }

    private void playVideo() {
        controlVideo("playVideo()");
    }

    private void pauseVideo() {
        controlVideo("pauseVideo()");
    }

    public void controlVideo(String method) {
        final int version = Build.VERSION.SDK_INT;
        if (version < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            videoView.loadUrl("javascript:" + method);
        } else {
            videoView.evaluateJavascript("javascript:" + method, null);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Object newP() {
        return null;
    }
}
