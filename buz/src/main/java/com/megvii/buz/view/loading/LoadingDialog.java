package com.megvii.buz.view.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.megvii.buz.R;


public final class LoadingDialog extends Dialog {
    private LinearLayout mMainLayoutView;
    private TextView mText;

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
        init(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static LoadingDialog newInstance(Context context) {
        return new LoadingDialog(context, R.style.Common_LoadingDialog);
    }

    private void init(Context context) {
        View mDialogView = View.inflate(context, R.layout.common_dialog_loading, null);

        mMainLayoutView = (LinearLayout) mDialogView.findViewById(R.id.common_dialog_trans_main);
        mText = (TextView) mDialogView.findViewById(R.id.common_dialog_trans_text);

        setContentView(mDialogView);

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {

                mMainLayoutView.setVisibility(View.VISIBLE);
            }
        });
    }

    public LoadingDialog withText(CharSequence msg) {
        mText.setText(msg);
        return this;
    }
}
