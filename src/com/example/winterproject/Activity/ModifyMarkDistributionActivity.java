package com.example.winterproject.Activity;

import com.example.winterproject.R;
import com.example.winterproject.R.layout;
import com.example.winterproject.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ModifyMarkDistributionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_mark_distribution);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_mark_distribution, menu);
		return true;
	}

}
