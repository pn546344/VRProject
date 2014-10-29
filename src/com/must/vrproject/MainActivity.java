package com.must.vrproject;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView list;
	LazyAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar bar = getActionBar();
		bar.setTitle("您附近的設施");
		list = (ListView)findViewById(R.id.listView1);
		adapter = new LazyAdapter(this, imageUrls);
		list.setAdapter(adapter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		list.setAdapter(null);
		super.onDestroy();
	}
	private String imageUrls[] = {
            "http://120.105.81.47/login/images/view/1.jpg",
            "http://120.105.81.47/login/images/view/2.jpg",   
    };
}
