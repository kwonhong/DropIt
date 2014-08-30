package com.example.winterproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.winterproject.R;
import com.example.winterproject.R.id;
import com.example.winterproject.R.layout;
import com.example.winterproject.R.menu;

public class SelectingActivity extends Activity {

	Button btn1 ;
	Button btn2 ;
	Button btn3 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selecting);
		
		//Finding Buttons from the layout
		btn1 = (Button)findViewById(R.id.button1);
		btn2 = (Button)findViewById(R.id.button2);
		btn3 = (Button)findViewById(R.id.button3);
		
		//Setting the listeners
		btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
	}
	
	//Going to different activity depending on which button pressed
	OnClickListener listener = new OnClickListener() {
		
		Intent intent;
		@Override
		public void onClick(View v) {
			if (v ==btn1) {
				intent = new Intent(SelectingActivity.this, DoorActivity.class);
			}
			else if (v == btn2) {
				intent = new Intent(SelectingActivity.this, ListActivity.class);
			}
			else if (v == btn3) {
				intent = new Intent(SelectingActivity.this, TimingAnalysingActivity.class);
			}
			startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selecting, menu);
		return true;
	}

}
