package com.example.ale_proj;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityRecord extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_record);

        ListView listView = findViewById(R.id.record_ListView);

        ArrayList<Rewards> rewards = new Database(this).selectAll();
        Collections.sort(rewards, new Comparator<Rewards>() {
            @Override
            public int compare(Rewards rewards, Rewards t1) {
                if (t1.getRecord() == rewards.getRecord()){
                    if (t1.getUsers().equals(rewards.getUsers())){
                        return (int) (t1.getId() - rewards.getId());
                    }
                    return t1.getUsers().compareTo(rewards.getUsers());
                }
                return t1.getRecord() - rewards.getRecord();
            }
        });
        RewardsAdapter rewardsAdapter = new RewardsAdapter(this, rewards);

        listView.setAdapter(rewardsAdapter);
    }
}
