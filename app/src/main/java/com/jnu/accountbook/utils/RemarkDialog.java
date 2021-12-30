package com.jnu.accountbook.utils;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.jnu.accountbook.R;

public class RemarkDialog extends Dialog implements View.OnClickListener {

    private EditText edit;
    private ImageView ensure;

    public interface OnEnsureListener {
        void ensure();
    }

    private OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public RemarkDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remark_dialog);

        edit = findViewById(R.id.remark_edit);
        ensure = findViewById(R.id.remark_ensure);
        edit.setOnClickListener(this);
        ensure.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.remark_edit:
                break;
            case R.id.remark_ensure:
                if (onEnsureListener != null) {
                    onEnsureListener.ensure();
                }
                break;
        }
    }

    public String getEditText() {
        return edit.getText().toString().trim();
    }
}
