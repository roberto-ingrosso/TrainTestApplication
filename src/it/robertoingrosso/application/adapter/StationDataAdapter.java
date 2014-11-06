package it.robertoingrosso.application.adapter;

import it.robertoingrosso.application.R;
import it.robertoingrosso.application.data.ObjStationData;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class StationDataAdapter extends BaseAdapter implements ListAdapter {

	private Context mContext;
	private List<ObjStationData> mData;

	public StationDataAdapter(Context context, List<ObjStationData> data) {
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
					.inflate(R.layout.station_data_item, parent, false);
		}

		ObjStationData stationData = mData.get(position);

		((TextView) convertView.findViewById(R.id.station_data_item_traintype))
				.setText(stationData.getTraintype());
		((TextView) convertView.findViewById(R.id.station_data_item_traincode))
				.setText(stationData.getTraincode());
		((TextView) convertView.findViewById(R.id.station_data_item_traindate))
				.setText(stationData.getTraindate());
		((TextView) convertView.findViewById(R.id.station_data_item_info_row_1))
				.setText(String.format(mContext
						.getString(R.string.station_data_item_info_row_1),
						stationData.getOrigin(), stationData.getOrigintime(),
						stationData.getDestination(), stationData
								.getDestinationtime()));
		((TextView) convertView.findViewById(R.id.station_data_item_info_row_2))
				.setText(String.format(mContext
						.getString(R.string.station_data_item_info_row_2),
						(stationData.getLastlocation() == null ? mContext
								.getString(R.string.no_last_location):stationData.getLastlocation()), stationData.getStatus()));
		((TextView) convertView.findViewById(R.id.station_data_item_info_row_3))
				.setText(String.format(mContext
						.getString(R.string.station_data_item_info_row_3),
						stationData.getScharrival(), stationData.getLate()));

		return convertView;
	}
}
