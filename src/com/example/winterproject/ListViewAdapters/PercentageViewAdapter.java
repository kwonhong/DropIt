package com.example.winterproject.ListViewAdapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.winterproject.R;
import com.example.winterproject.Classes.Percentage_Item;

public class PercentageViewAdapter extends BaseAdapter {
	private Context mContext;
	private List<Percentage_Item> mList;
	
	 Integer[] num2 =  {
			 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5, 0
	 };
	 
	 Percentage_Item item;
	 
	
	public PercentageViewAdapter(Context context, List<Percentage_Item> list) {
		mContext = context;
		mList = list;
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		
		if(convertView == null) {
			LayoutInflater inflater = (LayoutInflater)
					mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.percentage_menu_list, null);
		}
		
		// getting the item from the particular position
		item = mList.get(position);
		
		//setting adapter for each spinner
		
		
		TextView txtTitle = (TextView)convertView.findViewById(R.id.txtCurrentTitle);
		Spinner spinner = (Spinner)convertView.findViewById(R.id.spinner1);
		ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(
				mContext, android.R.layout.simple_spinner_item, num2);
		spinner.setAdapter(adapter2);
		
		item.setSpinner(spinner);
		txtTitle.setText(item.getName());
		return convertView;
	}
	
	
}
