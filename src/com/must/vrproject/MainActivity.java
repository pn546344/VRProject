package com.must.vrproject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.AlteredCharSequence;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView list;
	private LazyAdapter adapter;
	private LocationManager lManager;
	private Location loc;
	private String bestGPS=null,address="" , select=null;
	private double latiude=0,longitude=0;				//�n��,�g��
	private String[] item , pic;
	private String imageUrls[] = {
            "http:/120.105.81.47/login/images/view/1.jpg",
            "http://120.105.81.47/login/images/view/2.jpg",   
    };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();
		bar.setTitle("�z���񪺳]�I");
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		list = (ListView)findViewById(R.id.listView1);
		list.setOnItemClickListener(this);
		
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
		GetBigJson gbJson = new GetBigJson(address);
		gbJson.start();
		try {
			gbJson.join();
			item = gbJson.getItem();
			pic = gbJson.getPic();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		adapter = new LazyAdapter(this, pic , item);
		
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		select = item[arg2];
//		Log.i("ttt", "���="+select);
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("ĵ�i");
		dialog.setMessage("�T�w�n����U��?");
		dialog.setPositiveButton("�T�w",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				showProgressDialog();
			}
		});
		dialog.setNegativeButton("����", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.setCancelable(false);  //�T��Ĳ�οù��N��dialog����
		dialog.show();
		
	}
	
	private void showProgressDialog() {
		// TODO Auto-generated method stub
		ProgressDialog proDialog = new ProgressDialog(this);
		proDialog.setTitle("�U���i��");
		proDialog.setMessage("�U����.....");
		proDialog.setMax(100);
		proDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		proDialog.setIndeterminate(true);
		proDialog.setCancelable(false);			//�T��Ĳ�οù��N��prodialog����
		proDialog.show();
		
		GetSmallJson gsJson = new GetSmallJson(select);
		gsJson.start();
	}
	
}
