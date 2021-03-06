package com.example.marijn.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class DisplayText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_text);

        // Get the full story
        String fullStory = getIntent().getExtras().getString("FullStory");

        // Treat the story as html and display it
        ((TextView) findViewById(R.id.storyText)).setText(Html.fromHtml(fullStory));
    }

    // On button pressed, go back to the ChooseText activity to do another
    public void createAnotherStory(View view) {
        startActivity(new Intent(DisplayText.this, ChooseText.class));
    }

    // Go back to main activity instead of add words on back pressed
    @Override
    public void onBackPressed() {
        startActivity(new Intent(DisplayText.this, MainActivity.class));
    }
}
