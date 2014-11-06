package it.robertoingrosso.application.adapter;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class DrawerListAdapter extends BaseAdapter implements ListAdapter {

	private Context mContext;

	public DrawerListAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		return Constants.SECTION_LABELS.length;
	}

	@Override
	public Object getItem(int position) {
		return Constants.SECTION_LABELS[position];
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
					.inflate(R.layout.drawer_list_item, parent, false);
		}

		TextView label = (TextView) convertView
				.findViewById(R.id.drawer_list_item_label);

		label.setText(Constants.SECTION_LABELS[position]);

		return convertView;
	}
}
