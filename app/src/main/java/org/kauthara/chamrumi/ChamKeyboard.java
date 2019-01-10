package org.kauthara.chamrumi;

import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.KeyEvent;

public class ChamKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboardAiueo, keyboardNumber, keyboardLatin;

    private boolean isCaps = false;
    private boolean isLatin = true;
    private ChamLatin chamLatin = new ChamLatin();
    private int indexInList = 0;

    @Override
    public View onCreateInputView() {
        kv = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboardAiueo = new Keyboard(this, R.xml.aiueo);
        keyboardNumber = new Keyboard(this, R.xml.number);
        keyboardLatin = new Keyboard(this, R.xml.latin);
        // keyboardLatinUpper = new Keyboard(this, R.xml.latin_upper);
        kv.setKeyboard(keyboardLatin);
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
    public void onKey(int character, int[] ints) {
        InputConnection ic = getCurrentInputConnection();
        playClick(character);
        switch (character) {
            case -100:
                Util.selectIME(this);
                this.reset(ic);
                break;
            case -101:
                if (isLatin) {
                    kv.setKeyboard(keyboardAiueo);
                } else {
                    kv.setKeyboard(keyboardLatin);
                }
                isLatin = !isLatin;
                this.reset(ic);
                break;
            case -103:
                sendNextString(ic);
                break;
            case -104:
                sendEnd(ic);
                break;
            case Keyboard.KEYCODE_DELETE:
                if (!isLatin) {
                    simpleDelete(ic);
                } else {
                    sendLatinDelete(ic);
                }
                break;
            case Keyboard.KEYCODE_DONE:
                handleActionDone();
                this.reset(ic);
                break;
            case Keyboard.KEYCODE_SHIFT:
                if (isCaps) {
                    if (!isLatin) {
                        kv.setKeyboard(keyboardAiueo);
                    } else {
                        kv.setKeyboard(keyboardLatin);
                    }
                } else {
                    kv.setKeyboard(keyboardNumber);
                }
                isCaps = !isCaps;
                this.reset(ic);
                break;
            default:
                if (isLatin) {
                    sendLatin(ic, character, indexInList);
                } else {
                    sendEnd(ic);
                    icCommitInt(ic, character);
                }
        }
    }

    private void sendLatin(InputConnection ic, int character, int indexInList) {
        if ((character < 97 || character > 122) && character != 226 && character != 45) {
            String convert = chamLatin.convert(indexInList, this);
            if (convert != null) {
                ic.commitText(convert, 1);
            }
            icCommitInt(ic, character);
            chamLatin.reset();
            this.indexInList = 0;
        } else {
            chamLatin.addKey(new String(Character.toChars(character)));
            String textConvert = chamLatin.convert(indexInList, this);
            ic.setComposingText(textConvert, 1);
        }
    }

    private void sendLatinDelete(InputConnection ic) {
        if (chamLatin.getWord().length() > 0) {
            if (chamLatin.getWord().length() == 1) {
                this.reset(ic);
            }
            chamLatin.delete();
            String textConvert = chamLatin.convert(indexInList, this);
            ic.setComposingText(textConvert, 1);
        } else {
            simpleDelete(ic);
        }
    }

    private void sendNextString(InputConnection ic) {
        String textConvert;
        if (indexInList == 0) {
            indexInList = 1;
        } else {
            indexInList = 0;
        }
        textConvert = chamLatin.convert(indexInList, this);
        ic.setComposingText(textConvert, 1);
    }

    private void sendEnd(InputConnection ic) {
        String convert = chamLatin.convert(indexInList, this);
        if (convert != null) {
            ic.commitText(convert, 1);
        }
        indexInList = 0;
        chamLatin.reset();
    }

    private void simpleDelete(InputConnection ic) {
        CharSequence lastOne = ic.getTextBeforeCursor(1, 0);
        if (!Util.isValidSequence(lastOne)) {
            ic.deleteSurroundingText(2, 0);
        } else {
            ic.deleteSurroundingText(1, 0);
        }
        ic.commitText("", 1);
    }

    private void icCommitInt(InputConnection ic, int i) {
        ic.commitText(new String(Character.toChars(i)), 1);
    }

    private void handleActionDone() {
        EditorInfo curEditor = getCurrentInputEditorInfo();
        switch (curEditor.imeOptions & EditorInfo.IME_MASK_ACTION) {
            case EditorInfo.IME_ACTION_DONE:
                // getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_DONE);
                // fix for messager, not work
                getCurrentInputConnection().sendKeyEvent(
                        new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                getCurrentInputConnection().sendKeyEvent(
                        new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
                break;
            case EditorInfo.IME_ACTION_GO:
                getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_GO);
                break;
            case EditorInfo.IME_ACTION_NEXT:
                getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_NEXT);
                break;
            case EditorInfo.IME_ACTION_SEARCH:
                getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_SEARCH);
                break;
            case EditorInfo.IME_ACTION_SEND:
               // if (curEditor.imeOptions == EditorInfo.IME_FLAG_NO_ENTER_ACTION) {
               //     getCurrentInputConnection().performEditorAction(EditorInfo.IME_ACTION_SEND);
               // } else {
                    getCurrentInputConnection().sendKeyEvent(
                            new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                    getCurrentInputConnection().sendKeyEvent(
                            new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
                // }
                break;
            default:
                getCurrentInputConnection().sendKeyEvent(
                        new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                getCurrentInputConnection().sendKeyEvent(
                        new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ENTER));
                break;
        }
    }

    public void reset(InputConnection ic) {
        sendEnd(ic);
        indexInList = 0;
        chamLatin.reset();
    }

    public void pickSuggestionManually(int index) {

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