package com.megvii.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.megvii.ui.R;
import com.megvii.ui.fragment.BaseActionFragment;

import butterknife.BindView;
import cn.droidlover.xdroidmvp.mvp.XActivity;
import cn.droidlover.xdroidmvp.router.Router;

/**
 * @author by licheng on 2018/7/10.
 */
public class ResultActivity extends XActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    BaseActionFragment resultFragment;

    @Override
    public void initData(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        String subFragment = getIntent().getStringExtra("faceset_sub_fragment");
        Bundle bundle = new Bundle();
        bundle.putString("faceset_sub_fragment", subFragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        resultFragment = (BaseActionFragment) Fragment.instantiate(this,
                getIntent().getStringExtra("fragment"), bundle);
        transaction.replace(R.id.result, resultFragment).commitNowAllowingStateLoss();
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
        launch(activity, fragment, title, "");
    }

    public static void launch(Activity activity, String fragment, String title, String subFragment) {
        Bundle bundle = new Bundle();
        bundle.putString("fragment", fragment);
        bundle.putString("title", title);
        bundle.putString("faceset_sub_fragment", subFragment);
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
