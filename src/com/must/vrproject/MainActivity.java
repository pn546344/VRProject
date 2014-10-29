package com.must.vrproject;

import android.app.ActionBar;
import android.app.Activity;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView list;
	private LazyAdapter adapter;
	private LocationManager lManager;
	private Location loc;
	private String bestGPS=null,address="";
	private double latiude=0,longitude=0;				//緯度,經度
	private String imageUrls[] = {
            "http://120.105.81.47/login/images/view/1.jpg",
            "http://120.105.81.47/login/images/view/2.jpg",   
    };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();
		bar.setTitle("您附近的設施");
		list = (ListView)findViewById(R.id.listView1);
		adapter = new LazyAdapter(this, imageUrls);
		
		lManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		bestGPS = lManager.getBestProvider(criteria, true);
		if (bestGPS != null) {
			loc = lManager.getLastKnownLocation(bestGPS);
			latiude = loc.getLatitude();
			longitude = loc.getLongitude();
			address= "http://120.105.81.47/login/big_android.php?latiude="+latiude+"&longitude="+longitude;
			Log.i("ttt", "address="+address);
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		list.setAdapter(adapter);
		super.onResume();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		list.setAdapter(null);
		super.onDestroy();
	}
	
}
