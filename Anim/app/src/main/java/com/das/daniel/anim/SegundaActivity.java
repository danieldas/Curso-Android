package com.das.daniel.anim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_segunda);
    }
}
