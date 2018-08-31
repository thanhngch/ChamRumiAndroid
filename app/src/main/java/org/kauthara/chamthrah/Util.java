package org.kauthara.chamthrah;

import android.view.inputmethod.InputMethodManager;
import 	android.content.Context;

public class Util {
    public static void selectIME(Context  context) {
        InputMethodManager imeManager = (InputMethodManager) context.getApplicationContext().getSystemService(
                context.INPUT_METHOD_SERVICE);
                    if (imeManager != null) {
            imeManager.showInputMethodPicker();
        }
    }
    // https://stackoverflow.com/questions/17025150/how-to-check-if-character-is-utf-16
    public static boolean isValidSequence(CharSequence a) {
        for (int i = 0; i < a.length(); i++) {
            if (Character.isHighSurrogate(a.charAt(i))) {
                if (i < a.length() - 1 && Character.isLowSurrogate(a.charAt(i+1))) {
                    i++;
                } else {
                    return false;
                }
            } else if (Character.isLowSurrogate(a.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
