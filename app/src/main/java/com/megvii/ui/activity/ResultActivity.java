package com.megvii.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.megvii.buz.view.loading.ProgressDialogHelper;
import com.megvii.ui.R;
import com.megvii.ui.fragment.BaseResultFragment;
import com.megvii.ui.view.FaceView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * @author by licheng on 2018/7/10.
 */
public class ResultActivity extends XActivity {

    static final int PHOTO_REQUEST_CODE = 0x10;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    BaseResultFragment resultFragment;

    @Override
    public void initData(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        resultFragment = (BaseResultFragment) Fragment.instantiate(this,
                getIntent().getStringExtra("fragment"));
        transaction.replace(R.id.result, resultFragment).commitNowAllowingStateLoss();
    }

    @OnClick(R.id.btn_choose_file)
    public void chooseFile(View view) {
        showFileChooseDialog();
    }

    private void showFileChooseDialog() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    //通过uri的方式返回，部分手机uri可能为空
                    if (uri != null) {
                        try {
                            //通过uri获取到bitmap对象
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            resultFragment.onFileChoose(bitmap);
                            scrollView.smoothScrollTo(0, 0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        //部分手机可能直接存放在bundle中
                        Bundle bundleExtras = data.getExtras();
                        if (bundleExtras != null) {
                            Bitmap bitmaps = bundleExtras.getParcelable("data");
                            resultFragment.onFileChoose(bitmaps);
                            scrollView.smoothScrollTo(0, 0);
                        }
                    }

                }
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_result;
    }

    @Override
    public Object newP() {
        return null;
    }

    public static void launch(Activity activity, String fragment, String title) {
        Bundle bundle = new Bundle();
        bundle.putString("fragment", fragment);
        bundle.putString("title", title);
        Router.newIntent(activity).to(ResultActivity.class).data(bundle).launch();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
