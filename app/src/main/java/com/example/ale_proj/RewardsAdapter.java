package com.example.ale_proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RewardsAdapter extends ArrayAdapter<Rewards> {
    public RewardsAdapter(Context context, ArrayList<Rewards> rewards) {
        super(context, R.layout.record_adapter_item, rewards);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Rewards rewards = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.record_adapter_item, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.number)).setText(String.valueOf(rewards.getId()));
        ((TextView) convertView.findViewById(R.id.name)).setText(String.valueOf(rewards.getUsers()));
        ((TextView) convertView.findViewById(R.id.record)).setText(String.valueOf(rewards.getRecord()));


        return convertView;
    }

}
