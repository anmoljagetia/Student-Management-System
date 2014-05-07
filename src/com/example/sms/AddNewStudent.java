package com.example.sms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewStudent extends Activity {
	String name;
	String id;
	String classId;
	EditText nameText;
	EditText idText;
	EditText classText;
	SQLiteDatabase db;
	DBHelper dbhelper;
	String teacher_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_student);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		teacher_id = (String) bundle.getString("teacher_id");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_student, menu);
		return true;
	}

	public void change(View view) {

		dbhelper = new DBHelper(getApplicationContext());
		db = dbhelper.getWritableDatabase();

		nameText = (EditText) findViewById(R.id.aName);
		idText = (EditText) findViewById(R.id.aRollNo);
		classText = (EditText) findViewById(R.id.aClassId);

		name = nameText.getText().toString();
		id = idText.getText().toString();
		classId = classText.getText().toString();
		if (name.length() < 2 || id.length() < 2 || classId.length() < 2) {
			Toast.makeText(this,
					"Enter valid Student Name or Roll No or Class ID",
					Toast.LENGTH_SHORT).show();
		} else {

			String query = "insert into student_table values ('" + id + "', '"
					+ name + "', '" + classId + "', 0) ;";

			Log.d("insert query", query);

			try {
				db.execSQL(query);

				/* default values for student_course */
				query = "insert into student_course values ('" + id
						+ "', '401') ;";
				db.execSQL(query);
				Log.d("insert query", query);
				query = "insert into student_course values ('" + id
						+ "', '402') ;";
				db.execSQL(query);
				query = "insert into student_course values ('" + id
						+ "', '403') ;";
				db.execSQL(query);
				query = "insert into student_course values ('" + id
						+ "', '404') ;";
				db.execSQL(query);
				query = "insert into student_course values ('" + id
						+ "', '405') ;";
				db.execSQL(query);

				/* default values for performance_table */

				query = "insert into performance_table values ('" + id
						+ "', '401', 0, 0, 0, 0, 0, '') ;";
				db.execSQL(query);
				Log.d("insert query", query);
				query = "insert into performance_table values ('" + id
						+ "', '402', 0, 0, 0, 0, 0, '') ;";
				db.execSQL(query);
				query = "insert into performance_table values ('" + id
						+ "', '403', 0, 0, 0, 0, 0, '') ;";
				db.execSQL(query);
				query = "insert into performance_table values ('" + id
						+ "', '404', 0, 0, 0, 0, 0, '') ;";
				db.execSQL(query);
				query = "insert into performance_table values ('" + id
						+ "', '405', 0, 0, 0, 0, 0, '') ;";
				db.execSQL(query);
				Intent intent = new Intent(this, SecondActivity.class);
				intent.putExtra("teacher_id", teacher_id);
				startActivity(intent);

			} catch (Exception e) {
				Toast.makeText(this, "Student with this ID exists",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

}
