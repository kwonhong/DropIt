package com.example.winterproject.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.winterproject.FlyOutContainer;
import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.OnSwipeTouchListener;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Course;
import com.example.winterproject.Classes.Menu;
import com.example.winterproject.ListViewAdapters.ListView2Adapter;
import com.example.winterproject.ListViewAdapters.MenuViewAdapter;

public class ListActivity extends Activity {
	
	FlyOutContainer root;
	MySQLiteHelper db; 
	List<Menu> menus;
	List<Course> allCourses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.activity_sample, null);
        this.setContentView(root);
		
        Toast.makeText(this, "Swipe to the right to add new course", 1000).show();
		
        db = new MySQLiteHelper(getApplicationContext());
		ListView mListView = (ListView)findViewById(R.id.mainList);
		ListView mMenuList = (ListView)findViewById(R.id.list2);
		
		mListView.setOnTouchListener(new OnSwipeTouchListener() {
	        public void onSwipeTop() {
	          //  Toast.makeText(ListActivity.this, "top", Toast.LENGTH_SHORT).show();
	            
	        }
	        public void onSwipeRight() {
	            Toast.makeText(ListActivity.this, "Swipe to the left to close the menu", Toast.LENGTH_SHORT).show();
	            root.toggleMenu();
	        }
	        public void onSwipeLeft() {
	          //  Toast.makeText(ListActivity.this, "left", Toast.LENGTH_SHORT).show();
	            
	        }
	        public void onSwipeBottom() {
	          //  Toast.makeText(ListActivity.this, "bottom", Toast.LENGTH_SHORT).show();
	           
	        }
	    });
		
		mMenuList.setOnTouchListener(new OnSwipeTouchListener() {
	        public void onSwipeTop() {
	           // Toast.makeText(ListActivity.this, "top", Toast.LENGTH_SHORT).show();
	            
	        }
	        public void onSwipeRight() {
	          //  Toast.makeText(ListActivity.this, "right", Toast.LENGTH_SHORT).show();
	           
	        }
	        public void onSwipeLeft() {
	          //  Toast.makeText(ListActivity.this, "left", Toast.LENGTH_SHORT).show();
	            root.toggleMenu();
	        }
	        public void onSwipeBottom() {
	          //  Toast.makeText(ListActivity.this, "bottom", Toast.LENGTH_SHORT).show();
	           
	        }
	    });
 
        allCourses = db.getAllCourses();
        menus = new ArrayList<Menu> ();
        
        Menu menu1 = new Menu(39293, "Add New Course");
        Menu menu2 = new Menu(38293, "Delete Course");
        menus.add(menu1);
        menus.add(menu2);
     
        ListView2Adapter adapter = new ListView2Adapter(this, allCourses);
        MenuViewAdapter adapter2 = new MenuViewAdapter(this, menus);
        
        mMenuList.setOnItemClickListener(itemClickListener1);
        mListView.setOnItemClickListener(itemClickListener2);
        mListView.setOnItemLongClickListener(itemLongClickListener1);
        
		mListView.setAdapter(adapter);
        mMenuList.setAdapter(adapter2);
        // Don't forget to close database connection
        db.closeDB();	
	}
	
	OnItemClickListener itemClickListener1 = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View convertView, int position,
				long arg3) {
			if (menus.get(position).getMenu() == "Add New Course") {
				Intent intent = new Intent(ListActivity.this, NewCourseActivity.class);
				startActivity(intent);
			}
			
		}
		
	};
	
	OnItemClickListener itemClickListener2 = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Intent intent = new Intent(ListActivity.this, AnalyzingActivity.class);
			intent.putExtra("Course", allCourses.get(position).getCourse());
			startActivity(intent);
			
		}
	};
	
	OnItemLongClickListener itemLongClickListener1 = new OnItemLongClickListener() {

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	public void toggleMenu(View v) {
		this.root.toggleMenu();
	}
	

	

	
	
	
	

}
