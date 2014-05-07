package com.example.sms;

import java.util.ArrayList;

import com.beardedhen.androidbootstrap.BootstrapButton;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FragmentA extends Fragment {

	SQLiteDatabase db;
	DBHelper dbhelper;
	ListView lv;
	RadioGroup rgA;
	RadioButton rbA;
	int flagA = -1;
	EditText sb;
	String teacher_id = null;
	String decide = null;
	public FragmentA() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		teacher_id = getActivity().getIntent().getExtras()
				.getString("teacher_id");
		View v = inflater.inflate(R.layout.fragment_a, container, false);
		rgA = (RadioGroup) v.findViewById(R.id.radioGroup1);
		sb = (EditText) v.findViewById(R.id.t1);

		rgA.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int selected) {
				// TODO Auto-generated method stub
				switch (selected) {
				case R.id.a0:
					flagA = 0;
					break;

				case R.id.a1:
					flagA = 1;
					break;
				default:

				}

			}
		});

		BootstrapButton b = (BootstrapButton) v
				.findViewById(R.id.bootstrapButton1);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ArrayList<String> IDList = new ArrayList<String>();
				ArrayList<String> nameList = new ArrayList<String>();
				ArrayList<String> classList = new ArrayList<String>();

				list lt = new list();

				if (flagA == 0) {
					Log.wtf("Individual", "1st selected");
					String name = (String) sb.getText().toString();
					dbhelper = new DBHelper(getActivity()
							.getApplicationContext());
					db = dbhelper.getReadableDatabase();
					try {
						String query = "select distinct s.student_id, "
								+ "s.student_name, " + "s.class_id "
								+ "from student_table s "
								+ "join student_course c "
								+ "where s.student_name like '%" + name
								+ "%' and c.course_id = (select course_id "
								+ "from teacher_course "
								+ "where teacher_id = '" + teacher_id + "') ;";

						Log.wtf("query", query);

						Cursor cursor = db.rawQuery(query, null);

						if (cursor != null) {
							if (cursor.moveToFirst()) {
								do {
									String ID = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(0)));
									String Name = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(1)));
									String ClassID = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(2)));
									Log.wtf("yipee", ID + " " + Name + " "
											+ ClassID);
									IDList.add(ID);
									nameList.add(Name);
									classList.add(ClassID);

								} while (cursor.moveToNext());
							}
						}
					} catch (SQLiteException se) {
						Log.e("Shit!", "Could not create or Open the database");
					}
				

					ArrayList<String> tId = new ArrayList<String>();
					ArrayList<String> gp = new ArrayList<String>();
					gp.add("Name");
					tId.add(teacher_id);
					Log.d("TeacherId", teacher_id);
					lt.totalList.add(IDList);
					lt.totalList.add(nameList);
					lt.totalList.add(classList);
					lt.totalList.add(tId);
					lt.totalList.add(gp);
					
					Intent intent = new Intent(getActivity(),
							IndividualActivity.class);
					intent.putExtra("list", lt);
					startActivity(intent);
				}

				else if (flagA == 1) {
					String rollNo = (String) sb.getText().toString();
					Log.wtf("Individual", "2nd selected");
					dbhelper = new DBHelper(getActivity()
							.getApplicationContext());
					db = dbhelper.getReadableDatabase();
					try {
						String query = "select distinct student_id, "
								+ "student_name, " + "class_id "
								+ "from student_table  "
								+ "where student_id like '%" + rollNo + "%' ;";

						Log.wtf("query", query);

						Cursor cursor = db.rawQuery(query, null);

						if (cursor != null) {
							if (cursor.moveToFirst()) {
								do {
									String ID = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(0)));
									String Name = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(1)));
									String ClassID = cursor.getString(cursor
											.getColumnIndex(cursor
													.getColumnName(2)));
									Log.wtf("yipee", ID + " " + Name + " "
											+ ClassID);
									IDList.add(ID);
									nameList.add(Name);
									classList.add(ClassID);

								} while (cursor.moveToNext());
							}
						}
					} catch (SQLiteException se) {
						Log.e("Shit!", "Could not create or Open the database");
					}
					/*
					 * total.add(IDlist); total.add(Namelist);
					 * total.add(Classlist); ArrayAdapter<String> adapter = new
					 * ArrayAdapter<String>(getActivity(),
					 * android.R.layout.simple_list_item_1, null);
					 * lv.setAdapter(adapter);
					 */

					ArrayList<String> tId = new ArrayList<String>();
					ArrayList<String> gp = new ArrayList<String>();
					gp.add("Roll No");
					tId.add(teacher_id);
					Log.d("TeacherId", teacher_id);
					lt.totalList.add(IDList);
					lt.totalList.add(nameList);
					lt.totalList.add(classList);
					lt.totalList.add(tId);
					lt.totalList.add(gp);
					Intent intent = new Intent(getActivity(),
							IndividualActivity.class);
					intent.putExtra("list", lt);
					startActivity(intent);
				} else {
					Log.wtf("Individual", "I don't know what I am doing!");
					Toast.makeText(getActivity(), "Select atleast one option",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		// Inflate the layout for this fragment
		return v;
	}
}

class list implements Parcelable {
	ArrayList<ArrayList<String>> totalList;

	public ArrayList<ArrayList<String>> getStrings() {
		return totalList;
	}

	public void setStrings(ArrayList<ArrayList<String>> totalList) {
		this.totalList = totalList;
	}

	public list() {
		totalList = new ArrayList<ArrayList<String>>();
	}

	@SuppressWarnings("unchecked")
	public list(Parcel in) {
		totalList = (ArrayList<ArrayList<String>>) in.readSerializable();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeSerializable(totalList);
	}

	public static final Parcelable.Creator<list> CREATOR = new Parcelable.Creator<list>() {

		@Override
		public list createFromParcel(Parcel in) {
			return new list(in);
		}

		@Override
		public list[] newArray(int size) {
			return new list[size];
		}
	};
}
