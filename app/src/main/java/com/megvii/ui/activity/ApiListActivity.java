package com.megvii.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.megvii.ui.R;
import com.megvii.ui.fragment.ApiListFragment;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * @author by licheng on 2018/7/6.
 */

public class ApiListActivity extends XActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar();
        addFragment();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("人脸相关Api列表");
    }

    private void addFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, Fragment.instantiate(this, ApiListFragment.class.getName(), getIntent().getExtras()), null);
        transaction.commitNowAllowingStateLoss();
    }

    public static void launch(Activity activity, Bundle bundle) {
        Router.newIntent(activity)
                .to(ApiListActivity.class)
                .data(bundle)
                .launch();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_api_list;
    }

    @Override
    public Object newP() {
        return null;
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
