package com.example.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class CrimePagerActivity extends FragmentActivity {
	private ArrayList<Crime> mCrimes;
	private ViewPager mViewPager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		mCrimes = CrimeLab.get(this).getCrimes();
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		FragmentStatePagerAdapter tb = new FragmentStatePagerAdapter(fm);
		mViewPager.setAdapter(tb);
		UUID crimeId = (UUID)getIntent()
				.getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
		for (int i = 0; i < mCrimes.size(); i++) {
			if (mCrimes.get(i).getId().equals(crimeId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}

	}
	
	private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int pos) {
			Crime crime = mCrimes.get(pos);
			if (crime.getTitle() != null) {
			setTitle(crime.getTitle());
			}
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};




	public class FragmentStatePagerAdapter extends FragmentPagerAdapter{

		public FragmentStatePagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Crime crime = mCrimes.get(arg0);
			return CrimeFragment.newInstance(crime.getId());
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mCrimes.size();
		}
	}

}
