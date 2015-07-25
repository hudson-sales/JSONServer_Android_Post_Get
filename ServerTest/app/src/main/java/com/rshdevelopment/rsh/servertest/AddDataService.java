package com.rshdevelopment.rsh.servertest;

import android.app.IntentService;
import android.content.Intent;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ScottHanlon on 7/24/15.
 */
public class AddDataService extends IntentService{

    public AddDataService() {
        super("AddDataService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ArrayList<CustomClass> data = (ArrayList<CustomClass>) intent.getSerializableExtra("DATA");

        try {
            HttpClient client = new DefaultHttpClient();

            //******* change this to your local IP address/
            HttpPost post = new HttpPost("http://192.168.2.5:26080/info");
            // ********


            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("FirstName", data.get(0).FirstName + ""));
            params.add(new BasicNameValuePair("LastName", data.get(0).LastName + ""));
            params.add(new BasicNameValuePair("Hobby", data.get(0).Hobby + ""));
            params.add(new BasicNameValuePair("PhoneNumber", data.get(0).PhoneNumber + ""));

            UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            post.setEntity(ent);
            client.execute(post);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
