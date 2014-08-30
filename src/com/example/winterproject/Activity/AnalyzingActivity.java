package com.example.winterproject.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.winterproject.R;

public class AnalyzingActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_analyzing);
		
	    //getting information from the previous activity
		Bundle extras = getIntent().getExtras();
		String course = extras.getString("Course");
		
        TabHost tabHost = getTabHost();
         
        // Tab for MarkDistribution
        TabSpec photospec = tabHost.newTabSpec("Mark Distribution");
        // setting Title and Icon for the Tab
        photospec.setIndicator("Mark Distribution", getResources().getDrawable(R.drawable.ic_launcher));
        Intent photosIntent = new Intent(this, MarkDistributionAnalysisActivity.class);
        photosIntent.putExtra("Course", course);
        photospec.setContent(photosIntent);
         
        // Tab for Current Status
        TabSpec songspec = tabHost.newTabSpec("Current Status");        
        songspec.setIndicator("Current Status", getResources().getDrawable(R.drawable.ic_launcher));
        Intent songsIntent = new Intent(this, CurrentStatusActivity.class);
        songsIntent.putExtra("Course", course);
        songspec.setContent(songsIntent);
         
        // Tab for Requirement
        TabSpec videospec = tabHost.newTabSpec("Requirement");
        videospec.setIndicator("Requirement", getResources().getDrawable(R.drawable.ic_launcher));
        Intent videosIntent = new Intent(this, RequirementActivity.class);
        videosIntent.putExtra("Course", course);
        videospec.setContent(videosIntent);
        
        //Tab for MyProgress
        TabSpec progressSpec = tabHost.newTabSpec("MyProgress");
        progressSpec.setIndicator("MyProgress", getResources().getDrawable(R.drawable.ic_launcher));
        Intent progressIntent = new Intent(this, ProgressActivity.class );
        progressIntent.putExtra("Course", course);
        progressSpec.setContent(progressIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(photospec); // Adding photos tab
        tabHost.addTab(songspec); // Adding songs tab
        tabHost.addTab(progressSpec);
        tabHost.addTab(videospec); // Adding videos tab
        
	}
	
	
}
