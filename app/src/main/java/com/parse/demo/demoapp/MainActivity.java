package com.parse.demo.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {


    public static final String APPLICATION_ID = "SGeYuXVlPid56Obae9nz3fB75kRIV7gfzByRIPdn";
    public static final String CLIENT_KEY = "Lj6KbsPotHTU9aUGyfcA6W2024MCldTngM42DZtx";
    public static final String MASTER_KEY = "DhJOlV4JU49MCHzglJsBjrjxlmLCiH50leM4rFDc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Categories");
        query.whereEqualTo("name", "Coffee");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> coursesList, ParseException e) {
                ArrayList<String> courses = null;
                if (e == null) {
                    courses = new ArrayList<String>();
                    for (ParseObject course : coursesList) {
                        String courseName = course.getString("name");
                        courses.add(courseName);
                        System.out.println("---- name " + courseName);
                    }
                } else {
                    Log.d("Post retrieval", "Error: " + e.getMessage());
                }


            }
        });


        // Insert data
        final ParseObject ocarina = new ParseObject("Coupons");

        // ParseObjects are just property bags, so we get no assurances that we're putting
        // the right kinds of objects in our Instrument.
        ocarina.put("codeString", "xxx-yyy");
        ocarina.put("couponType", 1);
        ocarina.put("discountType", 1);
        ocarina.put("discountValue", "100");
        ocarina.saveInBackground(new SaveCallback() {
            public void done(ParseException e) {
                // We have to use utility classes to add behavior to our ParseObjects.
                if (e != null)
                    System.out.println("Insert data successed");
                else
                    System.out.print(" ------- Messages recived " + e.getMessage());
            }
        });


    }
}
