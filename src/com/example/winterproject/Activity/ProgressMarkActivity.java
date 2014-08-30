package com.example.winterproject.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Work;

public class ProgressMarkActivity extends Activity {

	MySQLiteHelper db; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_mark);
		
		db = new MySQLiteHelper(getApplicationContext());
		
		Bundle extras = getIntent().getExtras();
		String course = extras.getString("Course");
		String item = extras.getString("Item");
		String work = extras.getString("Work");
		
		Work work1 = db.getWork(work);
		String mark = "" + work1.getMark();
		String out_of = "" + work1.getOut_of();
		int worth = work1.getWorth();
		int mark1 = work1.getMark();
		int outof = work1.getOut_of();
		
		double lost = (1- (double)mark1/outof)*worth;
		String lost_string = "" + lost;
		
		TextView txtTitle = (TextView)findViewById(R.id.txtCurrentTitle);
		TextView txtMark = (TextView)findViewById(R.id.txtBar);
		TextView txtOutOf = (TextView)findViewById(R.id.textView3);
		TextView txtLost = (TextView)findViewById(R.id.textView4);
		
		txtTitle.setText(work);
		txtMark.setText(mark);
		txtOutOf.setText(out_of);
		txtLost.setText(lost_string);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.progress_mark, menu);
		return true;
	}

}
