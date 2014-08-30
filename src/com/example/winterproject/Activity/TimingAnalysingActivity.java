package com.example.winterproject.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.winterproject.R;
import com.example.winterproject.TimingAnalyzeTab1Activity;
import com.example.winterproject.TimingAnalyzeTab2Activity;

public class TimingAnalysingActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timing_analysing);
		
        TabHost tabHost = getTabHost();
         
        // Tab for MarkDistribution
        TabSpec tab1 = tabHost.newTabSpec("Daily");
        TabSpec tab2 = tabHost.newTabSpec("Total");

        tab1.setIndicator("Daily", getResources().getDrawable(R.drawable.ic_launcher));
        Intent tab1Intent = new Intent(this, TimingAnalyzeTab1Activity.class);
        tab1.setContent(tab1Intent);
        
        tab2.setIndicator("Total", getResources().getDrawable(R.drawable.ic_launcher));
        Intent tab2Intent = new Intent(this, TimingAnalyzeTab2Activity.class);
        tab2.setContent(tab2Intent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(tab1); // Adding photos tab
        tabHost.addTab(tab2);
      
		
	}

}
