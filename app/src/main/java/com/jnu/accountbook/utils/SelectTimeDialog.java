package com.jnu.accountbook.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jnu.accountbook.R;

public class SelectTimeDialog extends Dialog implements View.OnClickListener {
    DatePicker datePicker;
    Button confirmBtn;
    Button cancelBtn;
    String year, month, day;

    public interface OnEnsureListener {
        void ensure(String year, String month, String day);
    }

    private OnEnsureListener onClickListener;

    public void setEnsureListener(OnEnsureListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_dialog);
        this.datePicker = findViewById(R.id.time_dialog_calendar);
        this.confirmBtn = findViewById(R.id.time_dialog_confirm);
        this.cancelBtn = findViewById(R.id.time_dialog_cancel);
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);

    }

    public SelectTimeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.time_dialog_confirm:

                year = Integer.toString(datePicker.getYear());
                month = Integer.toString(datePicker.getMonth());
                day = Integer.toString(datePicker.getDayOfMonth());
                if (onClickListener != null) {
                    onClickListener.ensure(year,month,day);
                }
                break;
            case R.id.time_dialog_cancel:
                cancel();
                break;
        }
    }
}
