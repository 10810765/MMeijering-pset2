package com.example.marijn.madlibs;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseText extends AppCompatActivity {


    // Create a list with all stories
    String[] allTexts = {"madlib0_simple", "madlib1_tarzan", "madlib2_university", "madlib3_clothes", "madlib4_dance" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_text);

        ListView storyList = findViewById(R.id.listView);

        // Populate a List from Array elements
        // Source: https://android--code.blogspot.com/2015/08/android-gridview-add-item.html
        final List<String> textList = new ArrayList<>(Arrays.asList(allTexts));

        // Create a new ArrayAdapter
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<>
                (ChooseText.this,android.R.layout.simple_list_item_1, textList);

        // Data bind GridView with ArrayAdapter (String Array elements)
        storyList.setAdapter(gridViewArrayAdapter);

        // Set on click listener to save the chosen story
        storyList.setOnItemClickListener(new ListItemClickListener());

        }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedStory = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(ChooseText.this, AddWords.class);
            intent.putExtra("clicked_story", clickedStory);
            startActivity(intent);
        }
    }
}
