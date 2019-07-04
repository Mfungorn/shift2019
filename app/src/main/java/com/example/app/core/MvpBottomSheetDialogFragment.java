package com.example.app.core;

import android.app.Dialog;
import android.os.Bundle;
import com.example.app.moxy.MvpAndroidxDialogFragment;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MvpBottomSheetDialogFragment extends MvpAndroidxDialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new BottomSheetDialog(getContext(), getTheme());
    }
}
