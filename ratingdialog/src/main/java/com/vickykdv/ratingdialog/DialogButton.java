package com.vickykdv.ratingdialog;

public class DialogButton {
    private String title;
        private int icon;
        private AbstractDlg.OnClickListener onClickListener;

        DialogButton(String title, int icon, AbstractDlg.OnClickListener onClickListener) {
            this.title = title;
            this.icon = icon;
            this.onClickListener = onClickListener;
        }

        String getTitle() {
            return title;
        }

        public int getIcon() {
            return icon;
        }

        AbstractDlg.OnClickListener getOnClickListener() {
            return onClickListener;
        }
}
