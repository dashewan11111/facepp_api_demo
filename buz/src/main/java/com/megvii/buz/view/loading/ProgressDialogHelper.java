/*
 * The MIT License (MIT)
 * Copyright (c) 2011-2014 九州智通 Corporation
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.megvii.buz.view.loading;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.megvii.buz.R;

public class ProgressDialogHelper {

    private final Handler handler = new Handler(Looper.getMainLooper());

    private LoadingDialog pd;

    private final Context context;

    private int titleResId = R.string.app_loading_title;
    private int messageResId = R.string.com_loading_message;
    private int positiveButtonTextId = android.R.string.ok;
    private int negativeButtonTextId = android.R.string.cancel;

    private boolean cancelable;
    private String dialogMsg;
    private Runnable runnable;
    private Consumer<ProgressDialogHelper> consumer;
    private Consumer<ProgressDialogHelper> confirmClick;
    private DialogInterface.OnCancelListener cancelListener;
    private Cancelable handleObject;

    public ProgressDialogHelper(Context context) {
        this.context = context;
    }

    public ProgressDialogHelper setTitle(int titleResId) {
        this.titleResId = titleResId;
        return this;
    }

    public ProgressDialogHelper setMessage(int messageResId) {
        this.messageResId = messageResId;
        return this;
    }

    public ProgressDialogHelper setPositiveButtonText(int positiveButtonTextId) {
        this.positiveButtonTextId = positiveButtonTextId;
        return this;
    }

    public ProgressDialogHelper setNegativeButtonText(int negativeButtonTextId) {
        this.negativeButtonTextId = negativeButtonTextId;
        return this;
    }

    public ProgressDialogHelper setCancel(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public ProgressDialogHelper setConfirmDialog(int dialogMsgId) {
        dialogMsg = context.getString(dialogMsgId);
        return this;
    }

    public ProgressDialogHelper setConfirmDialog(String dialogMsg) {
        this.dialogMsg = dialogMsg;
        return this;
    }

    @Deprecated
    public ProgressDialogHelper setOnProgress(Runnable runnable) {
        this.runnable = runnable;
        return this;
    }

    public ProgressDialogHelper setOnProgress(Consumer<ProgressDialogHelper> consumer) {
        this.consumer = consumer;
        return this;
    }

    public ProgressDialogHelper setConfirmClick(Consumer<ProgressDialogHelper> consumer) {
        confirmClick = consumer;
        return this;
    }

    public ProgressDialogHelper setCancelListener(DialogInterface.OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
        return this;
    }

    public ProgressDialogHelper setHandleTask(Cancelable handleObject) {
        this.handleObject = handleObject;
        return this;
    }

    public ProgressDialogHelper show() {
        if (!TextUtils.isEmpty(dialogMsg)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle(titleResId)
                    .setMessage(dialogMsg)
                    .setPositiveButton(positiveButtonTextId, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            doing();
                        }
                    })
                    .setNegativeButton(negativeButtonTextId, null)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            if (null != handleObject) {
                                handleObject.cancel();
                            }
                            if (null != cancelListener) {
                                cancelListener.onCancel(dialog);
                            }
                        }
                    });
            final AlertDialog alertDialog = builder.create();
            if (cancelable) {
                alertDialog.setCancelable(true);
                alertDialog.setCanceledOnTouchOutside(true);
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    alertDialog.show();
                }
            });
        } else {
            doing();
        }
        return this;
    }

    public ProgressDialogHelper showAtProgress() {
        if (!TextUtils.isEmpty(dialogMsg)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context)
                    .setTitle(titleResId)
                    .setMessage(messageResId)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            if (null != handleObject) {
                                handleObject.cancel();
                            }
                            if (null != cancelListener) {
                                cancelListener.onCancel(dialog);
                            }
                        }
                    });
            final AlertDialog alertDialog = builder.create();
            if (cancelable) {
                alertDialog.setCancelable(true);
                alertDialog.setCanceledOnTouchOutside(true);
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    alertDialog.show();
                }
            });
        } else {
            doing();
        }
        return this;
    }

    public ProgressDialogHelper showAtLoading() {
        setMessage(R.string.com_loading);
        return showAtProgress();
    }

    public boolean isShowing() {
        return pd != null && pd.isShowing();
    }

    public void dismiss() {
        if (null != pd && pd.isShowing()) {
            pd.dismiss();
        }
        pd = null;
    }

    private void doing() {
        if (null != confirmClick) {
            confirmClick.accept(this);
        } else {
            showProgress();
            if (null != runnable) {
                runnable.run();
            }
            if (null != consumer) {
                consumer.accept(this);
            }
        }
    }

    private void showProgress() {
        if (pd == null) {
            pd = LoadingDialog.newInstance(context);
            //            pd = ProgressDialog.show(context, context.getString(titleResId),
            //                context.getString(messageResId), true, cancelable);
            pd.withText(context.getString(messageResId));
            pd.setCanceledOnTouchOutside(cancelable);
            pd.setCancelable(cancelable);
        }
        pd.show();
    }
}
