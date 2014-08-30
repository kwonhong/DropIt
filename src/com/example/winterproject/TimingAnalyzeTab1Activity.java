package com.example.winterproject;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;

import Charts.PieChart;
import Charts.TimePieChart;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import com.example.winterproject.Classes.Course;
import com.example.winterproject.Classes.Time;


public class TimingAnalyzeTab1Activity extends Activity {

	MySQLiteHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timing_analyze_tab1);
		
		LinearLayout linear = (LinearLayout)findViewById(R.id.layout1);
		
		db = new MySQLiteHelper(getApplicationContext());
		List<Course> course = db.getAllCourses();
		List<String> list_courses = new ArrayList<String>();
		for (int i=0; i<course.size(); i++) {
			list_courses.add(course.get(i).getCourse());
		}
		
		double[] values = new double[list_courses.size()];
		String[] labels = new String[list_courses.size()];
		
		for (int i=0; i<list_courses.size(); i++) {
			List<Time> time = db.getAllTime(list_courses.get(i));
			int timesum =0;
			for (int j=0; j<time.size(); j++) {
				timesum += time.get(j).getTime();
			}
			
			values[i] = timesum;
			labels[i] = list_courses.get(i);
		}
		
		TimePieChart pie = new TimePieChart(values, labels);
		GraphicalView graph1 = pie.getGraphic(this);
		linear.addView(graph1);
		
		
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timing_analyze_tab1, menu);
		return true;
	}


	
}
