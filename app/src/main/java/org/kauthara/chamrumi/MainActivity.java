package org.kauthara.chamrumi;

import android.content.Intent;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void activate(View v){
        this.startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
    }

    public void select(View v){
        Util.selectIME(this);
    }
}
