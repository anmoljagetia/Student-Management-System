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

public class IndividualActivity extends Activity implements OnItemClickListener {

	ListView lv ;
	SQLiteDatabase db;
	DBHelper dbhelper;
	Cursor cursor;
	String IDList[]; 
	String nameList[]; 
	String classList[]; 
	String teacherId = null;
	ActionBar actionBar;
	String individual;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual);	
		IDList = null;
		nameList = null;
		classList = null;
	
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		list lt = (list) bundle.getParcelable("list");
		
		IDList = new String [lt.totalList.get(0).size()];
		nameList = new String [lt.totalList.get(1).size()];
		classList = new String [lt.totalList.get(2).size()];
		individual = lt.totalList.get(4).toString();
		Log.wtf("Tag", individual);
		for (int i = 0; i < lt.totalList.get(0).size(); ++i) {
			IDList[i] = lt.totalList.get(0).get(i);
		}
		
		for (int i = 0; i < lt.totalList.get(1).size(); ++i) {
			nameList[i] = lt.totalList.get(1).get(i);
		}
		
		for (int i = 0; i < lt.totalList.get(2).size(); ++i) {
			classList[i] = lt.totalList.get(2).get(i);
		}
		

		teacherId = lt.totalList.get(3).get(0); 
		
		lv = (ListView) findViewById(R.id.iResult);
		
	//	Log.d("Teacher_id", lt.teacher_id);
		Log.d("ID", lt.totalList.get(0).toString());
		Log.d("Name", lt.totalList.get(1).toString());
		Log.d("Class", lt.totalList.get(2).toString());
		ArrayAdapter<String> adapter;
		if (individual.equals("[Name]")) {
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nameList);	
		}
		else {
			adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, IDList);
		}
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.individual, menu);
		return true;
	}

	

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int i, long l) {
		// TODO Auto-generated method stub
		TextView temp = (TextView) view;
		Toast.makeText(this, temp.getText(), Toast.LENGTH_SHORT).show();
		sender sd = new sender ();
		ArrayList <String> sendList = new ArrayList <String> ();
		sendList.add(IDList[i]);
		sendList.add(teacherId);
		sd.sendList = sendList;
		Intent intent = new Intent(this, IndividualDetailActivity.class);
		intent.putExtra("sender", sd);
		startActivity(intent);
		
	}
}

class sender implements Parcelable 
{
	ArrayList<String> sendList;
	
	public ArrayList <String> getStrings() {
	    return sendList;
	}

	public void setStrings(ArrayList <String> sendList) {
	    this.sendList = sendList;
	}

	public sender() {
		sendList = new ArrayList<String> ();
	}

	@SuppressWarnings("unchecked")
	public sender(Parcel in) {
	    sendList = (ArrayList <String>) in.readSerializable();
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeSerializable(sendList);
	}
	
	public static final Parcelable.Creator<sender> CREATOR = new Parcelable.Creator<sender>() {

	    @Override
	    public sender createFromParcel(Parcel in) {
	        return new sender(in);
	    }

	    @Override
	    public sender[] newArray(int size) {
	        return new sender[size];
	    }
	};
}

/*
class SingleRow {
	
	class VivzAdapter extends BaseAdapter
	{
		ArrayList<ArrayList<String>> totalList ;
		Context context;
		VivzAdapter (ArrayList<ArrayList<String>> a) {
			totalList = a;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return totalList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return totalList.get(arg0);
		}

		@Override
		public long getItemId(int i) {
			// TODO Auto-generated method stub
			return Integer.getInteger((totalList.get(0).get(i)));
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = (LayoutInflater);
			View row = inflater.inflate(R.layout.row_individual, viewGroup, false);
			TextView Name = (TextView) row.findViewById(R.id.Name);
			TextView RollNo = (TextView) row.findViewById(R.id.RollNo);
			TextView Section = (TextView) row.findViewById(R.id.Section);
			
			Name.setText(totalList.get(0).get(i));
			RollNo.setText(totalList.get(1).get(i));
			Section.setText(totalList.get(2).get(i));
			
			return null;
		}
	}	
}
*/
