package com.sheygam.masa_2017_13_12_part2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private boolean isUpdating = false;
    private ListView myList;
    private FrameLayout progressFrame;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressFrame = findViewById(R.id.progress_frame);
        myList = findViewById(R.id.my_list);
        progressFrame.setOnClickListener(null);
        myList.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new UpdateListTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(isUpdating){
            Toast.makeText(this, "Wait updating finish!", Toast.LENGTH_SHORT).show();
        }else{
            if(item.getItemId() == R.id.refresh_item){
                new UpdateListTask().execute();
            }else if(item.getItemId() == R.id.add_item){
                startActivity(new Intent(this,AddBranchActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(adapter!=null){
            Step step = (Step) adapter.getItem(position);
            Intent intent = new Intent(this,StepViewActivity.class);
            intent.putExtra("STEP",step);
            startActivity(intent);
        }
    }

    class UpdateListTask extends AsyncTask<Void,Void,String>{
        private Steps steps;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(adapter != null){
                adapter.clearList();
            }
            progressFrame.setVisibility(View.VISIBLE);
            isUpdating = true;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = "Updating Success";
            try {
//                String response = HttpProvider.getInstance().getSteps();
                String response = OkHttpProvider.getInstance().getSteps();
                steps = new Gson().fromJson(response,Steps.class);
            } catch (IOException ex){
                ex.printStackTrace();
                result = "Connection error!Check your internet!";
            } catch (Exception e) {
                e.printStackTrace();
                result = "Server error";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            adapter = new MyAdapter(steps.getSteps());
            myList.setAdapter(adapter);
            progressFrame.setVisibility(View.GONE);
            isUpdating = false;
        }
    }
}
