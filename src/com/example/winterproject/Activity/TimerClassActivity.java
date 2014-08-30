package com.example.winterproject.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Time;

public class TimerClassActivity extends Activity {

	//Stop and Start Buttons
	private Button startButton;
    private Button pauseButton;
    
    //Displaying Time
    private TextView timeDisplaytxt;
    private long startTime = 0;
    private Handler customHandler = new Handler();
    
    //Time Parameters
    long timeInMilliseconds = 0L;
	long timePaseed = 0L;
	long updatedTime = 0L;

	
	MySQLiteHelper db;
	Time time;
	TextView txtView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timer_class);
		
		//getting buttons and textviews
		timeDisplaytxt = (TextView) findViewById(R.id.textView2);
		startButton = (Button) findViewById(R.id.button1);
		pauseButton = (Button) findViewById(R.id.button2);
		
		//setting listener for buttons
		startButton.setOnClickListener(startButtonListener);
		pauseButton.setOnClickListener(pauseButtonListener);
		
		//Getting extra from the previous activity
		Bundle extras = getIntent().getExtras();
		String course = extras.getString("Course");
		String name = extras.getString("Name");
		
		//Database
		db = new MySQLiteHelper(getApplicationContext());
		time = db.getTime(course, name);
		
		//TextView 1
		TextView txtView1 = (TextView)findViewById(R.id.textView1);
		txtView1.setText(time.getName());
		
		//TextView 3
		txtView = (TextView)findViewById(R.id.textView3);
		String time2 = "Time you have spent on this assignment = " + time.getTime() + "milliseconds";
		txtView.setText(time2);
		
	}
	
	//Button Listeners
	//Start Button
	OnClickListener startButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startTime = SystemClock.uptimeMillis();
			customHandler.postDelayed(updateTimerThread, 0);
			
		}
	};
	
	//Pause Button
	OnClickListener pauseButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int timeelapsed = (int) (timeInMilliseconds + time.getTime());
			time.setTime(timeelapsed);
			
			String time2 = "Time you have spent on this assignment = " + time.getTime() + "milliseconds";
			txtView.setText(time2);
			db.updateTime(time);
			//timePaseed += timeInMilliseconds;
			customHandler.removeCallbacks(updateTimerThread);
		}
	};
	

	
	
	
	private Runnable updateTimerThread = new Runnable() {

		public void run() {

			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

			//updatedTime = timePaseed + timeInMilliseconds;
			updatedTime = timeInMilliseconds;
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			int hour = (int) (mins/60);
			secs = secs % 60;
			int milliseconds = (int) (updatedTime % 1000);
			timeDisplaytxt.setText(""+ hour+ ":" + mins + ":"
					+ String.format("%02d", secs) + ":"
					+ String.format("%03d", milliseconds));
			customHandler.postDelayed(this, 0);
		}

	};

	 

}
