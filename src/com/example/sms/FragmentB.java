package com.example.sms;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class FragmentB extends Fragment {

	SQLiteDatabase db;
	DBHelper dbhelper;
	RadioGroup rgB;
	RadioButton rbB;
	int flagB = -1;
	EditText sb2;
	String teacher_id = null;

	public FragmentB() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		teacher_id = getActivity().getIntent().getExtras()
				.getString("teacher_id");
		View v = inflater.inflate(R.layout.fragment_b, container, false);
		rgB = (RadioGroup) v.findViewById(R.id.radioGroup2);
		sb2 = (EditText) v.findViewById(R.id.t2);
		rgB.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int selected) {
				// TODO Auto-generated method stub
				switch (selected) {
				case R.id.b0:
					flagB = 0;
					break;

				case R.id.b1:
					flagB = 1;
					break;
				default:

				}

			}
		});

		com.beardedhen.androidbootstrap.BootstrapButton b = (com.beardedhen.androidbootstrap.BootstrapButton) v
				.findViewById(R.id.bootstrapButton2);
		b.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				sect s = new sect();

				if (flagB == 0) {
					s.se.add("Course");
					Log.wtf("Group", "1st selected");
					String string = (String) sb2.getText().toString();
					dbhelper = new DBHelper(getActivity()
							.getApplicationContext());
					db = dbhelper.getReadableDatabase();

					try {
						String query = "select distinct course_id from course_table " 
										+ "where course_id like '%" + string + "%' ;";

						Log.d("query", query);

						Cursor cursor = db.rawQuery(query, null);

						if (cursor != null) {
							if (cursor.moveToFirst()) {
								do {
									String sec = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(0)));
									Log.wtf("List of courses", sec);
									s.se.add(sec);
								} while (cursor.moveToNext());
							}
						}

						s.se.add(teacher_id);
					} catch (SQLiteException se) {
						Log.e("Shit!", "Could not create or Open the database");
					}

					Intent intent = new Intent(getActivity(),
							GroupActivity.class);
					intent.putExtra("sect", s);
					startActivity(intent);

				}

				else if (flagB == 1) {
					s.se.add("Class");
					Log.wtf("Group", "2nd selected");
					String string = (String) sb2.getText().toString();
					dbhelper = new DBHelper(getActivity()
							.getApplicationContext());
					db = dbhelper.getReadableDatabase();

					try {
						String query = "select distinct class_id "
								+ "from student_table s "
								+ "join student_course c "
								+ "on s.student_id = c.student_id "
								+ "where c.course_id = (select course_id " 
														+ "from teacher_course "
														+ "where teacher_id = '" + teacher_id + "') " 
								+ "and s.class_id like '%" + string + "%'; ";

						Log.d("query", query);

						Cursor cursor = db.rawQuery(query, null);

						if (cursor != null) {
							if (cursor.moveToFirst()) {
								do {
									String sec = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(0)));
									Log.wtf("List of sections", sec);
									s.se.add(sec);
								} while (cursor.moveToNext());
							}
							s.se.add(teacher_id);
							
						}
					} catch (SQLiteException se) {
						Log.e("Shit!", "Could not create or Open the database");
					}

					Intent intent = new Intent(getActivity(),
							GroupActivity.class);
					intent.putExtra("sect", s);
					startActivity(intent);

				} else {
					Log.wtf("Group", "I don't know what I am doing!");
					Toast.makeText(getActivity(), "Select atleast one option",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		// Inflate the layout for this fragment
		return v;
	}

}

class sect implements Parcelable {
	ArrayList<String> se;

	public ArrayList<String> getStrings() {
		return se;
	}

	public void setStrings(ArrayList<String> se) {
		this.se = se;
	}

	public sect() {
		se = new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	public sect(Parcel in) {
		se = (ArrayList<String>) in.readSerializable();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeSerializable(se);
	}

	public static final Parcelable.Creator<sect> CREATOR = new Parcelable.Creator<sect>() {

		@Override
		public sect createFromParcel(Parcel in) {
			return new sect(in);
		}

		@Override
		public sect[] newArray(int size) {
			return new sect[size];
		}
	};
}
