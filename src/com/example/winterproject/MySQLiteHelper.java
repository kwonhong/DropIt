package com.example.winterproject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.winterproject.Classes.Course;
import com.example.winterproject.Classes.Percentage_Item;
import com.example.winterproject.Classes.Time;
import com.example.winterproject.Classes.Work;
 
public class MySQLiteHelper extends SQLiteOpenHelper {
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "dropit.db";
 
    // Table Names
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_PERCENTAGE = "percentage";
    private static final String TABLE_WORK = "work";
    private static final String TABLE_DISTRIBUTION = "distribution";
    private static final String TABLE_TIME = "time";
 
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
 
    // COURSE Table
    private static final String KEY_COURSE = "course";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_GOAL = "goal";
    
    // Percentage Table
    private static final String KEY_ITEM = "item";
    private static final String KEY_PERCENTAGE = "percentage";
 
    //Work Table
    private static final String KEY_WORK_NAME = "work_name";
    private static final String KEY_MARK = "mark";
    private static final String KEY_OUT_OF = "outof";
    private static final String KEY_WORTH = "worth";
    
    //Time Table
    private static final String KEY_TIME = "time";
    private static final String KEY_GOAL_TIEM = "goal_time";
    private static final String KEY_PROGRESS = "progress";
    
    // Table Create Statements
    // Course table create statement
    private static final String CREATE_TABLE_COURSE = "CREATE TABLE " 
    		+ TABLE_COURSE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COURSE
    		+ " TEXT," + KEY_IMAGE + " INTEGER," + KEY_GOAL + " INTEGER," + KEY_CREATED_AT + " DATETIME" + ")";
    
    private static final String CREATE_TABLE_PERCENTAGE = "CREATE TABLE " 
    		+ TABLE_PERCENTAGE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COURSE
    		+ " TEXT," + KEY_ITEM + " TEXT," + KEY_PERCENTAGE + " INTEGER" +
    		KEY_CREATED_AT + " DATETIME" + ")";
    
    private static final String CREATE_TABLE_WORK = "CREATE TABLE " 
    		+ TABLE_WORK + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COURSE
    		+ " TEXT," + KEY_ITEM + " TEXT," + KEY_WORK_NAME + " TEXT," + KEY_MARK + " INTEGER," + 
    		KEY_OUT_OF + " INTEGER," + KEY_WORTH + " INTEGER" + ")";
    
    private static final String CREATE_TABLE_TIME = "CREATE TABLE " 
    		+ TABLE_TIME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COURSE
    		+ " TEXT,"  + KEY_WORK_NAME + " TEXT," + KEY_TIME + " INTEGER," + 
    		KEY_GOAL_TIEM + " INTEGER," + KEY_PROGRESS + " INTEGER" + ")";
    
 
    
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
    	db.execSQL(CREATE_TABLE_COURSE);
    	db.execSQL(CREATE_TABLE_PERCENTAGE);
    	db.execSQL(CREATE_TABLE_WORK);
    	db.execSQL(CREATE_TABLE_TIME);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERCENTAGE);
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK);
    	db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIME);
        // create new tables
        onCreate(db);
    }
 
    //creating course
    public long createCourse (Course course) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_COURSE, course.getCourse());
    	values.put(KEY_IMAGE, course.getImage_id());
    	values.put(KEY_CREATED_AT, getDateTime());
    	values.put(KEY_GOAL, course.getGoal());
    	
    	//insert row
    	long course_id = db.insert(TABLE_COURSE, null, values);
    	
    	return course_id;
    }
    
  //creating Percentage
    public long createPercentage (String course, Percentage_Item item) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
        values.put(KEY_COURSE,course);
        values.put(KEY_ITEM, item.getName());
        values.put(KEY_PERCENTAGE, item.getPercentage());
    	
    	//insert row
    	long item_id = db.insert(TABLE_PERCENTAGE, null, values);
    	
    	return item_id;
    }
    
    //creating Work
    public long createWork (Work work ) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_COURSE, work.getCourse());
    	values.put(KEY_ITEM, work.getItem());
    	values.put(KEY_WORK_NAME, work.getWork_name());
    	values.put(KEY_MARK, work.getMark());
    	values.put(KEY_OUT_OF, work.getOut_of());
    	values.put(KEY_WORTH, work.getWorth());
    	
    	long item_id = db.insert(TABLE_WORK, null, values);
    	return item_id;
    	
    }
    
    //creating Time
    public long createTime (Time time ) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_COURSE, time.getCourse());
    	values.put(KEY_WORK_NAME, time.getName());
    	values.put(KEY_TIME, time.getTime());
    	values.put(KEY_GOAL_TIEM, time.getGoalTime());
    	values.put(KEY_PROGRESS, time.getProgress());
    	
    	long item_id = db.insert(TABLE_TIME, null, values);
    	return item_id;
    	
    }
 
    // getting a single course
    public Course getCourse (String course) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE + " WHERE "
                + KEY_COURSE + " = " + "'" +course+"'";
 
        Cursor c = db.rawQuery(selectQuery, null);
 
        if (c != null)
            c.moveToFirst();
 
        Course course1 = new Course();
        course1.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        course1.setCourse(c.getString(c.getColumnIndex(KEY_COURSE)));
        course1.setImage_id(c.getInt(c.getColumnIndex(KEY_IMAGE)));
        course1.setGoal(c.getInt(c.getColumnIndex(KEY_GOAL)));
        return course1;
    }
    
    //getting a single time
    public Time getTime (String course, String name) {
        SQLiteDatabase db = this.getReadableDatabase();
 
 
        String selectQuery = "SELECT  * FROM " + TABLE_TIME + " WHERE " + KEY_COURSE + " = " + "'"+course + "'" + " AND "
       		 + KEY_WORK_NAME + " = " + "'"+name+"'";
        
        Cursor c = db.rawQuery(selectQuery, null);
 
        if (c != null)
            c.moveToFirst();
 
        Time time = new Time();
    	time.setId(c.getInt(c.getColumnIndex(KEY_ID)));
     	time.setCourse(c.getString(c.getColumnIndex(KEY_COURSE)));
        time.setName(c.getString(c.getColumnIndex(KEY_WORK_NAME)));
        time.setTime(c.getInt(c.getColumnIndex(KEY_TIME)));
        time.setGoalTime(c.getInt(c.getColumnIndex(KEY_GOAL_TIEM)));
        time.setProgress(c.getInt(c.getColumnIndex(KEY_PROGRESS)));
        // adding to courses list
        return time;
    }
    
    
    // getting a single Percentage
    public Percentage_Item getPercentage(long todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        String selectQuery = "SELECT  * FROM " + TABLE_PERCENTAGE + " WHERE "
                + KEY_ID + " = " + todo_id;
 
        Cursor c = db.rawQuery(selectQuery, null);
 
        if (c != null)
            c.moveToFirst();
 
        Percentage_Item item = new Percentage_Item();
    	item.setId(c.getInt(c.getColumnIndex(KEY_ID)));
    	item.setName(c.getString(c.getColumnIndex(KEY_ITEM)));
    	item.setPercentage(c.getInt(c.getColumnIndex(KEY_PERCENTAGE)));
        
        return item;
    }
    
    public Work getWork(String name) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	
    	String selectQuery = "SELECT  * FROM " + TABLE_WORK + " WHERE "
                + KEY_WORK_NAME + " = " + "'"+name+"'";
    	Cursor c = db.rawQuery(selectQuery, null);
    	
    	if (c != null)
    		c.moveToFirst();
    	
    	Work work = new Work();
     	work.setCourse(c.getString(c.getColumnIndex(KEY_COURSE)));
        work.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        work.setItem(c.getString(c.getColumnIndex(KEY_ITEM)));
        work.setWork_name(c.getString(c.getColumnIndex(KEY_WORK_NAME)));
        work.setMark(c.getInt(c.getColumnIndex(KEY_MARK)));
        work.setOut_of(c.getInt(c.getColumnIndex(KEY_OUT_OF)));
        work.setWorth(c.getInt(c.getColumnIndex(KEY_OUT_OF)));
        
        return work;
    }
    
    public List<Time> getAllTime(String course) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	List<Time> times = new ArrayList<Time>();
    	String selectQuery = "SELECT  * FROM " + TABLE_TIME + " WHERE "
                + KEY_COURSE + " = " + "'" +course+"'";
    	Cursor c = db.rawQuery(selectQuery, null);
        
        if (c.moveToFirst()) {
            do {
            	Time time = new Time();
            	time.setId(c.getInt(c.getColumnIndex(KEY_ID)));
             	time.setCourse(c.getString(c.getColumnIndex(KEY_COURSE)));
                time.setName(c.getString(c.getColumnIndex(KEY_WORK_NAME)));
                time.setTime(c.getInt(c.getColumnIndex(KEY_TIME)));
                time.setGoalTime(c.getInt(c.getColumnIndex(KEY_GOAL_TIEM)));
                time.setProgress(c.getInt(c.getColumnIndex(KEY_PROGRESS)));
                // adding to courses list
                times.add(time);
            } while (c.moveToNext());
        }
        
        return times;
    }
 
    //getting all courses
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<Course>();
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Course course = new Course();
                course.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                course.setCourse(c.getString(c.getColumnIndex(KEY_COURSE)));
                course.setImage_id(c.getInt(c.getColumnIndex(KEY_IMAGE)));
                course.setGoal(c.getInt(c.getColumnIndex(KEY_GOAL)));
 
                // adding to courses list
                courses.add(course);
            } while (c.moveToNext());
        }
 
        return courses;
    }
    
  //getting all percentages
    public List<Percentage_Item> getAllPercentages() {
        List<Percentage_Item> items = new ArrayList<Percentage_Item>();
        String selectQuery = "SELECT  * FROM " + TABLE_PERCENTAGE;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
            	Percentage_Item item = new Percentage_Item();
            	item.setId(c.getInt(c.getColumnIndex(KEY_ID)));
            	item.setName(c.getString(c.getColumnIndex(KEY_ITEM)));
            	item.setPercentage(c.getInt(c.getColumnIndex(KEY_PERCENTAGE)));
 
                // adding to courses list
                items.add(item);
            } while (c.moveToNext());
        }
 
        return items;
    }
    
    //getting all percentages with specific course name
    public List<Percentage_Item> getAllPercentages(String course) {
    	SQLiteDatabase db = this.getReadableDatabase();
    	List<Percentage_Item> items = new ArrayList<Percentage_Item>();
    	String selectQuery = "SELECT  * FROM " + TABLE_PERCENTAGE + " WHERE "
                + KEY_COURSE + " = " + "'"+course + "'";
 
        Cursor c = db.rawQuery(selectQuery, null);
    	 
    	if (c.moveToFirst()) {
             do {
             	Percentage_Item item = new Percentage_Item();
             	item.setId(c.getInt(c.getColumnIndex(KEY_ID)));
             	item.setName(c.getString(c.getColumnIndex(KEY_ITEM)));
             	item.setPercentage(c.getInt(c.getColumnIndex(KEY_PERCENTAGE)));
  
                 // adding to courses list
                 items.add(item);
             } while (c.moveToNext());
         }
  
         return items;
    }
    
    public List<Work> getAllWork(String course, String item) {
    	 List<Work> works = new ArrayList<Work>();
         String selectQuery = "SELECT  * FROM " + TABLE_WORK + " WHERE " + KEY_COURSE + " = " + "'"+course + "'" + " AND "
        		 + KEY_ITEM + " = " + "'"+item+"'";
  
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor c = db.rawQuery(selectQuery, null);
  
         // looping through all rows and adding to list
         if (c.moveToFirst()) {
             do {
            	 
             	Work work = new Work();
             	work.setCourse(c.getString(c.getColumnIndex(KEY_COURSE)));
                work.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                work.setItem(c.getString(c.getColumnIndex(KEY_ITEM)));
                work.setWork_name(c.getString(c.getColumnIndex(KEY_WORK_NAME)));
                work.setMark(c.getInt(c.getColumnIndex(KEY_MARK)));
                work.setOut_of(c.getInt(c.getColumnIndex(KEY_OUT_OF)));
                work.setWorth(c.getInt(c.getColumnIndex(KEY_OUT_OF)));
                
                 // adding to courses list
                 works.add(work);
             } while (c.moveToNext());
         }
         return works;
    	
    }
     //getting course count
    public int getCourseCount() {
        String countQuery = "SELECT  * FROM " + TABLE_COURSE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
 
        int count = cursor.getCount();
        cursor.close();
 
        // return count
        return count;
    }
    
    public int getPercentageCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PERCENTAGE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
 
        int count = cursor.getCount();
        cursor.close();
 
        // return count
        return count;
    }
 
    // updating course
    public int updateCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_COURSE, course.getCourse());
        values.put(KEY_IMAGE, course.getImage_id());
 
        // updating row
        return db.update(TABLE_COURSE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(course.getId()) });
        //need to find the id in order to update the correct one
    }
    
    //updating time
    public int updateTime(Time time) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	ContentValues values = new ContentValues();
    	values.put(KEY_COURSE, time.getCourse());
    	values.put(KEY_WORK_NAME, time.getName());
    	values.put(KEY_TIME, time.getTime());
    	values.put(KEY_GOAL_TIEM, time.getGoalTime());
    	values.put(KEY_PROGRESS, time.getProgress());
    	
    	return db.update(TABLE_TIME, values, KEY_ID + " = ?",
                new String[] { String.valueOf(time.getId()) });
    }
    
    public int updatePercentage(String course, Percentage_Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_COURSE,course);
        values.put(KEY_ITEM, item.getName());
        values.put(KEY_PERCENTAGE, item.getPercentage());
 
        // updating row
        return db.update(TABLE_PERCENTAGE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        //need to find the id in order to update the correct one
    }
 
    
    //delete 
    public void deleteCourse(long course_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, KEY_ID + " = ?",
                new String[] { String.valueOf(course_id) });
    }
    
    public void deletePercentage(long course_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERCENTAGE, KEY_ID + " = ?",
                new String[] { String.valueOf(course_id) });
    }
    
    public void deleteWork(long course_id) {
    	SQLiteDatabase db = this.getWritableDatabase();
    	 db.delete(TABLE_WORK, KEY_ID + " = ?",
                 new String[] { String.valueOf(course_id) });
    }
 
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
 
    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}