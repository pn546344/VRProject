package com.must.vrproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class GetSmallJson extends Thread{

	String result="",address=null , getData;
	
	public GetSmallJson(String url) {
		getData = url;
		try {
			getData	= URLEncoder.encode(getData,"utf-8");
			address = "http://120.105.81.47/login/small_android.php?viewname="+getData;
			Log.i("url", address);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.i("ttt", "small address="+address);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			if(connection.getResponseCode()==HttpURLConnection.HTTP_OK)
			{
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream(), "utf-8"));
				String str;
				while((str=reader.readLine())!=null)
				{
					result = result +str;
				}
				reader.close();
				connection.disconnect();
				Log.i("ttt", "small result="+result);
			/*	JSONArray jsonArray = new JSONArray(result);
				item = new String[jsonArray.length()];
				pic = new String[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject json = jsonArray.getJSONObject(i);
					item[i] = json.getString("View_Name");
					pic[i] = json.getString("View_Logo");
				}*/
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("ttt", "Malformed");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("ttt", "Unsupport");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("ttt", "Io");
		}
		super.run();
	}
}
