package com.vickykdv.ratingdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;


public class AbstractDlg implements DialogInterface {

    //Membuat Constan
    public static final int BUTTON_POSITIVE = 1;
    public static final int BUTTON_NEGATIVE = -1;
    public static final int NO_ICON = -111;

    protected Dialog mDialog;
    protected Activity mActivity;
    protected String title;
    protected String message;
    protected boolean mCancelable;
    protected DialogButton mPositiveButton;

    public String getKomentar() {
        return komentar;
    }

    public AbstractDlg setKomentar(String komentar) {
        this.komentar = komentar;
        return this;
    }

    public String getRating() {
        return rating;
    }

    public AbstractDlg setRating(String rating) {
        this.rating = rating;
        return this;
    }

    protected DialogButton mNegativeButton;
    protected String komentar;
    protected String rating;

    protected OnDismissListener mOnDismissListener;
    protected OnCancelListener mOnCancelListener;
    protected OnShowListener mOnShowListener;
    protected TextInputEditText edt_pesan;
    protected SmileRating smileRating;



    protected AbstractDlg(@NonNull Activity mActivity,
                          @NonNull String title,
                          @NonNull String message,
                          boolean mCancelable,
                          @NonNull DialogButton mPositiveButton,
                          @NonNull DialogButton mNegativeButton, String komentar, String rating) {
        this.mActivity = mActivity;
        this.title = title;
        this.message = message;
        this.mCancelable = mCancelable;
        this.mPositiveButton = mPositiveButton;
        this.mNegativeButton = mNegativeButton;
        this.komentar = komentar;
        this.rating = rating;

    }

    protected View createView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container) {
        final View dialogView = inflater.inflate(R.layout.rating_layout, container, false);

        TextView mTitleView = dialogView.findViewById(R.id.tv_dialog_title);
        TextView mMessageView = dialogView.findViewById(R.id.tv_dialog_content_desc);
        Button mPositiveButtonView = dialogView.findViewById(R.id.btn_kirim);
        Button mNegativeButtonView = dialogView.findViewById(R.id.btn_nanti);
        edt_pesan = dialogView.findViewById(R.id.edt_pesan);
        smileRating = dialogView.findViewById(R.id.smile_rating);
        smileRating.setSelectedSmile(BaseRating.OKAY);




        if (title != null) {
            mTitleView.setVisibility(View.VISIBLE);
            mTitleView.setText(title);
        } else {
            mTitleView.setVisibility(View.GONE);
        }

        if (message != null) {
            mMessageView.setVisibility(View.VISIBLE);
            mMessageView.setText(message);
        } else {
            mMessageView.setVisibility(View.GONE);
        }



        if (mPositiveButton != null) {
            mPositiveButtonView.setVisibility(View.VISIBLE);
            mPositiveButtonView.setText(mPositiveButton.getTitle());


            mPositiveButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    setKomentar(edt_pesan.getText().toString());
                    setRating(String.valueOf(smileRating.getRating()));
                    Log.d("AbstractDlg", "onClick: "+getKomentar());
                    Log.d("AbstractDlg", "onClick: "+getRating());
                    mPositiveButton.getOnClickListener().onClick(AbstractDlg.this, BUTTON_POSITIVE);


                }
            });
        } else {
            mPositiveButtonView.setVisibility(View.INVISIBLE);
        }

        if (mNegativeButton != null) {
            mNegativeButtonView.setVisibility(View.VISIBLE);
            mNegativeButtonView.setText(mNegativeButton.getTitle());


            mNegativeButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mNegativeButton.getOnClickListener().onClick(AbstractDlg.this, BUTTON_NEGATIVE);
                }
            });
        } else {
            mNegativeButtonView.setVisibility(View.INVISIBLE);
        }

        return dialogView;
    }


//    public void Komentar(){
//        rating = String.valueOf(smileRating.getSelectedSmile());
//    }
//
//    public void Rating(){
//        rating = String.valueOf(smileRating.getSelectedSmile());
//    }



    /**
     * Displays the Dialog
     */
    public void show() {
        if (mDialog != null) {
            mDialog.show();
        } else {
            throwNullDialog();
        }
    }

    /**
     * Cancels the Dialog
     */
    @Override
    public void cancel() {
        if (mDialog != null) {
            mDialog.cancel();
        } else {
            throwNullDialog();
        }
    }

    /**
     * Dismisses the Dialog
     */
    @Override
    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        } else {
            throwNullDialog();
        }
    }

    /**
     * @param onShowListener interface for callback events when dialog is showed.
     */
    public void setOnShowListener(@NonNull final OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;

        mDialog.setOnShowListener(new android.content.DialogInterface.OnShowListener() {
            @Override
            public void onShow(android.content.DialogInterface dialogInterface) {
                showCallback();
            }
        });
    }

    /**
     * @param onCancelListener interface for callback events when dialog is cancelled.
     */
    public void setOnCancelListener(@NonNull final OnCancelListener onCancelListener) {
        this.mOnCancelListener = onCancelListener;

        mDialog.setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(android.content.DialogInterface dialogInterface) {
                cancelCallback();
            }
        });
    }

    /**
     * @param onDismissListener interface for callback events when dialog is dismissed;
     */
    public void setOnDismissListener(@NonNull final OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;

        mDialog.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(android.content.DialogInterface dialogInterface) {
                dismissCallback();
            }
        });
    }

//
    private void showCallback() {
        if (mOnShowListener != null) {
            mOnShowListener.onShow(this);
        }
    }

    private void dismissCallback() {
        if (mOnDismissListener != null) {
            mOnDismissListener.onDismiss(this);
        }
    }

    private void cancelCallback() {
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(this);
        }
    }

    private void throwNullDialog() {
        throw new NullPointerException("Lakukan builder dialog sebelum memanggil /  show");
    }

    public interface OnClickListener {
        void onClick(DialogInterface dialogInterface, int which);
    }
}

