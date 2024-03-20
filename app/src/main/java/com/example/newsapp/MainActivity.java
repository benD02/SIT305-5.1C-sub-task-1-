package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.newsapp.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new NewsList())
                    .commit();
        }
    }
}
