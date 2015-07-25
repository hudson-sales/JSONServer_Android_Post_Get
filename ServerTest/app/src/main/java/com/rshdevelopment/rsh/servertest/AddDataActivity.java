package com.rshdevelopment.rsh.servertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by ScottHanlon on 7/24/15.
 */
public class AddDataActivity extends Activity implements AddDataFragment.sendDataToActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().replace(
                R.id.container,
                AddDataFragment.newInstance(),
                AddDataFragment.AddDataFragmentTag)
                .commit();

    }

    @Override
    public void startPostService(ArrayList<CustomClass> data) {

        Intent startServiceIntent = new Intent(this, AddDataService.class);
        startServiceIntent.putExtra("DATA", data);
        startService(startServiceIntent);
    }
}
