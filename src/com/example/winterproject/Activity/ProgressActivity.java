package com.example.winterproject.Activity;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.Classes.Work;
import com.example.winterproject.ListViewAdapters.ProgressListAdapter;

public class ProgressActivity extends Activity {

	MySQLiteHelper db; 
	List<Percentage_Item> allPercentages;
	String course1;
	HashMap<String, List<Work>> listDataChild;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
		Bundle extras = getIntent().getExtras();
		course1 = extras.getString("Course");
	
		db = new MySQLiteHelper(getApplicationContext());
		allPercentages = db.getAllPercentages(course1);
		
		listDataChild = new HashMap<String, List<Work>>();
		
		for (int i=0; i<allPercentages.size(); i++)  {
			String ItemName = allPercentages.get(i).getName();
			
			List<Work> allWorks = db.getAllWork(course1, ItemName);
			listDataChild.put(ItemName, allWorks);
		}	
			
		ExpandableListView listview1 = (ExpandableListView)findViewById(R.id.expandableListView1);
		ProgressListAdapter adapter = new ProgressListAdapter(this, allPercentages, listDataChild);
		listview1.setAdapter(adapter);
		listview1.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView arg0, View arg1, int position1,
					int position2, long arg4) {
				String Item = allPercentages.get(position1).getName();
				
				Intent intent = new Intent(ProgressActivity.this, ProgressMarkActivity.class);
				intent.putExtra("Course", course1);
				intent.putExtra("Item", Item);
				intent.putExtra("Work", listDataChild.get(Item).get(position2).getWork_name());
				startActivity(intent);
				
				return true;
			}
		});
		
		Button btnAddTask = (Button)findViewById(R.id.btnAddItem);
		btnAddTask.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				
				Intent intent = new Intent(ProgressActivity.this, NewWorkActivity.class);
				intent.putExtra("Course", course1);
				startActivity(intent);
			}
		});
		
		allPercentages = db.getAllPercentages();
		
	
		
	
	
	
	}

	

}
