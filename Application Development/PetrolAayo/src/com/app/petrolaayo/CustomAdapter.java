package com.app.petrolaayo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	Context context;
	ArrayList<String> group,pump,address,petrol,diesel;
	LayoutInflater inflates;
	
	CustomAdapter(MainActivity mainActivity, ArrayList<String> group, ArrayList<String> pump, ArrayList<String> address, ArrayList<String> petrol, ArrayList<String> diesel){
		this.context=mainActivity;
		this.group=group;
		this.pump=pump;
		this.address=address;
		this.petrol=petrol;
		this.diesel=diesel;
		this.inflates=(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return this.group.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		Holder h1;
		if(vi==null){
			vi=this.inflates.inflate(R.layout.mylayouts, null);
			h1=new Holder();
			h1.group=(TextView)vi.findViewById(R.id.Group);
			h1.pump=(TextView)vi.findViewById(R.id.Pump);
			h1.address=(TextView)vi.findViewById(R.id.Address);
			h1.petrol=(TextView)vi.findViewById(R.id.Petrol);
			h1.diesel=(TextView)vi.findViewById(R.id.Diesel);
			vi.setTag(h1);
		}else{
			h1=(Holder)vi.getTag();
		}
		h1.group.setText(this.group.get(position));
		h1.pump.setText(this.pump.get(position));
		h1.address.setText(this.address.get(position));
		h1.petrol.setText(this.petrol.get(position));
		h1.diesel.setText(this.diesel.get(position));
		return vi;
	}

	public class Holder {
		public TextView group,pump,address,petrol,diesel;
	}
	
}
