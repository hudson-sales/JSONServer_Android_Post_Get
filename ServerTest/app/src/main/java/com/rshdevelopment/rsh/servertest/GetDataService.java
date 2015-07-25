package com.rshdevelopment.rsh.servertest;

import android.app.IntentService;
import android.content.Intent;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by ScottHanlon on 7/24/15.
 */
public class GetDataService extends IntentService{

    public GetDataService() {
        super("GetDataService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        ArrayList<CustomClass> data = new ArrayList<>();

        try {
            HttpClient client = new DefaultHttpClient();

            //***** change this to your local IP address
            String getURL = "http://192.168.2.5:26080/info";


            HttpGet get = new HttpGet(getURL);
            HttpResponse responseGet = client.execute(get);


            JSONArray object = new JSONArray(EntityUtils.toString(responseGet.getEntity()));

            for (int i = 0; i < object.length(); i++){

                JSONObject jsonObject = object.getJSONObject(i);

                // Gets the display name
                String FirstName = jsonObject.getString("FirstName");
                String LastName = jsonObject.getString("LastName");
                String Hobby = jsonObject.getString("Hobby");
                String PhoneNumber = jsonObject.getString("PhoneNumber");

                data.add(new CustomClass(FirstName, LastName, Hobby, PhoneNumber));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent broadcast = new Intent();
        broadcast.setAction("sendData.data.userInfo");
        broadcast.putExtra("DATA", data);
        sendBroadcast(broadcast);
    }



}
