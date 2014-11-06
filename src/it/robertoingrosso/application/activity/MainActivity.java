package it.robertoingrosso.application.activity;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.R;
import it.robertoingrosso.application.adapter.DrawerListAdapter;
import it.robertoingrosso.application.fragment.OngoingTrainsFragment;
import it.robertoingrosso.application.fragment.StationFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {

	private static final String INSTANCE_STATE_CURRENT_SECTION = "currentSection";

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	private int mCurrentSection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerList.setAdapter(new DrawerListAdapter(this));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// if the app has just started, show the first section and open the
		// navigation drawer to let the user know it exists
		// else go to the section the app was in
		if (savedInstanceState == null) {
			changeSection(0);
			mDrawerLayout.openDrawer(Gravity.LEFT);
		} else {
			changeSection(savedInstanceState
					.getInt(INSTANCE_STATE_CURRENT_SECTION));
		}

	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int position, long id) {

			changeSection(position);

		}

	}

	public void changeSection(int position) {
		mCurrentSection = position;

		// Create the fragment of the chosen section
		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (position) {
		case 0:
			fragment = new OngoingTrainsFragment();
			break;
		case 1:
			fragment = new StationFragment();
			// for Arklow and Shankill station we reuse the same fragment, so we
			// need to specify what station was selected
			args.putString(StationFragment.ARG_STATION_CODE,
					Constants.STATION_CODE_ARKLOW);
			fragment.setArguments(args);
			break;
		case 2:
			fragment = new StationFragment();
			args.putString(StationFragment.ARG_STATION_CODE,
					Constants.STATION_CODE_SHANKILL);
			fragment.setArguments(args);
			break;
		}

		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// Highlight the selected item and close the
		// drawer
		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// remember the last selected section
		outState.putInt(INSTANCE_STATE_CURRENT_SECTION, mCurrentSection);
		super.onSaveInstanceState(outState);
	}

}
