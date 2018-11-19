package com.example.marijn.madlibs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseText extends AppCompatActivity {

    String[] allTexts = {"madlib0_simple", "madlib1_tarzan"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_text);

        for (String button : allTexts) {
            ListView list = findViewById(R.id.listView);

            list.setText();

        }

        adapter.add("New Item");

        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);

        TextView grid = findViewById(R.id.gridDisplay);

        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new GridItemClickListener());
    }
}

// InputStream is = getResources().openRawResource(R.raw.madlib0_simple);