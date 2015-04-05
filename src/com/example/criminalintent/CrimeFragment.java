package com.example.criminalintent;

import java.util.Date;
import java.util.UUID;
import java.util.zip.Inflater;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;

public class CrimeFragment extends Fragment {

	public static final String EXTRA_CRIME_ID = "crimeID";
	private static final int REQUEST_DATE = 0;
	private Crime mCrime;
	private EditText mTextField;
	private Button mDateButton;
	private CheckBox mSolvedCheckBox;
	private static final String DIALOG_DATE = "date";
	private ImageButton mPhotoButton;

	private View mProgressContainer ;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//mCrime = new Crime();
		/*	UUID crimeId = (UUID)getActivity().getIntent()
			.getSerializableExtra(EXTRA_CRIME_ID);
		 */
		UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

	}


	public static CrimeFragment newInstance(UUID crimeId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_CRIME_ID, crimeId);
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(args);
		return fragment;
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) return;
		if (requestCode == REQUEST_DATE) {
			Date date = (Date)data
					.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			mDateButton.setText(mCrime.getDate().toString());
		}
	}




	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_crime, parent, false);

		mTextField = (EditText) v.findViewById(R.id.crime_title);
		mTextField.setText(mCrime.getTitle());
		mTextField.addTextChangedListener(textWatcherForField);

		mDateButton = (Button)v.findViewById(R.id.crime_date);
		mDateButton.setText(mCrime.getDate().toString());
		//mDateButton.setEnabled(false);
		mDateButton.setOnClickListener(mDateButtonListener);
		mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
		mSolvedCheckBox.setChecked(mCrime.isSolved());
		mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// Set the crime's solved property
				mCrime.setSolved(isChecked);
			}
		});

		mPhotoButton = (ImageButton)v.findViewById(R.id.crime_imageButton);   
		mPhotoButton.setOnClickListener(new View.OnClickListener() 
		{

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), CrimeCameraActivity.class);   
				startActivity(i);

			}               
		});              

		// If camera is not available, disable camera functionality        
		PackageManager pm = getActivity().getPackageManager();       
		boolean hasACamera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA) 
				||   pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT) ||   
				(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD 
				&&                Camera.getNumberOfCameras() > 0);     
		if (!hasACamera) {           
			mPhotoButton.setEnabled(false);      
		}


		return v;
	}

	private View.OnClickListener mDateButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			FragmentManager fm = getActivity()
					.getSupportFragmentManager();
			DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
			dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
			dialog.show(fm, DIALOG_DATE);	
		}
	};

	private OnCheckedChangeListener mSolvedCheckBoxListener  = new OnCheckedChangeListener() 
	{
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// Set the crime's solved property
			mCrime.setSolved(isChecked);
		}

	};

	private TextWatcher textWatcherForField = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(
				CharSequence c, int start, int before, int count) {
			mCrime.setTitle(c.toString());
		}
	};


}
