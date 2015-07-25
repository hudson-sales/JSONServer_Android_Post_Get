package com.rshdevelopment.rsh.servertest;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by ScottHanlon on 7/24/15.
 */
public class AddDataFragment extends Fragment implements View.OnClickListener{


    public static final String AddDataFragmentTag = "AddDataFragmentTag";


    sendDataToActivity sendData;

    public static interface sendDataToActivity{
        public void startPostService(ArrayList<CustomClass> data);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof sendDataToActivity){
            sendData = (sendDataToActivity)activity;
        }
    }

    public static AddDataFragment newInstance(){
        return new AddDataFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_data_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.sendData).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        ArrayList<CustomClass> data = new ArrayList<>();
        data.add(new CustomClass(
                ((EditText)getView().findViewById(R.id.inputOne)).getText().toString(),
                ((EditText)getView().findViewById(R.id.inputTwo)).getText().toString(),
                ((EditText)getView().findViewById(R.id.inputThree)).getText().toString(),
                ((EditText)getView().findViewById(R.id.inputFour)).getText().toString()));

        sendData.startPostService(data);

    }



}
