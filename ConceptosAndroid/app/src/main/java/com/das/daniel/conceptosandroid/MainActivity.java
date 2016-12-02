package com.das.daniel.conceptosandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        //tab1
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("CONCEPTOS");
        tabs.addTab(spec);
        //tab2
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("ATRIBUTOS");
        tabs.addTab(spec);
        tabs.setCurrentTab(0);

    }
}

