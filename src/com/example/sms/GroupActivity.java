package com.example.sms;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupActivity extends Activity implements OnItemClickListener{

	ListView lv ;
	SQLiteDatabase db;
	DBHelper dbhelper;
	Cursor cursor;
	ActionBar actionBar;
	String teacher_id = null;
	String section[] = null;
	String course[] = null;
	String group = null;
	sect sec;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		sec = (sect) bundle.getParcelable("sect");
		group = sec.se.get(0);
		sec.se.remove(0);
		Log.d ("group", group);
		
		teacher_id = sec.se.get(sec.se.size() - 1); 
		
		if (group.equals("Course")) {
			Log.d("inside course", group);
			course = new String[sec.se.size() - 1];
			
			for (int i = 0; i < sec.se.size() - 1; ++ i) {
				course[i] = sec.se.get(i);
			}
			
			
			lv = (ListView) findViewById(R.id.iGroup);
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, course);
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(this);
		} else if (group.equals("Class")){
			Log.d("inside class", group);
			section = new String[sec.se.size() - 1];
			
			for (int i = 0; i < sec.se.size() - 1; ++ i) {
				section[i] = sec.se.get(i);
			}
			lv = (ListView) findViewById(R.id.iGroup);
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, section);
			lv.setAdapter(adapter);
			lv.setOnItemClickListener(this);
		}
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.group, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int i, long l) {
		// TODO Auto-generated method stub
		TextView temp = (TextView) view;
		Toast.makeText(this, temp.getText(), Toast.LENGTH_SHORT).show();
		details d = new details ();
		if (group.equals("Course")) {
			d.detail.add(course[i]);
			d.detail.add(teacher_id);
			d.detail.add("Course");
			Intent intent = new Intent(this, GroupDetailActivity.class);
			intent.putExtra("details", d);
			startActivity(intent);
		} else if (group.equals("Class")) {
			d.detail.add(section[i]);
			d.detail.add(teacher_id);
			d.detail.add("Class");
			Intent intent = new Intent(this, GroupDetailActivity.class);
			intent.putExtra("details", d);
			startActivity(intent);
		}
	}

}

class details implements Parcelable {
	ArrayList<String> detail;

	public ArrayList<String> getStrings() {
		return detail;
	}

	public void setStrings(ArrayList<String> detail) {
		this.detail = detail;
	}

	public details() {
		detail = new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	public details(Parcel in) {
		detail = (ArrayList<String>) in.readSerializable();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeSerializable(detail);
	}

	public static final Parcelable.Creator<details> CREATOR = new Parcelable.Creator<details>() {

		@Override
		public details createFromParcel(Parcel in) {
			return new details(in);
		}

		@Override
		public details[] newArray(int size) {
			return new details[size];
		}
	};
}

