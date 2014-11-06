package it.robertoingrosso.application.adapter;

import it.robertoingrosso.application.data.ObjTrainMovements;
import it.robertoingrosso.application.fragment.TrainMovementFragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TrainMovementsPagerAdapter extends FragmentStatePagerAdapter {

	private List<ObjTrainMovements> mData;

	public TrainMovementsPagerAdapter(FragmentManager fm,
			List<ObjTrainMovements> mData) {
		super(fm);
		this.mData = mData;
	}

	@Override
	public Fragment getItem(int i) {
		// instantiate the fragment for the single page
		Fragment fragment = new TrainMovementFragment();
		Bundle args = new Bundle();
		args.putString(TrainMovementFragment.ARG_TRAIN_CODE, mData.get(i)
				.getTrainCode());
		args.putInt(TrainMovementFragment.ARG_MOVEMENT_INDEX, i);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// set the location full name as page title
		return mData.get(position).getLocationFullName();
	}

}
