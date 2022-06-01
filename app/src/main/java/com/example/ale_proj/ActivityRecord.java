package com.example.ale_proj;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityRecord extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_record);

        ListView listView = findViewById(R.id.record_ListView);

        ArrayList<Rewards> rewards = new Database(this).selectAll();

        RewardsAdapter rewardsAdapter = new RewardsAdapter(this, rewards);

        listView.setAdapter(rewardsAdapter);
    }
}
