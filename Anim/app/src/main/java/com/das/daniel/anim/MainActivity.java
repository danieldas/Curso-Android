package com.das.daniel.anim;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this,
                        SegundaActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        TextView textView =(TextView)findViewById(R.id.texto);

        switch (item.getItemId()){
            case R.id.alpha:
                Animation animation = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.alpha);
                textView.startAnimation(animation);
                return  true;

            case R.id.scale:
                animation = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.scale);
                textView.startAnimation(animation);
                return  true;

            case R.id.translate:
                 animation = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.translate);
                textView.startAnimation(animation);
                return  true;

            case R.id.rotate:
                 animation = AnimationUtils.loadAnimation(
                        getApplicationContext(), R.anim.rotate);
                textView.startAnimation(animation);
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }



    }
}
