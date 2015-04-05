package com.example.criminalintent;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	private static final String TAG = "CrimeIntent";
	private ArrayList<com.example.criminalintent.Crime> mCrimes ;
	private ArrayList<String> mTitle;
	private ArrayList<Employee> mEmployeeList = new ArrayList<Employee>();

	@Override
	public void onResume() {
		super.onResume();
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.crimes_title);
		mCrimes = CrimeLab.get(getActivity()).getCrimes();
		Employee e = new Employee("Ankit", 22);
		Employee e2 = new Employee("Ashish", 24);
		mEmployeeList.add(e);
		mEmployeeList.add(e2);
		CrimeAdapter adapter = new CrimeAdapter(mCrimes);
		setListAdapter(adapter);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		
		
	}

	public void onListItemClick(ListView l, View v, int position, long id){
		Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
		Log.d(TAG, c.getTitle() + " was clicked");
		/*	Intent i = new Intent(getActivity(), MainActivity.class);
		i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
		startActivity(i);*/
		Intent i = new Intent(getActivity(), CrimePagerActivity.class);
		i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
		startActivity(i);


	}

	private class CrimeAdapter extends ArrayAdapter<Crime> {
		public CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// If we weren't given a view, inflate one
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_crime, null);
			}
			// Configure the view for this Crime
			Crime c = getItem(position);
			TextView titleTextView =
					(TextView)convertView.findViewById(R.id.title);
			titleTextView.setText(c.getTitle());
			TextView dateTextView =
					(TextView)convertView.findViewById(R.id.date);
			dateTextView.setText(c.getDate().toString());
			CheckBox solvedCheckBox =
					(CheckBox)convertView.findViewById(R.id.solved);
			solvedCheckBox.setChecked(c.isSolved());
			return convertView;
		}

	}
}