package com.example.internal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    /**
     *      @author gmail address: pb7677@bs.amalnet.k12.il
     *      * @version	    2022.3.1
     *      * @since       23/11/2023
     *      this class contains 3 buttons, the "save" button which will save the data which is written
     *      and also display the written message
     *      the "reset" button which will reset(clear) any data that has been used and saved
     *      and the "exit" button which will exit the app and save any remaining data.
     */
    TextView TV;
    EditText ET;
    private final String FILENAME = "r.txt";
    private final String FILENAME2 = "text.txt";
    String Text2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TV = findViewById(R.id.textView);
        ET = findViewById(R.id.editTextTextMultiLine);




        try {
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            fIS.close();
            TV.setText(sB.toString());
        }catch (Exception e){
            Toast.makeText(this, "Error inttest", Toast.LENGTH_SHORT).show();
        }

        try{
            FileInputStream fIS = openFileInput(FILENAME2);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            bR.close();
            iSR.close();
            fIS.close();
            ET.setText(sB.toString());
        }catch (Exception e){
            Toast.makeText(this, "Error text", Toast.LENGTH_SHORT).show();
        }

    }

    public void Save(View view) {
        try {
            Text2 =  ET.getText().toString()+ Text2;
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write(Text2);
            bW.close();
            oSW.close();
            fOS.close();
        }catch (Exception e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        try {
            FileInputStream fIS = openFileInput(FILENAME);
            InputStreamReader iSR = new InputStreamReader(fIS);
            BufferedReader bR = new BufferedReader(iSR);
            StringBuilder sB = new StringBuilder();
            String line = bR.readLine();
            while (line != null) {
                sB.append(line + '\n');
                line = bR.readLine();
            }
            TV.setText(sB.toString());
            bR.close();
            iSR.close();
            fIS.close();
        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void Reset(View view) {
        try{
            FileOutputStream fOS = openFileOutput(FILENAME, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            if(bW != null) {
                bW.write("");
                ET.setText("");
                TV.setText("");
                Text2 = "";
            }
            bW.close();
            oSW.close();
            fOS.close();
        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void Exit(View view) {
        try {
            FileOutputStream fOS = openFileOutput(FILENAME2, MODE_PRIVATE);
            OutputStreamWriter oSW = new OutputStreamWriter(fOS);
            BufferedWriter bW = new BufferedWriter(oSW);
            bW.write(ET.getText().toString());
            bW.close();
            oSW.close();
            fOS.close();
        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("credits");
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("credits")) {
            Intent Di = new Intent(this, credits.class);
            startActivity(Di);
        }
        return super.onOptionsItemSelected(item);
    }






}