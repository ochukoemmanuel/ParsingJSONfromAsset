package com.ebeatsz.parsingjsonfromasset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // 1 - Widgets
    RecyclerView recyclerView;

    ArrayList<String> personName = new ArrayList<>();
    ArrayList<String> emailIds = new ArrayList<>();
    ArrayList<String> mobileNumbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        try {
            JSONObject obj = new JSONObject(loadJSONfromAssets());
            JSONArray userArray = obj.getJSONArray("users");

            for (int i = 0; i < userArray.length(); i++){
                JSONObject userDetail = userArray.getJSONObject(i);

                personName.add(userDetail.getString("name"));
                emailIds.add(userDetail.getString("email"));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

    }

    private String loadJSONfromAssets() {
        String json = null;

        try {
            InputStream is = getAssets().open("users_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
        return json;
    }
}