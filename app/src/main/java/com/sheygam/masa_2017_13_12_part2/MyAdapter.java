package com.sheygam.masa_2017_13_12_part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gregorysheygam on 14/12/2017.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Step> steps;

    public MyAdapter(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public void clearList(){
        if(steps != null){
            steps.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public Object getItem(int position) {
        return steps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_row,parent,false);
        }
        Step step = steps.get(position);
        TextView titleTxt = convertView.findViewById(R.id.title_txt);
        TextView descTxt = convertView.findViewById(R.id.description_txt);
        titleTxt.setText(step.getTitle());
        descTxt.setText(step.getDescription());
        return convertView;
    }
}
