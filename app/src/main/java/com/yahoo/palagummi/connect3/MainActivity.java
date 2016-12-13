package com.yahoo.palagummi.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void dropIn(View view) {
        ImageView counter = (ImageView) view; // need not search for the id because it is what the user tapped on
        counter.setTranslationY(-1000f); // initially move it off the screen
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
