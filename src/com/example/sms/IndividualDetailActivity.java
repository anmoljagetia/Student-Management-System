package com.example.sms;

import com.beardedhen.androidbootstrap.BootstrapButton;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

public class IndividualDetailActivity extends Activity {

	
	SQLiteDatabase db;
	DBHelper dbhelper;
	Cursor cursor;
	EditText tID;
	EditText tName;
	EditText tClassID;
	EditText tmidSem;
	EditText tendSem;
	EditText tassignment;
	EditText tproject;
	EditText tattendance;
	EditText tbehavior;
	EditText tsports;
	EditText dummy;
	ActionBar actionBar;
	String teacher_id = null;
	String i = null;
	BootstrapButton save;
	TableRow tableRow1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_detail);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		sender sd = (sender) bundle.getParcelable("sender");

		tableRow1 = (TableRow) findViewById(R.id.tableRow1);
		SharedPreferences getPrefs = PreferenceManager
				.getDefaultSharedPreferences(getBaseContext());
		boolean dName = getPrefs.getBoolean("checkbox", true);
		if (dName == true) {
			tableRow1.setVisibility(0);

		} else {
			tableRow1.setVisibility(100);
		}

		dbhelper = new DBHelper(getApplicationContext());
		db = dbhelper.getReadableDatabase();
		String input = sd.sendList.get(0);
		teacher_id = sd.sendList.get(1);

		dummy = (EditText) findViewById(R.id.dummy);
		tID = (EditText) findViewById(R.id.ResultID);
		tName = (EditText) findViewById(R.id.ResultName);
		tClassID = (EditText) findViewById(R.id.ResultClassID);
		tmidSem = (EditText) findViewById(R.id.ResultMidSem);
		tendSem = (EditText) findViewById(R.id.ResultEndSem);
		tassignment = (EditText) findViewById(R.id.ResultAssignment);
		tproject = (EditText) findViewById(R.id.ResultProject);
		tattendance = (EditText) findViewById(R.id.ResultAttendance);
		tbehavior = (EditText) findViewById(R.id.ResultBehaviour);
		tsports = (EditText) findViewById(R.id.ResultSports);
		save = (BootstrapButton) findViewById(R.id.save);

		try {
			String query = "select distinct s.student_id, "
					+ "s.student_name, " + "s.class_id, " + "p.mid_sem, "
					+ "p.end_sem, " + "p.assignment, " + "p.project, "
					+ "p.attendance, " + "p.behavior, " + "s.sports "
					+ "from student_table s " + "join performance_table p "
					+ "on s.student_id = p.student_id "
					+ "where s.student_id = '" + input
					+ "' and p.course_id  = (select course_id "
					+ "from teacher_course " + "where teacher_id = '"
					+ teacher_id + "');";

			Log.wtf("query", query);

			Cursor cursor = db.rawQuery(query, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						String ID1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(0)));
						String Name1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(1)));
						String ClassID1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(2)));
						String midSem1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(3)));
						String endSem1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(4)));
						String assignment1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(5)));
						String project1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(6)));
						String attendance1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(7)));
						String behavior1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(8)));
						String sports1 = cursor.getString(cursor
								.getColumnIndex(cursor.getColumnName(9)));

						Log.wtf("Works!!", ID1 + " " + Name1 + " " + ClassID1);

						tID.setText(ID1);
						i = ID1;
						tName.setText(Name1);
						tClassID.setText(ClassID1);
						tmidSem.setText(midSem1);
						tendSem.setText(endSem1);
						tassignment.setText(assignment1);
						tproject.setText(project1);
						tattendance.setText(attendance1);
						tbehavior.setText(behavior1);
						tsports.setText(sports1);

						/*
						 * tID.setFocusable(false); tName.setFocusable(false);
						 * tClassID.setFocusable(false);
						 * tmidSem.setFocusable(false);
						 * tendSem.setFocusable(false);
						 * tassignment.setFocusable(false);
						 * tproject.setFocusable(false);
						 * tattendance.setFocusable(false);
						 * tbehavior.setFocusable(false);
						 * tsports.setFocusable(false);
						 */

						tID.setEnabled(false);
						tName.setEnabled(false);
						tClassID.setEnabled(false);
						tmidSem.setEnabled(false);
						tendSem.setEnabled(false);
						tassignment.setEnabled(false);
						tproject.setEnabled(false);
						tattendance.setEnabled(false);
						tbehavior.setEnabled(false);
						tsports.setEnabled(false);

					} while (cursor.moveToNext());
				}
			} else {
				Log.d("Cursor", "Null");
			}
		} catch (SQLiteException se) {
			Log.e("Shit!", "Could not create or Open the database");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.individual_detail, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.editButton:
			Log.d("Menu", "Edit Button Clicked");

			tmidSem.setEnabled(true);
			tendSem.setEnabled(true);
			tassignment.setEnabled(true);
			tproject.setEnabled(true);
			tattendance.setEnabled(true);
			tbehavior.setEnabled(true);
			tsports.setEnabled(true);
			save.setBootstrapButtonEnabled(true);

			// make all fields editable
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	/** Called when the user clicks the Save button */
	public void saveStuff(View view) {
		// Do something in response to button
		String mid = tmidSem.getText().toString();
		String end = tendSem.getText().toString();
		String assign = tassignment.getText().toString();
		String pro = tproject.getText().toString();
		String atten = tattendance.getText().toString();
		String beh = tbehavior.getText().toString();
		String sport = tsports.getText().toString();

		dbhelper = new DBHelper(getApplicationContext());
		db = dbhelper.getWritableDatabase();

		try {

			db.execSQL("update performance_table "
					+ "set mid_sem = '"
					+ mid
					+ "', end_sem = '"
					+ end
					+ "', assignment = '"
					+ assign
					+ "', project = '"
					+ pro
					+ "', attendance = '"
					+ atten
					+ "', behavior = '"
					+ beh
					+ "' where student_id = '"
					+ i
					+ "' and course_id = (select course_id from teacher_course where teacher_id = '"
					+ teacher_id + "');");

			db.execSQL("update student_table " + "set sports = '" + sport
					+ "' " + "where student_id = '" + i + "' ;");

			Log.wtf("Behavior", beh);

			Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
			dbhelper.close();
			db.close();
			finish();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
