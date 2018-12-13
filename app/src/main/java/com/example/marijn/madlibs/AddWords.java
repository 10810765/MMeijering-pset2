package com.example.marijn.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class AddWords extends AppCompatActivity {

    // Variable used to hold the story
    private Story story;

    // Various variables
    private EditText filledInWord;
    private TextView wordCount, title, whichText;
    private Button confirmBtn, showStoryBtn;
    private String storyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        // Get various ID's
        filledInWord = findViewById(R.id.enterWord);
        wordCount = findViewById(R.id.wordsLeft);
        title = findViewById(R.id.titleText);
        confirmBtn = findViewById(R.id.confirmWord);
        showStoryBtn = findViewById(R.id.nextButton);
        whichText = findViewById(R.id.chosenText);

        // Get the name of the chosen story
        storyName = getIntent().getExtras().getString("clicked_story");

        // Set the story chosen by the user
        getStory();

        // Tell the user what text he's chosen
        whichText.setText("You have chosen the text: " + storyName);

        // Set the title of the activity_add_words view
        title.setText("Fill in " + story.getPlaceholderCount() + " words to complete the story!");

        // If there is no saved state set the text, else restore the saved state
        if (savedInstanceState == null) {
            setText();
            return;
        } else {
            story = (Story) savedInstanceState.getSerializable("story");
            setText();
            isAllFilled();
        }
    }

    // Get the story (text) chosen by the user
    public Story getStory() {
        InputStream storyTxt = getResources().openRawResource(getResources().getIdentifier(
                storyName, "raw", getPackageName()));
        return story = new Story(storyTxt);
    }

    // Retrieve the word the user typed in
    public String getWord() {
        return String.valueOf(filledInWord.getText());
    }

    // Save the word the user typed in
    public void setWord(View view) {
        if (filledInWord.getText().toString().equals("")) {
            Toast.makeText(this, "The textfield cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            story.fillInPlaceholder(getWord());
            // Set text fields
            setText();
            // Check if all words have been filled in
            isAllFilled();
        }
    }

    // Set the text of various text fields
    private void setText(){
        filledInWord.getText().clear();
        filledInWord.setHint("Type a/an " + story.getNextPlaceholder());
        wordCount.setText(story.getPlaceholderRemainingCount() + " word(s) left");
    }

    // Check if all words in the text have been filled
    private void isAllFilled() {
        if (story.isFilledIn() == true) {
            filledInWord.setVisibility(View.INVISIBLE);
            confirmBtn.setVisibility(View.INVISIBLE);
            whichText.setVisibility(View.INVISIBLE);
            showStoryBtn.setVisibility(View.VISIBLE);
            title.setText("You have completed the story!");
        }
    }

    // On button pressed, show the created story
    public void showStory(View view) {
        Intent showStory = new Intent(AddWords.this, DisplayText.class);
        showStory.putExtra("FullStory", story.toString());
        startActivity(showStory);
    }

    // Save the state of the current story
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("story", story);

    }
}