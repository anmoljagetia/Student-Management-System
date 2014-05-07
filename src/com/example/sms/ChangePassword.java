package com.example.sms;

import com.beardedhen.androidbootstrap.BootstrapButton;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends Activity {
	String teacher_id = null;
	EditText op;
	EditText np;
	EditText np1;
	String oldPassword = null;
	String newPassword = null;
	String newPassword1 = null;
	String old = null;
	SQLiteDatabase db;
	DBHelper dbhelper;
	BootstrapButton b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		
		
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		teacher_id = (String) bundle.getString("teacher_id");
		op = (EditText) findViewById(R.id.op);
		np = (EditText) findViewById(R.id.np);
		np1 = (EditText) findViewById(R.id.np1);
		b = (BootstrapButton) findViewById(R.id.change);
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				oldPassword = op.getText().toString();
				newPassword = np.getText().toString();
				newPassword1 = np1.getText().toString();

				Log.d("Old password", oldPassword);
				Log.d("New password", newPassword);
				Log.d("New password 1", newPassword1);

				dbhelper = new DBHelper(getApplicationContext());
				db = dbhelper.getWritableDatabase();

				try {
					Cursor c = db.rawQuery(
							"select password from teacher_table where teacher_id = '"
									+ teacher_id + "' ;", null);
					
					Log.d("teacher id", teacher_id);
					
					if (c != null) {
						c.moveToFirst();
						old = c.getString(c.getColumnIndex(c.getColumnName(0)));
						Log.d("old", old);
					}
					
					if (old.matches(oldPassword)) {
						if (newPassword.matches(newPassword1)) {
							if (newPassword.length() > 5) {
							String query = "update teacher_table set password = '"
									+ newPassword + "' where teacher_id = '" + teacher_id
									+ "' ;";
							Log.d("query", query);
							db.execSQL(query);
							Toast.makeText(getApplicationContext(), "Password successfully changed",
									Toast.LENGTH_SHORT).show();
							finish();
							}
							else {
								Toast.makeText(getApplicationContext(), "Password should be atleast 6 characters",
										Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(getApplicationContext(), "The new passwords don't match",
									Toast.LENGTH_SHORT).show();
						}

					} else {
						Toast.makeText(getApplicationContext(), "The old password is incorrect",
								Toast.LENGTH_SHORT).show();
					}
					dbhelper.close();
					db.close();
					

					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Log.d("Couldnt help", old);
					e.printStackTrace();
				}

				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_password, menu);
		return true;
	}

}
