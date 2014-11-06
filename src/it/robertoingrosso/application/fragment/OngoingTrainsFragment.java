package it.robertoingrosso.application.fragment;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.R;
import it.robertoingrosso.application.activity.TrainMovementsActivity;
import it.robertoingrosso.application.adapter.CurrentTrainsAdapter;
import it.robertoingrosso.application.data.ArrayOfObjTrainPositions;
import it.robertoingrosso.application.data.ObjTrainPositions;
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

public class OngoingTrainsFragment extends BaseFragment {

	Handler mNetHandler = new Handler() {

		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Constants.MSG_CURRENT_TRAINS_PERFORMED:
				// hide the loading wheel and show the backend data
				mLoadingWheel.setVisibility(View.GONE);
				ArrayOfObjTrainPositions trainsData = getDataModel()
						.getCurrentTrains();

				if (trainsData.getList() != null
						&& !trainsData.getList().isEmpty()) {
					mTrainsList.setVisibility(View.VISIBLE);
					mTrainsList.setAdapter(new CurrentTrainsAdapter(
							getActivity(), trainsData.getList()));
				} else {
					// if the request was successfull but returned no
					// values, notify the user
					mInfoMessage.setVisibility(View.VISIBLE);
					mInfoMessage.setText(getString(R.string.no_ongoing_trains));
				}
				break;
			case Constants.MSG_CURRENT_TRAINS_FAILED:
				// something went wrong with the request
				mLoadingWheel.setVisibility(View.GONE);
				mInfoMessage.setVisibility(View.VISIBLE);
				mInfoMessage.setText(getString(R.string.error_message));
				break;
			}
		};

	};

	private TextView mHeaderTitle;
	private View mLoadingWheel;
	private ListView mTrainsList;
	private TextView mInfoMessage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.fragment_ongoing_trains,
				container, false);

		mHeaderTitle = (TextView) layout
				.findViewById(R.id.ongoing_trains_header_title);
		mLoadingWheel = layout.findViewById(R.id.ongoing_trains_loading_wheel);
		mTrainsList = (ListView) layout.findViewById(R.id.ongoing_trains_list);
		mInfoMessage = (TextView) layout
				.findViewById(R.id.ongoing_trains_info_message);

		mHeaderTitle.setText(getString(R.string.ongoing_trains));

		mTrainsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				// when the user taps on a train, show its movements
				Intent intent = new Intent(getActivity(),
						TrainMovementsActivity.class);
				intent.putExtra(
						TrainMovementsActivity.EXTRA_TRAIN_CODE,
						((ObjTrainPositions) adapterView.getAdapter().getItem(
								position)).getTrainCode());
				startActivity(intent);

			}
		});

		// request the list of current trains
		HttpClient.getCurrentTrains(mNetHandler);

		return layout;
	}

}
