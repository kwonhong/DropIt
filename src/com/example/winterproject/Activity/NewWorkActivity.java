package com.example.winterproject.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.Classes.Work;

public class NewWorkActivity extends Activity {

	private EditText edtName;
	private EditText edtMark;
	private EditText edtOutOf;
	private MySQLiteHelper db;
	private String coursee;
	private List<String> nameList;
	List<Percentage_Item> list;
	private int pos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_work);
		
		db = new MySQLiteHelper(getApplicationContext());
		
		Bundle extras = getIntent().getExtras();
		coursee = extras.getString("Course");
		
		list = db.getAllPercentages(coursee);
		nameList = new ArrayList<String> ();
		for (int i=0; i < list.size(); i++) {
			nameList.add ( list.get(i).getName());
		}
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, nameList);
		spinner.setAdapter(adapter1);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				pos = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnAdd = (Button)findViewById(R.id.btnAddItem);
		edtName = (EditText)findViewById(R.id.editText1);
		edtMark = (EditText)findViewById(R.id.editText2);
		edtOutOf = (EditText)findViewById(R.id.editText3);
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Work work = new Work();
				work.setWork_name(edtName.getText().toString());
				work.setMark(Integer.parseInt(edtMark.getText().toString()));
				work.setOut_of(Integer.parseInt(edtOutOf.getText().toString()));
				work.setCourse(coursee);
				work.setItem(nameList.get(pos));
				work.setWorth(list.get(pos).getPercentage());
				
				work.setId(db.createWork(work));
				
				Intent intent = new Intent(NewWorkActivity.this, AnalyzingActivity.class);
				intent.putExtra("Course", coursee);
				startActivity(intent);
			
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_work, menu);
		return true;
	}

}
