package com.example.winterproject.Activity;


import java.util.HashMap;
import java.util.List;

import org.achartengine.GraphicalView;

import Charts.BarGraph;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.Classes.Work;

public class CurrentStatusActivity extends Activity {

	private MySQLiteHelper db; 
	private List<Percentage_Item> items;
	private HashMap<String, List<Work>> works;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();
	private ProgressBar progressBar;
	private int progress = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current_status);
		
		Bundle extras = getIntent().getExtras();
		String course = extras.getString("Course");
		
		
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
		
		//Progress Bar Part
		progressBar = (ProgressBar)findViewById(R.id.progressBar1);
		progressBar.setProgress(0);
		
		new Thread(new Runnable() {
			  public void run() {
				while (progressBar.getProgress() < progress) {
				
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
					  progressBar.incrementProgressBy(1);
				}

			  }
		}).start();
		
		BarGraph barGraph = new BarGraph(items, works);
		
		// TextView Under ProgressBar
		TextView txtBar = (TextView)findViewById(R.id.txtBar);
		String text = progress + "% LOST" + "\n\n";
		txtBar.setText(text);
		
		for (int i =0; i<items.size(); i++) {
			text = (i+1) + "." + items.get(i).getName() + " - " + losts[i] + "%\n";
			txtBar.append(text);
		}
		
		//TextView Title
		TextView txtTitle = (TextView)findViewById(R.id.txtCurrentTitle);
		txtTitle.setText(course);

		LinearLayout linearlayout = (LinearLayout)findViewById(R.id.layout2);
		GraphicalView graph1 = barGraph.getGraphic(this);
		linearlayout.addView(graph1);
		
		
	}
	
	

}
