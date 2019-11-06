package com.vickykdv.ratingdialog;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

public class CenterRating extends AbstractDlg {


    public CenterRating(@NonNull Activity mActivity,
                        @NonNull String title,
                        @NonNull String message,
                        boolean mCancelable,
                        @NonNull DialogButton mPositiveButton,
                        @NonNull DialogButton mNegativeButton,
                        String mKomentar,
                        String mRating) {
        super(mActivity, title, message, mCancelable, mPositiveButton, mNegativeButton,mKomentar,mRating);

        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        LayoutInflater inflater = mActivity.getLayoutInflater();

        builder.setView(createView(inflater, null));

        builder.setCancelable(mCancelable);

        mDialog = builder.create();
    }

    /**
     * Builder for {@link CenterRating}.
     */
    public static class Builder {
        private Activity activity;
        private String title;
        private String message;

        private String komentar;
        private String rating;
        private boolean isCancelable;
        private DialogButton positiveButton;
        private DialogButton negativeButton;



        /**
         * @param activity where Material Dialog is to be built.
         */
        public Builder(@NonNull Activity activity) {
            this.activity = activity;
        }

        /**
         * @param title Sets the Title of Material Dialog.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setTitle(@NonNull String title) {
            this.title = title;
            return this;
        }



        /**
         * @param message Sets the Message of Material Dialog.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setMessage(@NonNull String message) {
            this.message = message;
            return this;
        }

        public String getKomentar() {
            return komentar;
        }

        public Builder setKomentar(String komentar) {
            this.komentar = komentar;
            return this;
        }


        public String getRating() {
            return rating;
        }

        public Builder setRating(String rating) {
            this.rating = rating;
            return this;
        }

        /**
         * @param isCancelable Sets cancelable property of Material Dialog.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        /** Sets the Positive Button to Material Dialog without icon
         * @param name sets the name/label of button.
         * @param onClickListener interface for callback event on click of button.
         * @see this, for chaining.
         */
        @NonNull
        public Builder setPositiveButton(@NonNull String name, @NonNull OnClickListener onClickListener) {
            return setPositiveButton(name, NO_ICON, onClickListener);
        }

        /** Sets the Positive Button to Material Dialog with icon
         * @param name sets the name/label of button.
         * @param icon sets the resource icon for button.
         * @param onClickListener interface for callback event on click of button.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setPositiveButton(@NonNull String name, int icon, @NonNull OnClickListener onClickListener) {
            positiveButton = new DialogButton(name, icon, onClickListener);
            return this;
        }

        /** Sets the Negative Button to Material Dialog without icon.
         * @param name sets the name/label of button.
         * @param onClickListener interface for callback event on click of button.
         * @see this, for chaining.
         */
        @NonNull
        public Builder setNegativeButton(@NonNull String name, @NonNull OnClickListener onClickListener) {
            return setNegativeButton(name, NO_ICON, onClickListener);
        }

        /** Sets the Negative Button to Material Dialog with icon
         * @param name sets the name/label of button.
         * @param icon sets the resource icon for button.
         * @param onClickListener interface for callback event on click of button.
         * @return this, for chaining.
         */
        @NonNull
        public Builder setNegativeButton(@NonNull String name, int icon, @NonNull OnClickListener onClickListener) {
            negativeButton = new DialogButton(name, icon, onClickListener);
            return this;
        }

        @NonNull
        public CenterRating build() {
            return new CenterRating(activity, title, message, isCancelable, positiveButton, negativeButton,komentar,rating);
        }
    }
}
