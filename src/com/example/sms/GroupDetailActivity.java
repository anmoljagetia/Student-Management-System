package com.example.sms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class GroupDetailActivity extends Activity {
	details d;
	EditText msa;
	EditText esa;
	EditText aa;
	EditText ap;
	EditText aat;
	
	String teacher_id = null;
	String listValue = null;
	String group = null;
	String avgMid = null;
	String avgEnd = null;
	String avgProject = null;
	String avgAttendance = null;
	String avgAssignment = null;
	
	DBHelper dbhelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_detail);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		d = (details) bundle.getParcelable("details");
		dbhelper = new DBHelper(getApplicationContext());
		db = dbhelper.getReadableDatabase();
		
		listValue = d.detail.get(0);
		teacher_id = d.detail.get(1);
		group = d.detail.get(2);
		
	//	Log.d("teacher_id", teacher_id);
		msa = (EditText) findViewById(R.id.msa);
		esa = (EditText) findViewById(R.id.esa);
		aa = (EditText) findViewById(R.id.aa);
		ap = (EditText) findViewById(R.id.ap);
		aat = (EditText) findViewById(R.id.aat);
		
		String midQuery = null;
		
		if(group.equals("Course")) {
			midQuery = "select avg (mid_sem), avg(end_sem), avg(project), avg(attendance), avg(assignment) " + "from performance_table "
				+ "where course_id = '" + listValue + "' ;";
			Log.d("Query for midsem average", midQuery);
		} else if (group.equals("Class")) {
			midQuery = "select avg (mid_sem), avg(end_sem), avg(project), avg(attendance), avg(assignment) " + "from performance_table "
					+ "where student_id in (select s.student_id "
					+ "from student_table s " + "join student_course c "
					+ "on c.student_id = s.student_id "
					+ "where c.course_id = (select course_id "
					+ "from teacher_table " + "where teacher_id = '" + teacher_id
					+ "') " + " and s.class_id = '" + listValue + "') ;";
			Log.d("Query for midsem average", midQuery);
		}

		try {
			Cursor c = db.rawQuery(midQuery, null);
			if (c != null) {
				if (c.moveToFirst()) {
					avgMid = c.getString(c.getColumnIndex(c
							.getColumnName(0)));
					avgEnd = c.getString(c.getColumnIndex(c
							.getColumnName(1)));
					avgProject = c.getString(c.getColumnIndex(c
							.getColumnName(2)));
					avgAttendance = c.getString(c.getColumnIndex(c
							.getColumnName(3)));
					avgAssignment = c.getString(c.getColumnIndex(c
							.getColumnName(4)));
					
					Log.wtf("Works!!", avgMid + avgEnd + avgProject + avgAttendance + avgAssignment);
					msa.setText(avgMid);
					esa.setText(avgEnd);
					aa.setText(avgAssignment);
					ap.setText(avgProject);
					aat.setText(avgAttendance);
					
					msa.setEnabled(false);
					esa.setEnabled(false);
					aa.setEnabled(false);
					ap.setEnabled(false);
					aat.setEnabled(false);
					
				}
			} else {
				Log.d("Cursor", "Null");
			}
			c.close();
		} catch (SQLiteException se) {
			Log.e("Shit!", "Could not create or Open the database");
		}
		
		dbhelper.close();
		db.close();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group_detail, menu);
		return true;
	}

}
