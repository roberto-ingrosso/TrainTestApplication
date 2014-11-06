package it.robertoingrosso.application.fragment;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.R;
import it.robertoingrosso.application.activity.TrainMovementsActivity;
import it.robertoingrosso.application.adapter.StationDataAdapter;
import it.robertoingrosso.application.data.ArrayOfObjStationData;
import it.robertoingrosso.application.data.ObjStationData;
import it.robertoingrosso.application.net.HttpClient;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class StationFragment extends BaseFragment {

	public static final String ARG_STATION_CODE = "stationCode";

	Handler mNetHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.MSG_STATION_DATA_BY_CODE_WITH_MINS_PERFORMED:

				if (msg.obj.equals(mStationCode)) {
					// hide the loading wheel and show the backend data
					mLoadingWheel.setVisibility(View.GONE);
					ArrayOfObjStationData stationData = getDataModel()
							.getStationData(mStationCode);

					if (stationData.getList() != null
							&& !stationData.getList().isEmpty()) {
						mTrainsList.setVisibility(View.VISIBLE);
						mTrainsList.setAdapter(new StationDataAdapter(
								getActivity(), stationData.getList()));
					} else {
						// if the request was successfull but returned no
						// values, notify the user
						mInfoMessage.setVisibility(View.VISIBLE);
						mInfoMessage
								.setText(getString(R.string.no_departing_trains));
					}
				}

				break;
			case Constants.MSG_STATION_DATA_BY_CODE_WITH_MINS_FAILED:
				// something went wrong with the request
				mLoadingWheel.setVisibility(View.GONE);
				mInfoMessage.setVisibility(View.VISIBLE);
				mInfoMessage.setText(getString(R.string.error_message));
				break;
			}
		};

	};

	private String mStationCode;
	private int mMinutesSpan = Constants.DEFAULT_MINUTES_SPAN;

	private TextView mHeaderTitle;
	private View mLoadingWheel;
	private ListView mTrainsList;
	private TextView mInfoMessage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.fragment_station, container,
				false);

		mHeaderTitle = (TextView) layout
				.findViewById(R.id.station_header_title);
		mLoadingWheel = layout.findViewById(R.id.station_loading_wheel);
		mTrainsList = (ListView) layout.findViewById(R.id.station_list);
		mInfoMessage = (TextView) layout
				.findViewById(R.id.station_info_message);

		mStationCode = getArguments().getString(ARG_STATION_CODE);

		mHeaderTitle.setText(String.format(
				getString(R.string.station_header_title), mStationCode));

		mTrainsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				// when the user taps on a train, show its movements
				Intent intent = new Intent(getActivity(),
						TrainMovementsActivity.class);
				intent.putExtra(
						TrainMovementsActivity.EXTRA_TRAIN_CODE,
						((ObjStationData) adapterView.getAdapter().getItem(
								position)).getTraincode());
				startActivity(intent);

			}
		});

		// request the station data for the selected station
		HttpClient.getStationData(mStationCode, mMinutesSpan, mNetHandler);

		return layout;
	}

}
