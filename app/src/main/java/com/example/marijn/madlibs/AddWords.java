package com.example.marijn.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;

public class AddWords extends AppCompatActivity {

    private Story story;
    private EditText filledInWord;
    private TextView wordCount;
    private TextView title;
    private Button confirmBtn;
    private Button showStoryBtn;
    private TextView whatText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        filledInWord = findViewById(R.id.enterWord);
        wordCount = findViewById(R.id.wordsLeft);
        title = findViewById(R.id.titleText);
        confirmBtn = findViewById(R.id.confirmWord);
        showStoryBtn = findViewById(R.id.nextButton);
        whatText = findViewById(R.id.chosenText);

        // Set the story chosen by the user
        getStory();

        // Tell the user what text he's chosen
        whatText.setText("You have chosen the text: " + getIntent().getExtras().getString("clicked_story"));

        // Set the title of the activity_add_words view
        title.setText("Fill in " + story.getPlaceholderCount() + " words to complete the story!");

        if (savedInstanceState == null) {
            setText();
            return;
        } else {
            story = (Story) savedInstanceState.getSerializable("story");
            setText();
            isAllFilled();
        }
    }

    // Get the user chosen story to be used
    public Story getStory() {
        // Get the name of the chosen story
        String storyName = getIntent().getExtras().getString("clicked_story");
        InputStream storyTxt = getResources().openRawResource(getResources().getIdentifier(
                storyName, "raw", getPackageName()));
        return story = new Story(storyTxt);
    }

    // Get the filled in word from user
    public String getWord() {
        return String.valueOf(filledInWord.getText());
    }

    // Save the filled in words from user
    public void setWord(View view) {
        if (filledInWord.getText().toString().equals("")) {
            return;
        } else {
            story.fillInPlaceholder(getWord());
            setText();
            isAllFilled();
        }
    }

    // Set the text of various textViews
    private void setText(){
        filledInWord.getText().clear();
        filledInWord.setHint("Type a/an " + story.getNextPlaceholder());
        wordCount.setText(story.getPlaceholderRemainingCount() + " word(s) left");
    }

    // Check if all words have been filled in
    private void isAllFilled() {
        if (story.isFilledIn() == true) {
            filledInWord.setVisibility(View.INVISIBLE);
            confirmBtn.setVisibility(View.INVISIBLE);
            whatText.setVisibility(View.INVISIBLE);
            showStoryBtn.setVisibility(View.VISIBLE);
        }
    }

    // On button pressed, show the created story
    public void showStory(View view) {
        Intent showStory = new Intent(AddWords.this, DisplayText.class);
        showStory.putExtra("FullStory", story.toString());
        startActivity(showStory);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // always call super
        outState.putSerializable("story", story);

    }
}