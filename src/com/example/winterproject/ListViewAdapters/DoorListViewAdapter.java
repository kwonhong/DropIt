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
import com.example.winterproject.Classes.Course;
import com.example.winterproject.Classes.Time;

public class DoorListViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<Time> mList;
	
	public DoorListViewAdapter(Context context, List<Time> list) {
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
			convertView = inflater.inflate(R.layout.layout_doorlist, null);
		}
		//
		convertView.setMinimumHeight(100);

		Time time = mList.get(position);
		
		ImageView imgTitle = (ImageView)convertView.findViewById(R.id.imgTitle);
		TextView txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
		//TextView txtSubTitle = (TextView)convertView.findViewById(R.id.txtSubTitle);
		//TextView txtPosition = (TextView)convertView.findViewById(R.id.txtPrice);
		
		txtTitle.setText(time.getName());
		return convertView;
	}

}