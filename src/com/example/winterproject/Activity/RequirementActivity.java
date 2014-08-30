package com.example.winterproject.Activity;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Course;
import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.Classes.ProgressWheel;
import com.example.winterproject.Classes.Work;

public class RequirementActivity extends Activity {

	private MySQLiteHelper db; 
	private List<Percentage_Item> items;
	private HashMap<String, List<Work>> works;
	private int progress = 0;
	private ProgressBar progressBar;
	private ProgressWheel progressWheel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_requirement);
		
		Bundle extras = getIntent().getExtras();
		String course = extras.getString("Course");
		
		db = new MySQLiteHelper(getApplicationContext());
		items = db.getAllPercentages(course);
		
		works = new HashMap<String, List<Work>>();
		
		db = new MySQLiteHelper(getApplicationContext());
		items = db.getAllPercentages(course);
		for (int i = 0; i< items.size(); i++) {
			String item = items.get(i).getName();
			works.put(item, db.getAllWork(course, item));
		}
		
		//Calculating the lost
				double[] losts = new double[items.size()];
				for (int i =0; i<items.size(); i++) {
					
					double outof;
					double mark;
					double total_lost = 0;

					List<Work> worklist = works.get(items.get(i).getName());
					for (int j=0; j<worklist.size(); j++) {
						outof= worklist.get(j).getOut_of();
						mark = worklist.get(j).getMark();
						total_lost += mark/outof;
					}
					//adding all the percentages for each assignments and divide by the number of assignments
					if (worklist.size() != 0)
						total_lost = 1- total_lost/worklist.size(); 
					
					losts[i] =  total_lost* (items.get(i).getPercentage());
				}
				
				
				for (int i =0; i< losts.length; i++) {
					progress += losts[i];
				}
		
		
		//progress bar
		Course course1 = db.getCourse(course);
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		progressBar.setProgress(0);
		progressBar.setMax(100-course1.getGoal());
	
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while (progressBar.getProgress() < progress) {
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				progressBar.incrementProgressBy(1);
				}
			}
		}).start();
		
		//Progress Text Setup
		int remain = 100- course1.getGoal()-progress;
		TextView txtProgress = (TextView)findViewById(R.id.txtProgress);
		String temp = "Goal - " + course1.getGoal() + "%" + '\n' 
				+ "Lost - " + progress + "%" +'\n'
				+ remain + "% is remaining..";
		txtProgress.setText(temp);
		
		String[] listContent = {
				"Option1", "Option2", "Option3"
	 
	    };
		
		//ListView
		ListView listOption = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adapter
		 
		   = new ArrayAdapter<String>(this,
		 
		        android.R.layout.simple_list_item_1,
		 
		        listContent);
		
		listOption.setAdapter(adapter);
		
		//Layout
		
	} 
	

}
