package com.example.winterproject.Activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Time;
import com.example.winterproject.ListViewAdapters.DoorListViewAdapter;

public class DoorListActivity extends Activity {

	private static int TimeNum = 0;
	MySQLiteHelper db;
	List<Time> list;
	String course;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_door_list);
		
			//
			db = new MySQLiteHelper(getApplicationContext());
		
			//Getting extra from the previous activity
			Bundle extras = getIntent().getExtras();
			course = extras.getString("course");
				
			//TextView
			TextView txt1 = (TextView)findViewById(R.id.txtView1);
			txt1.setText(course);
			
			//ListView
			ListView listview = (ListView)findViewById(R.id.listView1);
			list = (List<Time>) db.getAllTime(course);
			DoorListViewAdapter adapter = new DoorListViewAdapter(this, list);
			listview.setAdapter(adapter);
			listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					
					Time time = list.get(position);
					String name = time.getName();
					Intent intent = new Intent(DoorListActivity.this, TimerClassActivity.class);
					intent.putExtra("Course", course);
					intent.putExtra("Name", name);
					startActivity(intent);
				}
				
			});
			
			//Button
			Button btnAdd = (Button)findViewById(R.id.button1);
			btnAdd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					TimeNum ++;
					String name = "Time" + TimeNum;
					Log.i("number", name);
					Time time1 = new Time();
					time1.setName(name);
					time1.setCourse(course);
					time1.setGoalTime(10);
					time1.setTime(10);
					time1.setProgress(10);
					db.createTime(time1);
					
				}
			});
			
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.door_list, menu);
		return true;
	}

}
