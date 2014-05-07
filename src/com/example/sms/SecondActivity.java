package com.example.sms;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class SecondActivity extends FragmentActivity implements TabListener {

	ActionBar actionBar;
	ViewPager viewPager;
	String teacher_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		teacher_id = (String) bundle.getString("teacher_id");
		
		//Set up the viewPager.
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		// Set up the action bar.
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
		ActionBar.Tab tab1 = actionBar.newTab();
		tab1.setText("Individual");
		tab1.setTabListener(this);
				
		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText("Group");
		tab2.setTabListener(this);
				
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			Log.d("Settings", "Change Password Clicked");
			//change password
			Intent intent = new Intent(this,
					ChangePassword.class);
			intent.putExtra("teacher_id", teacher_id);
			startActivity(intent);
			return true;
			
		case R.id.add_student :
			Log.d("Settings", "Add Student Clicked");
			//add a student
			Intent intent1 = new Intent(this,
					AddNewStudent.class);
			intent1.putExtra("teacher_id", teacher_id);
			startActivity(intent1);
			return true;
			
		case R.id.preferences :
			Intent i = new Intent(this,Prefs.class);
			startActivity(i);
		default:
			return super.onOptionsItemSelected(item);

		}
	}

	
	
}

class MyAdapter extends FragmentPagerAdapter
{
	public MyAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		if(arg0 == 0) {
			fragment = new FragmentA();
		}
		if(arg0 == 1) {
			fragment = new FragmentB();
		}
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}
}
