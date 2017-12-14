package com.sheygam.masa_2017_13_12_part2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StepViewActivity extends AppCompatActivity {

    private TextView stepNumberTxt, titleTxt, descTxt, needTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_view);
        stepNumberTxt = findViewById(R.id.step_number_txt);
        titleTxt = findViewById(R.id.title_txt);
        descTxt = findViewById(R.id.desc_txt);
        needTxt = findViewById(R.id.need_txt);
        Intent intent = getIntent();
        if(intent.getExtras()!=null){
            Step step = (Step) intent.getSerializableExtra("STEP");
            if (step != null){
                stepNumberTxt.setText(String.valueOf(step.getNumberOfStep()));
                titleTxt.setText(step.getTitle());
                descTxt.setText(step.getDescription());
                needTxt.setText(step.getNeed());
            }
        }
    }
}
