package com.app.petrolaayo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ArrayList<String> group,pump,address,petrol,diesel;
	ListView view;
	TextView view1;
	SharedPreferences pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startService(new Intent(MainActivity.this,ServiceIntent.class));
		view=(ListView)findViewById(R.id.listView);
		view1=(TextView)findViewById(R.id.Date);
		pref=getSharedPreferences("pref", Context.MODE_PRIVATE);
		String keys=pref.getString("MyKeys", "Hello");
		if(keys.equals("Hello")){
			AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
			dialog.setTitle("Error").setMessage("Internet is not connected");
			dialog.setPositiveButton("Quit",new OnClickListener() {
				public void onClick(DialogInterface arg0, int arg1) {
					MainActivity.this.finish();
				}
			});
			dialog.show();
		}else{
			try{
				JSONArray arr = new JSONArray(keys);
				view1.setText(arr.getJSONObject(0).getString("miti"));
				group=new ArrayList<String>();
				pump=new ArrayList<String>();
				address=new ArrayList<String>();
				petrol=new ArrayList<String>();
				diesel=new ArrayList<String>();
				for(int i=0;i<arr.length();i++){
					group.add(arr.getJSONObject(i).getString("group"));
					pump.add(arr.getJSONObject(i).getString("pump"));
					address.add(arr.getJSONObject(i).getString("address"));
					petrol.add(arr.getJSONObject(i).getString("petrol"));
					diesel.add(arr.getJSONObject(i).getString("diesel"));
					CustomAdapter adap=new CustomAdapter(MainActivity.this,group,pump,address,petrol,diesel);
					view.setAdapter(adap);
				}
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
