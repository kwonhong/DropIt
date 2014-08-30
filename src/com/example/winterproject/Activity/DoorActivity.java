package com.example.winterproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.winterproject.R;
import com.example.winterproject.R.id;
import com.example.winterproject.R.layout;
import com.example.winterproject.R.menu;

public class DoorActivity extends Activity {

	//Images
	ImageView img1;
	ImageView img2;
	ImageView img3;
	ImageView img4;
	ImageView img5;
	
	//TextView
	TextView txt1;
	TextView txt2;
	TextView txt3;
	TextView txt4;
	TextView txt5;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_door);
		
		//Finding ImageViews
		 img1 = (ImageView)findViewById(R.id.imageView1);
		 img2 = (ImageView)findViewById(R.id.imageView2);
		 img3 = (ImageView)findViewById(R.id.imageView3);
		 img4 = (ImageView)findViewById(R.id.imageView4);
		 img5 = (ImageView)findViewById(R.id.imageView5);
		 
		 //Finding TextViews;
		 txt1 = (TextView)findViewById(R.id.textView1);
		 txt2 = (TextView)findViewById(R.id.textView2);
		 txt3 = (TextView)findViewById(R.id.textView3);
		 txt4 = (TextView)findViewById(R.id.textView4);
		 txt5 = (TextView)findViewById(R.id.textView5);
		
		img1.setOnClickListener(listener);
		img2.setOnClickListener(listener);
		img3.setOnClickListener(listener);
		img4.setOnClickListener(listener);
		img5.setOnClickListener(listener);
		
		
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View view) {
			Intent intent = new Intent(DoorActivity.this, DoorListActivity.class);
			if (view == img1){
				intent.putExtra("course", txt1.getText().toString());
			}
			else if (view == img2) {
				intent.putExtra("course", txt2.getText().toString());
			}
			else if (view == img3) {
				intent.putExtra("course", txt3.getText().toString());
			}
			else if (view == img4) {
				intent.putExtra("course", txt4.getText().toString());
			}
			else if (view == img5) {
				intent.putExtra("course", txt5.getText().toString());
			}
			startActivity(intent);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.door, menu);
		return true;
	}

}
