package com.jnu.accountbook.utils;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.jnu.accountbook.R;
import com.jnu.accountbook.bean.AccountBean;

public class KeyBoardUtil {
    private final Keyboard k1;
    private KeyboardView keyboardView;
    private EditText editText;
    private boolean flag = true;

    public interface OnEnsureListener {
        public void onEnsure();
    }

    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public KeyBoardUtil(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.editText.setInputType(InputType.TYPE_NULL);
        k1 = new Keyboard(this.editText.getContext(), R.xml.key);

        this.keyboardView.setKeyboard(k1);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener);
    }

    KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int i) {
        }

        @Override
        public void onRelease(int i) {
        }

        @Override
        public void onKey(int i, int[] ints) {
            Editable text = editText.getText();
            int selectionStart = editText.getSelectionStart();
            switch (i) {
                case Keyboard.KEYCODE_DONE:
                    if (onEnsureListener != null) {
                        onEnsureListener.onEnsure();
                    }
                    break;
                default:
                    if (flag) {
                        text.clear();
                        flag = false;
                    }
                    text.insert(selectionStart, Character.toString((char) i));
            }
        }

        @Override
        public void onText(CharSequence charSequence) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    public void showKeyBoard() {
        int visibility = keyboardView.getVisibility();
    }
}
