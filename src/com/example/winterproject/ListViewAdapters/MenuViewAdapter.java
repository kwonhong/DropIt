package com.example.winterproject.ListViewAdapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.winterproject.R;
import com.example.winterproject.Classes.Menu;

public class MenuViewAdapter extends BaseAdapter {
	private Context mContext;
	private List<Menu> mList;
	
	public MenuViewAdapter(Context context, List<Menu> list) {
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
			convertView = inflater.inflate(R.layout.menu_list_item, null);
		}
		//
		convertView.setMinimumHeight(100);

		Menu menu = mList.get(position);
		
		ImageView imgTitle = (ImageView)convertView.findViewById(R.id.imageView1);
		TextView txtTitle = (TextView)convertView.findViewById(R.id.txtCurrentTitle);
		
		
		txtTitle.setText(menu.getMenu());
		return convertView;
	}
}
