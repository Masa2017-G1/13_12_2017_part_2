package com.sheygam.masa_2017_13_12_part2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AddBranchActivity extends AppCompatActivity {
    private Spinner langSpinner, catSpinner;
    private EditText inputName, inputAddress, inputPhone, inputUrl;
    private CheckBox[] arrCheck = new CheckBox[7];
    private EditText[] arrInputsFrom = new EditText[7];
    private EditText[] arrInputsTo = new EditText[7];

    private String[] langs = {"en","ru","he","fr"};
    private String[] cats = {"Bank","Hospital","Law Office","Police"};
    private boolean isUpdating = false;

    private FrameLayout progressFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);
        langSpinner = findViewById(R.id.lang_spinner);
        catSpinner = findViewById(R.id.cat_spinner);
        inputName = findViewById(R.id.input_name);
        inputPhone = findViewById(R.id.input_phone);
        inputAddress = findViewById(R.id.input_address);
        inputUrl = findViewById(R.id.input_url);
        progressFrame = findViewById(R.id.progress_frame);
        progressFrame.setOnClickListener(null);

        arrCheck[0] = findViewById(R.id.sun_check);
        arrCheck[1] = findViewById(R.id.mon_check);
        arrCheck[2] = findViewById(R.id.tue_check);
        arrCheck[3] = findViewById(R.id.wed_check);
        arrCheck[4] = findViewById(R.id.thu_check);
        arrCheck[5] = findViewById(R.id.fri_check);
        arrCheck[6] = findViewById(R.id.sat_check);

        arrInputsFrom[0] = findViewById(R.id.input_from_sun);
        arrInputsFrom[1] = findViewById(R.id.input_from_mon);
        arrInputsFrom[2] = findViewById(R.id.input_from_tue);
        arrInputsFrom[3] = findViewById(R.id.input_from_wed);
        arrInputsFrom[4] = findViewById(R.id.input_from_thu);
        arrInputsFrom[5] = findViewById(R.id.input_from_fri);
        arrInputsFrom[6] = findViewById(R.id.input_from_sat);

        arrInputsTo[0] = findViewById(R.id.input_to_sun);
        arrInputsTo[1] = findViewById(R.id.input_to_mon);
        arrInputsTo[2] = findViewById(R.id.input_to_tue);
        arrInputsTo[3] = findViewById(R.id.input_to_wed);
        arrInputsTo[4] = findViewById(R.id.input_to_thu);
        arrInputsTo[5] = findViewById(R.id.input_to_fri);
        arrInputsTo[6] = findViewById(R.id.input_to_sat);

        ArrayAdapter<String> langsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,langs);
        langSpinner.setAdapter(langsAdapter);
        ArrayAdapter<String> catsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cats);
        catSpinner.setAdapter(catsAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_branch_activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.done_item){
            if(isUpdating){
                Toast.makeText(this, "Wait updating finish!", Toast.LENGTH_SHORT).show();
            }else{
                new SendBranchTask().execute();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private Branch getBranch(){
        Branch branch = new Branch();
        String lang = langSpinner.getSelectedItem().toString();
        String cat = catSpinner.getSelectedItem().toString();
        String name = inputName.getText().toString();
        String address = inputAddress.getText().toString();
        String placeId = "GVDhkhbkjsdfhjkshfdjCNC";
        double lat = 20.345;
        double lon = 20.4567;
        String phone = inputPhone.getText().toString();
        String[] phones = {phone};
        String url = inputUrl.getText().toString();
        String[] sch = new String[7];
        for (int i = 0; i < sch.length;i++){
            boolean isChecked = arrCheck[i].isChecked();
            if(isChecked){
                sch[i] = arrInputsFrom[i].getText().toString() + "-" + arrInputsTo[i].getText().toString();
            }else{
                sch[i] = "close";
            }
        }

        branch.setLang(lang);
        branch.setName(name);
        branch.setCategory(cat);
        branch.setPhones(phones);
        branch.setPlaceId(placeId);
        branch.setLatitude(lat);
        branch.setLongitude(lon);
        branch.setUrl(url);
        branch.setSchedule(sch);
        return branch;
    }

    class SendBranchTask extends AsyncTask<Void, Void, String>{
        private Branch branch;
        private boolean isSuccess = true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressFrame.setVisibility(View.VISIBLE);
            branch = getBranch();
            isUpdating = true;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = "Send success";
            String data =  new Gson().toJson(branch);
            Log.d("MY_TAG", "doInBackground: data: " + data);
            try {
//                HttpProvider.getInstance().sendBranch(data);
                OkHttpProvider.getInstance().sendBranch(data);
            } catch (IOException ex){
                ex.printStackTrace();
                result = "Connection error! Check your internet!";
                isSuccess = false;
            }catch (Exception e) {
                e.printStackTrace();
                result = "Server error! Call to support";
                isSuccess = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(AddBranchActivity.this, s, Toast.LENGTH_SHORT).show();
            progressFrame.setVisibility(View.GONE);
            if(isSuccess){
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        AddBranchActivity.this.finish();
                    }
                }, 1000);
            }else{
                isUpdating = false;
            }
        }
    }
}
