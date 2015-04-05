package com.example.criminalintent;

import java.util.UUID;

import android.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends SingleFragmentActivity {

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return super.onOptionsItemSelected(item);
	}

	
	protected CrimeFragment createFragment() {
		// TODO Auto-generated method stub
		UUID crimeId = (UUID)getIntent()
				.getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
				return CrimeFragment.newInstance(crimeId);
	}
}
