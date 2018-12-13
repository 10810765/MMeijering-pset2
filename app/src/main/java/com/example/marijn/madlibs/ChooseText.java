package com.example.marijn.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Marijn Meijering <m.h.j.meijering@uva.nl>
 * 10810765 Universiteit van Amsterdam
 * Minor Programmeren 17/12/2018
 */
public class ChooseText extends AppCompatActivity {

    // Create a list with all stories
    String[] allTexts = {"madlib0_simple", "madlib1_tarzan", "madlib2_university", "madlib3_clothes", "madlib4_dance" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_text);

        // Get the ListView ID
        ListView storyList = findViewById(R.id.listView);

        // Populate a List from Array elements
        // Source: https://android--code.blogspot.com/2015/08/android-gridview-add-item.html
        List<String> textList = new ArrayList<>(Arrays.asList(allTexts));

        // Instantiate the adapter for the texts
        ArrayAdapter<String> textAdapter = new ArrayAdapter<>
                (ChooseText.this,android.R.layout.simple_list_item_1, textList);

        // Attach the adapter to the list view
        storyList.setAdapter(textAdapter);

        // Set on click listener to save the chosen story
        storyList.setOnItemClickListener(new ListItemClickListener());
    }

    // Create an on text clicked listener
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Get the string of the clicked item in the list view
            String clickedStory = (String) parent.getItemAtPosition(position);

            // Pass the chosen story to the next activity
            Intent intent = new Intent(ChooseText.this, AddWords.class);
            intent.putExtra("clicked_story", clickedStory);
            startActivity(intent);
        }
    }
}
