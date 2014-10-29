package com.must.vrproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class GetBigJson extends	Thread{
	
	String result="",address=null;
	String[] item,pic;
	public GetBigJson(String url) {
		address = url;
	}
	
	public String[] getItem() {
		return item;
	}
	
	public String[] getPic() {
		return pic;
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
				JSONArray jsonArray = new JSONArray(result);
				item = new String[jsonArray.length()];
				pic = new String[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject json = jsonArray.getJSONObject(i);
					item[i] = json.getString("View_Name");
					pic[i] = json.getString("View_Logo");
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
}
