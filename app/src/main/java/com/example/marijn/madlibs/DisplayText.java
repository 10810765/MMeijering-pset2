package com.example.marijn.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);

        // Get the full story
        String fullStory = getIntent().getExtras().getString("FullStory");

        // Display the full story
        ((TextView) findViewById(R.id.storyText)).setText(fullStory);
    }
}
