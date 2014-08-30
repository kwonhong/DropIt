package com.example.winterproject.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.achartengine.GraphicalView;

import Charts.PieChart;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.winterproject.FlyOutContainer2;
import com.example.winterproject.MySQLiteHelper;
import com.example.winterproject.OnSwipeTouchListener;
import com.example.winterproject.R;
import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.ListViewAdapters.MarkDistributionAdapter;

public class MarkDistributionAnalysisActivity extends Activity {
	//float values[] = {700, 400, 100, 500, 600};
	MySQLiteHelper db; 
	List<Percentage_Item> allPercentages;
	List<Integer> items = new ArrayList<Integer> ();
	FlyOutContainer2 root;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 this.root = (FlyOutContainer2) this.getLayoutInflater().inflate(R.layout.activity_main, null);
         this.setContentView(this.root);
         
         Toast.makeText(this, "Swipe to the right to see the list of mark distribution", 1000).show();
		
	    LinearLayout linearlayout = (LinearLayout) findViewById(R.id.layout1);
		ListView listview1 = (ListView)findViewById(R.id.mainList);
		
		linearlayout.setOnTouchListener(new OnSwipeTouchListener() {
	        public void onSwipeTop() {
	           
	            
	        }
	        public void onSwipeRight() {
	        	 Toast.makeText(MarkDistributionAnalysisActivity.this, "Swipe to the left to close the list", 1000).show();
	            root.toggleMenu();
	        }
	        public void onSwipeLeft() {
	          //  Toast.makeText(MarkDistributionAnalysisActivity.this, "left", Toast.LENGTH_SHORT).show();
	        
	        }
	        public void onSwipeBottom() {
	          //  Toast.makeText(MarkDistributionAnalysisActivity.this, "bottom", Toast.LENGTH_SHORT).show();
	           
	        }
	    });
		
		listview1.setOnTouchListener(new OnSwipeTouchListener() {
	        public void onSwipeTop() {
	            Toast.makeText(MarkDistributionAnalysisActivity.this, "top", Toast.LENGTH_SHORT).show();
	            
	        }
	        public void onSwipeRight() {
	            Toast.makeText(MarkDistributionAnalysisActivity.this, "right", Toast.LENGTH_SHORT).show();
	            
	        }
	        public void onSwipeLeft() {
	            Toast.makeText(MarkDistributionAnalysisActivity.this, "left", Toast.LENGTH_SHORT).show();
	            root.toggleMenu();
	        }
	        public void onSwipeBottom() {
	            Toast.makeText(MarkDistributionAnalysisActivity.this, "bottom", Toast.LENGTH_SHORT).show();
	           
	        }
	    });
		
		//getting information from previous activity
		Bundle extras = getIntent().getExtras();
		String course = extras.getString("Course");
		
		db = new MySQLiteHelper(getApplicationContext());
		allPercentages = db.getAllPercentages(course);
		for (int i=0; i<allPercentages.size(); i++) {
			items.add(i,allPercentages.get(i).getPercentage());
		}

		MarkDistributionAdapter adapter = new MarkDistributionAdapter(this, allPercentages);
		listview1.setAdapter(adapter);
		
		PieChart pie = new PieChart(allPercentages);
		GraphicalView graph1 = pie.getGraphic(this);
		linearlayout.addView(graph1);
		
	}
	
	//calculate the degree that each data should occupy
	private float[] calculateData (List<Integer> data) {
		float total = 0;
		float values[] = new float[data.size()];
		
		//finding the total data
		for (int i =0; i<data.size(); i++) {
			total += data.get(i);
		}
		
		//finding the degree that each data should occupy
		for (int i =0; i< data.size(); i++) {
			values[i] =  360 * (data.get(i)/total) ;
			
		}
		
		return values;
	}
	
	public class MyGraphview extends View {
		private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		private float[] value_degree;
		private List<Percentage_Item> allPercentages;
		
		//getting the size of the window
		Display display = getWindowManager().getDefaultDisplay(); 
		float width = display.getWidth();  // deprecated
		float height = display.getHeight();  // deprecated
		
		//center of the pie chart
		float centerX = width/2;
		float centerY = height/4;
		
		//radius of the pie chart
		float radius = (centerY -50)/2;
		
		//setting the position of the pie chart
		RectF rectf = new RectF(centerX - centerY+50, 50, centerX + centerY-50, height/2 -50);
		RectF rectf1 = new RectF(centerX - centerY+40, 40, centerX + centerY-40, height/2 -40);
				
		float temp = 0;
		
		public MyGraphview(Context context, float[] values) {
			super(context);
			
			value_degree = new float[values.length];
			for (int i = 0; i < values.length; i++) {
				value_degree[i] = values[i];
			}
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Random r;
			
			paint.setColor(Color.WHITE);
			canvas.drawArc(rectf1, 0, 360, true, paint);
			//value_degree.length
			for (int i = 0; i< value_degree.length; i++ ) {
				if (i == 0) {
					r = new Random();
					int color = Color.argb(100, r.nextInt(256), r.nextInt(256),r.nextInt(256));  
					paint.setColor(color);
					canvas.drawArc(rectf, 0, value_degree[i], true, paint);
					
					double  x = Math.toRadians(value_degree[i]/2);
					float x1 = (float) (centerX + radius*  Math.cos(x));
					float y1 = (float) (centerY + radius*  Math.sin(x));
					float x2 = (float) (x1 + radius*  Math.cos(x));
					float y2 = (float) (y1 + radius* Math.sin(x));
					paint.setColor(Color.RED);
					canvas.drawLine(x1, y1, x2, y2,paint);
					
				}
				
				else {
					temp += value_degree[i-1];
					r = new Random();
					int color = Color.argb(100, r.nextInt(256), r.nextInt(256),r.nextInt(256));  
					paint.setColor(color);
					canvas.drawArc(rectf, temp, value_degree[i], true, paint);
					
					double  x = Math.toRadians(temp + (value_degree[i])/2);
					float x1 = (float) (centerX + radius*  Math.cos(x));
					float y1 = (float) (centerY + radius*  Math.sin(x));
					float x2 = (float) (x1 + radius*  Math.cos(x));
					float y2 = (float) (y1 + radius* Math.sin(x));
					paint.setColor(Color.RED);
					canvas.drawLine(x1, y1, x2, y2,paint);
				}
			}
		}
	}
	
	public void toggleMenu(View v) {
		this.root.toggleMenu();
	}
	
	
}





