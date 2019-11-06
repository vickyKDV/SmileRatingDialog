package com.vickykdv.ratingdialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BottomSheetRating extends AbstractDlg {

    private AppCompatActivity mActivity;

    protected BottomSheetRating(@NonNull final AppCompatActivity mActivity,
                                @NonNull String title,
                                @NonNull String message,
                                boolean mCancelable,
                                @NonNull DialogButton mPositiveButton,
                                @NonNull DialogButton mNegativeButton,
                                String mKomentar,
                                String mRating) {
        super(mActivity, title, message, mCancelable, mPositiveButton, mNegativeButton,mKomentar,mRating);

        this.mActivity = mActivity;

        mDialog = new BottomSheetDialog(mActivity);

        LayoutInflater inflater = mActivity.getLayoutInflater();

        View dialogView = createView(inflater, null);
        mDialog.setContentView(dialogView);

        // Set Cancelable property
        mDialog.setCancelable(mCancelable);

            dialogView.findViewById(R.id.container).setPadding(0, (int) mActivity.getResources().getDimension(R.dimen.paddingatas),0,0);
    }

    @Override
    protected View createView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return super.createView(inflater, container);
    }

    /**
     * Builder for {@link BottomSheetRating}.
     */
    public static class Builder {
        private AppCompatActivity activity;
        private String title;
        private String message;
        private boolean isCancelable;
        private DialogButton positiveButton;
        private DialogButton negativeButton;
        private String komentar;
        private String rating;

        /**
         * @param activity where BottomSheet Material Dialog is to be built.
         */
        public Builder(@NonNull AppCompatActivity activity) {
            this.activity = activity;
        }

        /**
         * @param title Sets the Title of BottomSheet Material Dialog.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setTitle(@NonNull String title) {
            this.title = title;
            return this;
        }

        /**
         * @param message Sets the Message of BottomSheet Material Dialog.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setMessage(@NonNull String message) {
            this.message = message;
            return this;
        }

        /**
         * @param isCancelable Sets cancelable property of BottomSheet Material Dialog.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        /** Sets the Positive Button to BottomSheet Material Dialog without icon
         * @param name sets the name/label of button.
         * @param onClickListener interface for callback event on click of button.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setPositiveButton(@NonNull String name, @NonNull OnClickListener onClickListener) {
            return setPositiveButton(name, NO_ICON, onClickListener);
        }

        /** Sets the Positive Button to BottomSheet Material Dialog with icon
         * @param name sets the name/label of button.
         * @param icon sets the resource icon for button.
         * @param onClickListener interface for callback event on click of button.
         * @return this, for chaining.
         */
        @NonNull
        Builder setPositiveButton(@NonNull String name, int icon, @NonNull OnClickListener onClickListener) {
            positiveButton = new DialogButton(name, icon, onClickListener);
            return this;
        }

        /** Sets the Negative Button to BottomSheet Material Dialog without icon.
         * @param name sets the name/label of button.
         * @param onClickListener interface for callback event on click of button.
         * @see this, for chaining.
         */
        @NonNull
        public Builder setNegativeButton(@NonNull String name, @NonNull OnClickListener onClickListener) {
            return setNegativeButton(name, NO_ICON, onClickListener);
        }

        /** Sets the Negative Button to BottomSheet Material Dialog with icon
         * @param name sets the name/label of button.
         * @param icon sets the resource icon for button.
         * @param onClickListener interface for callback event on click of button.
         * @return this, for chaining.
         */
        @NonNull
        Builder setNegativeButton(@NonNull String name, int icon, @NonNull OnClickListener onClickListener) {
            negativeButton = new DialogButton(name, icon, onClickListener);
            return this;
        }



        /**
         * Build the {@link BottomSheetRating}.
         */
        @NonNull
        public BottomSheetRating build() {
            return new BottomSheetRating(activity, title, message, isCancelable, positiveButton, negativeButton,komentar,rating);
        }
    }

    class BottomSheetDialog extends com.google.android.material.bottomsheet.BottomSheetDialog {

        BottomSheetDialog(@NonNull Context context) {
            super(context, R.style.BottomSheetDialogTheme);
        }
    }
}
