package org.kauthara.chamthrah;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.KeyEvent;

public class ChamKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard, keyboard2;

    private boolean isCaps = false;
    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.querty);
        keyboard2 = new Keyboard(this, R.xml.querty2);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {
        InputConnection ic = getCurrentInputConnection();
        playClick(i);
        switch (i) {
            case -100:
                Util.selectIME(this);
                break;
            case Keyboard.KEYCODE_DELETE:
                CharSequence lastOne = ic.getTextBeforeCursor(1, 0);
                if (!Util.isValidSequence(lastOne)) {
                    ic.deleteSurroundingText(2,0);
                } else {
                    ic.deleteSurroundingText(1, 0);
                }
                break;
            case Keyboard.KEYCODE_DONE:
                 ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_SHIFT:
                if (isCaps) {
                    kv.setKeyboard(keyboard);
                } else {
                    kv.setKeyboard(keyboard2);
                }
                isCaps = !isCaps;
                break;
            default:
                // char code = (char) i;
                // if(Character.isLetter(code) && isCaps)
                //    code = Character.toUpperCase(code);
                // ic.commitText(new String(new int[]{code}, 0, 1), 1);
                ic.commitText(new String(Character.toChars(i)), 1);
        }

    }

    private void playClick(int i) {
        /*
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        switch (i) {
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
        */
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
}