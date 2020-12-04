package xyz.gaborohez.socialnetwork.ui.base;

public interface BaseUIListener {
    void showLoader(boolean visible);
    void showAlertDialog(String message);
    void showAlertDialog(int resId);
    void expiredToken();
}
