package com.sheygam.masa_2017_13_12_part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBtn = findViewById(R.id.get_btn);
        getBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.get_btn){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        String result = HttpProvider.getInstance().getSteps();
                        Steps steps = new Gson().fromJson(result,Steps.class);
                        Log.d("MY_TAG", "run: " + steps.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
