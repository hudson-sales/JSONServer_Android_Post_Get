package com.rshdevelopment.rsh.servertest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    MainActivityFragment mainActivityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityFragment = MainActivityFragment.newInstance();

        getFragmentManager().beginTransaction().replace(
                R.id.container,
                mainActivityFragment,
                MainActivityFragment.FragmentTag)
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, GetDataService.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_data) {

            startActivity(new Intent(this, AddDataActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter("sendData.data.userInfo"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            data = (ArrayList<CustomClass>)intent.getSerializableExtra("DATA");
            userInformationData adapter = new userInformationData(getApplicationContext());
            mainActivityFragment.setListAdapter(adapter);

        }
    };


    ArrayList<CustomClass> data = new ArrayList<>();

    public class userInformationData extends BaseAdapter {

        Context mContext;

        public userInformationData(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            if (data.size() != 0) {
                return data.size();
            }
            return 0;
        }

        @Override
        public CustomClass getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, parent, false);
            }

            ((TextView)convertView.findViewById(R.id.firstNameTV)).setText(data.get(position).FirstName);
            ((TextView)convertView.findViewById(R.id.lastNameTV)).setText(data.get(position).LastName);
            ((TextView)convertView.findViewById(R.id.hobbyTV)).setText(data.get(position).Hobby);
            ((TextView)convertView.findViewById(R.id.phoneNumberTV)).setText(data.get(position).PhoneNumber);


            return convertView;
        }

    }
}
