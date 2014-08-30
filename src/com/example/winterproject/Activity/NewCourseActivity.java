package com.example.winterproject.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Course;
import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.ListViewAdapters.PercentageViewAdapter;

public class NewCourseActivity extends Activity {

	private Spinner spnr, spinner2;
	 Integer[] num1 = {
				100, 95, 90, 85, 77, 73, 67, 63, 60, 57, 53, 50
		};
	 
	 Integer[] num2 =  {
			 100, 95, 90, 85, 80, 75, 70, 65, 60, 55, 50, 45, 40, 35, 30, 25, 20, 15, 10, 5, 0
	 };
	 
	 private MySQLiteHelper db; 
	 private EditText edtTitle;
	 private EditText edtGoal;
	 private List<Percentage_Item> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_course);
		
		spnr = (Spinner)findViewById(R.id.spinner1);
		ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(
				this, android.R.layout.simple_spinner_item, num1);
		spnr.setAdapter(adapter1);
		spnr.setOnItemSelectedListener(
	            new AdapterView.OnItemSelectedListener() {
	                @Override
	                public void onItemSelected(AdapterView<?> arg0, View arg1,
	                        int arg2, long arg3) {
	                	int position = spnr.getSelectedItemPosition();
	                	Toast.makeText(getApplicationContext(),"You have selected "+num1[+position],Toast.LENGTH_LONG).show();
	                    // TODO Auto-generated method stub
	                }
	                @Override
	                public void onNothingSelected(AdapterView<?> arg0) {
	                    // TODO Auto-generated method stub
	                }
	            }
	        );
		
		// Distribution Item Listview
				Spinner spinner = new Spinner(this);
				ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(
						this, android.R.layout.simple_spinner_item, 4);
				spinner.setAdapter(adapter2);
				
				Percentage_Item p1 = new Percentage_Item("Final", spinner);
				Percentage_Item p2 = new Percentage_Item("Assignments", spinner);
				Percentage_Item p3 = new Percentage_Item("Midterm/Test", spinner);
				
				list = new ArrayList<Percentage_Item> ();
				list.add(p1);
				list.add(p2);
				list.add(p3);
				
				ListView listview1 = (ListView)findViewById(R.id.list4);
				PercentageViewAdapter adapter3 = new PercentageViewAdapter(this, list);
				listview1.setAdapter(adapter3);
				
				//Back Button
				Button btnBack = (Button)findViewById(R.id.btnBack);
				btnBack.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(NewCourseActivity.this, ListActivity.class);
						startActivity(intent);
					}
				});
				
				//SQL database section
				db = new MySQLiteHelper(getApplicationContext());
				
				edtTitle = (EditText)findViewById(R.id.editText1);
				edtGoal = (EditText)findViewById(R.id.edtGoal);
				
				
				//Add Button
				Button btnAdd = (Button)findViewById(R.id.btnAdd);
				btnAdd.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						String name = (edtTitle.getText()).toString();
						int goal = Integer.parseInt((edtGoal.getText().toString()));
						
						Course course1 = new Course(name, 9383);
						course1.setGoal(goal);
						course1.setId(db.createCourse(course1));
						
						for (int i = 0; i<list.size(); i++) {
							int temp = list.get(i).getSpinner().getSelectedItemPosition();
							list.get(i).setPercentage(num2[temp]);
							list.get(i).setId(db.createPercentage(name, list.get(i)));
						}
						
						Intent intent = new Intent(NewCourseActivity.this, ListActivity.class);
						startActivity(intent);
					
					}
				});
				
				//Button Add More Item
				Button btnAddItem = (Button)findViewById(R.id.btnAddItem);
				btnAddItem.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						LayoutInflater li = LayoutInflater.from(NewCourseActivity.this);
						View promptsView = li.inflate(R.layout.addmoreitem, null);
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								NewCourseActivity.this);
		 
						final EditText userInput = (EditText) promptsView
								.findViewById(R.id.editTextDialogUserInput);
						
						// set prompts.xml to alertdialog builder
						alertDialogBuilder.setView(promptsView);
						alertDialogBuilder.setCancelable(false);
						alertDialogBuilder.setPositiveButton("OK",
						  new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog,int id) {
							// get user input and set it to result
							// edit text
							Percentage_Item percentage = new Percentage_Item(userInput.getText().toString(), null);
							list.add(percentage);
						    }
						  });
						alertDialogBuilder.setNegativeButton("Cancel",
						  new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog,int id) {
							dialog.cancel();
						    }
						  });
						
						AlertDialog alertDialog = alertDialogBuilder.create();
						 
						// show it
						alertDialog.show();
						
					}
				});
			}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_course_activity1, menu);
		return true;
	}

}
