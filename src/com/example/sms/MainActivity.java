package com.example.sms;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	ActionBar actionBar;
	EditText tv1;
	EditText tv2;
	SQLiteDatabase db;
	DBHelper dbhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
		// Do something in response to button

		dbhelper = new DBHelper(getApplicationContext());
		db = dbhelper.getWritableDatabase();

		String dateQuery = "select date();";
		Cursor ct = db.rawQuery(dateQuery, null);
		Log.d("query", dateQuery);
		ct.moveToFirst();
		String date = ct.getString(ct.getColumnIndex(ct.getColumnName(0)));
		Log.d("present date", date);

		dateQuery = "select end_date, class_id from class_table where end_date < '"
				+ date + "';";
		Cursor ctr = db.rawQuery(dateQuery, null);

		if (ctr != null) {
			if (ctr.moveToFirst()) {
				do {
					date = ctr.getString(ctr.getColumnIndex(ctr
							.getColumnName(0)));
					String classId = ctr.getString(ctr.getColumnIndex(ctr
							.getColumnName(1)));
					Log.d("expired class", date);
					Log.d("class id ", ctr.getString(ctr.getColumnIndex(ctr
							.getColumnName(1))));

					String q = " select student_id " + "from student_table s "
							+ "where s.class_id = '" + classId + "' ;";
					Cursor del = db.rawQuery(q, null);

					if (del != null) {
						if (del.moveToFirst()) {

							do {
								String id = del.getString(ctr
										.getColumnIndex(ctr.getColumnName(0)));

								try {
									dateQuery = "DELETE FROM performance_table "
											+ "where student_id = '"
											+ id
											+ "' and course_id = '401';";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM performance_table "
											+ "where student_id = '"
											+ id
											+ "' and course_id = '402';";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM performance_table "
											+ "where student_id = '"
											+ id
											+ "' and course_id = '403';";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM performance_table "
											+ "where student_id = '"
											+ id
											+ "' and course_id = '404';";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM performance_table "
											+ "where student_id = '"
											+ id
											+ "' and course_id = '405';";
									db.execSQL(dateQuery);
								} catch (Exception e) {
									Log.d("error in deleting performance",
											dateQuery);
									e.printStackTrace();
								}
							} while (del.moveToNext());
						}
					}
					Log.d("sucessfully deleted performance", dateQuery);

					del.moveToFirst();

					if (del != null) {
						if (del.moveToFirst()) {

							do {
								String id = del.getString(ctr
										.getColumnIndex(ctr.getColumnName(0)));

								try {
									dateQuery = "DELETE FROM student_course "
											+ "where student_id = '" + id
											+ "' and course_id = '401'; ";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM student_course "
											+ "where student_id = '" + id
											+ "' and course_id = '402'; ";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM student_course "
											+ "where student_id = '" + id
											+ "' and course_id = '403'; ";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM student_course "
											+ "where student_id = '" + id
											+ "' and course_id = '404'; ";
									db.execSQL(dateQuery);
									dateQuery = "DELETE FROM student_course "
											+ "where student_id = '" + id
											+ "' and course_id = '405'; ";
									db.execSQL(dateQuery);
								} catch (Exception e) {
									Log.d("error in deleting studentCourse",
											dateQuery);
									e.printStackTrace();
								}
							} while (del.moveToNext());
						}
					}
					Log.d("sucessfully deleted Student Course", dateQuery);

					del.moveToFirst();

					if (del != null) {
						if (del.moveToFirst()) {

							do {
								String id = del.getString(ctr
										.getColumnIndex(ctr.getColumnName(0)));
								dateQuery = "DELETE FROM student_table "
										+ "where student_id = '" + id + "' ; ";
								try {
									db.execSQL(dateQuery);
								} catch (Exception e) {
									Log.d("Error in deleting student table",
											dateQuery);
									e.printStackTrace();
								}
							} while (del.moveToNext());
						}
					}

					Log.d("Sucessfully deleted Student Table", dateQuery);

					dateQuery = "DELETE FROM class_table c.class_id = '"
							+ classId + "'; ";

					try {
						db.execSQL(dateQuery);
					} catch (Exception e) {
						Log.d("error in deleting classtable", dateQuery);
						e.printStackTrace();
					}
					Log.d("sucessfully deleted classtable", dateQuery);
					Toast.makeText(this, "IT-1 deleted, Session Complete!", Toast.LENGTH_SHORT)
							.show();
				} while (ctr.moveToNext());
			}
		}

		tv2 = (EditText) findViewById(R.id.txtTwo);
		tv1 = (EditText) findViewById(R.id.txtOne);
		String password = (String) tv2.getText().toString();
		String teacher_id = (String) tv1.getText().toString();

		try {
			Cursor c = db.rawQuery(
					"select * from teacher_table where Teacher_id='"
							+ teacher_id + "' and password = '" + password
							+ "';", null);
			c.moveToFirst();
			if (password == null || password == "" || password.length() < 5) {
				Toast.makeText(this, "Please Enter Correct password",
						Toast.LENGTH_SHORT).show();
			}

			else if (c.getCount() > 0) {
				Intent intent = new Intent(this, SecondActivity.class);
				intent.putExtra("teacher_id", teacher_id);
				startActivity(intent);
				dbhelper.close();
				db.close();
				finish();
			} else {
				Toast.makeText(this, "Wrong Username or Password",
						Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
