package it.robertoingrosso.application.adapter;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.R;
import it.robertoingrosso.application.data.ObjTrainPositions;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class CurrentTrainsAdapter extends BaseAdapter implements ListAdapter {

	private Context mContext;
	private List<ObjTrainPositions> mData;

	public CurrentTrainsAdapter(Context context, List<ObjTrainPositions> data) {
		super();
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = ((LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.current_trains_item, parent, false);
		}

		ObjTrainPositions trainData = mData.get(position);
		boolean running = trainData.getTrainStatus().equals(
				Constants.TRAIN_STATUS_RUNNING);

		// set a different background and show a label to notify the user if the
		// train is already running or not
		convertView
				.setBackgroundResource(running ? R.drawable.current_trains_bkg_r
						: R.drawable.current_trains_bkg_n);

		convertView.findViewById(R.id.current_trains_item_label_r)
				.setVisibility(running ? View.VISIBLE : View.GONE);
		convertView.findViewById(R.id.current_trains_item_label_n)
				.setVisibility(!running ? View.VISIBLE : View.GONE);

		((TextView) convertView
				.findViewById(R.id.current_trains_item_info_row_1))
				.setText(String.format(mContext
						.getString(R.string.current_trains_item_info_row_1),
						trainData.getTrainCode()));

		((TextView) convertView
				.findViewById(R.id.current_trains_item_info_row_2))
				.setText(trainData.getDirection());

		((TextView) convertView
				.findViewById(R.id.current_trains_item_info_row_3))
				.setText(trainData.getPublicMessage().replace("\\n", "\n"));

		return convertView;
	}
}
