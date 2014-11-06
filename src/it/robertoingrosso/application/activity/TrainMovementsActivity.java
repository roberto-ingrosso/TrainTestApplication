package it.robertoingrosso.application.activity;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.R;
import it.robertoingrosso.application.adapter.TrainMovementsPagerAdapter;
import it.robertoingrosso.application.data.ArrayOfObjTrainMovements;
import it.robertoingrosso.application.data.DataModel;
import it.robertoingrosso.application.data.ObjTrainMovements;
import it.robertoingrosso.application.net.HttpClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

public class TrainMovementsActivity extends FragmentActivity {

	public static final String EXTRA_TRAIN_CODE = "trainCode";

	Handler mNetHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.MSG_TRAIN_MOVEMENTS_PERFORMED:
				if (msg.obj.equals(mTrainCode)) {
					// hide the loading wheel and show the backend data
					mLoadingWheel.setVisibility(View.GONE);
					ArrayOfObjTrainMovements trainMovementsData = DataModel
							.getInstance().getTrainMovements(mTrainCode);

					if (trainMovementsData.getList() != null
							&& !trainMovementsData.getList().isEmpty()) {
						populatePages(trainMovementsData.getList());
					} else {
						// if the request was successfull but returned no
						// values, notify the user
						mInfoMessage.setVisibility(View.VISIBLE);
						mInfoMessage
								.setText(getString(R.string.no_train_movements));
					}
				}
				break;
			case Constants.MSG_TRAIN_MOVEMENTS_FAILED:
				// something went wrong with the request
				mLoadingWheel.setVisibility(View.GONE);
				mInfoMessage.setVisibility(View.VISIBLE);
				mInfoMessage.setText(getString(R.string.error_message));
				break;
			}
		};

	};

	private String mTrainCode;

	private TextView mHeaderTitle;
	private View mLoadingWheel;
	private TextView mInfoMessage;
	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_train_movements);

		mHeaderTitle = (TextView) findViewById(R.id.train_movements_header_title);
		mViewPager = (ViewPager) findViewById(R.id.train_movements_pager);
		mLoadingWheel = findViewById(R.id.train_movements_loading_wheel);
		mInfoMessage = (TextView) findViewById(R.id.train_movements_info_message);

		mTrainCode = getIntent().getExtras().getString(EXTRA_TRAIN_CODE);

		// show the traincode of the train whose movements we are showing
		mHeaderTitle.setText(String.format(
				getString(R.string.train_movements_header_title), mTrainCode));

		// request the train movements
		Calendar today = Calendar.getInstance();
		HttpClient.getTrainMovements(mTrainCode,
				today.get(Calendar.DAY_OF_MONTH), today.get(Calendar.MONTH),
				today.get(Calendar.YEAR), mNetHandler);
	}

	protected void populatePages(List<ObjTrainMovements> trainMovements) {
		// determine the first movement with a scheduled departure time
		// subsequent to current time
		int firstPageIndex = 0;
		for (int i = 0; i < trainMovements.size(); i++) {
			if (trainMovements
					.get(i)
					.getScheduledDeparture()
					.compareTo(
							new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
									.format(new Date())) < 0) {
				firstPageIndex++;
			} else {
				break;
			}
		}

		// set the adapter with the pages of the train movements
		mViewPager.setVisibility(View.VISIBLE);
		mViewPager.setAdapter(new TrainMovementsPagerAdapter(
				getSupportFragmentManager(), trainMovements));
		mViewPager.setCurrentItem(firstPageIndex);
	}

}
