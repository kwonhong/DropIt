package com.example.winterproject.ListViewAdapters;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.winterproject.R;
import com.example.winterproject.Classes.Percentage_Item;

public class MarkDistributionAdapter extends BaseAdapter {

	private Context mContext;
	private List<Percentage_Item> mList;
	
	public MarkDistributionAdapter(Context context, List<Percentage_Item> list) {
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
			convertView = inflater.inflate(R.layout.markdistribution_list_view, null);
		}
		
		convertView.setMinimumHeight(100);
		Percentage_Item item = mList.get(position);
		
		ImageView img = (ImageView) convertView.findViewById(R.id.imageView1);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtCurrentTitle);
        
       // img.setBackgroundColor(Color.rgb(255, 255, 255));
       // img.setPadding(5, 5, 5, 5);
        String temp = "   " + item.getName() + " = " + item.getPercentage() + "%";
		txtTitle.setText(temp);
		
		
		return convertView;
	}

}