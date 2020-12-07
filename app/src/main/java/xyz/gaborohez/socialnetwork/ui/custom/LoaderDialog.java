package xyz.gaborohez.socialnetwork.ui.custom;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatDialog;

import xyz.gaborohez.socialnetwork.databinding.LoaderDialogLayoutBinding;

public class LoaderDialog extends AppCompatDialog {

    public LoaderDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (super.getWindow() != null)
            super.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        if (savedInstanceState == null) {
            setCancelable(false);
            LoaderDialogLayoutBinding binding = LoaderDialogLayoutBinding.inflate(LayoutInflater.from(getContext()), null, false);
            this.setContentView(binding.getRoot());
        }
    }

    public void show() {
        if (!this.isShowing()) {
            super.show();
        }
    }

    public void dismiss() {
        if (this.isShowing()) {
            super.dismiss();
        }
    }
}
