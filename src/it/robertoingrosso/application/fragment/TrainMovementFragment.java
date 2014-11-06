package it.robertoingrosso.application.fragment;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.R;
import it.robertoingrosso.application.data.ObjTrainMovements;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TrainMovementFragment extends BaseFragment {

	public static final String ARG_TRAIN_CODE = "trainCode";
	public static final String ARG_MOVEMENT_INDEX = "movementIndex";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.fragment_train_movement,
				container, false);

		ObjTrainMovements trainMovement = getDataModel()
				.getTrainMovements(getArguments().getString(ARG_TRAIN_CODE))
				.getList().get(getArguments().getInt(ARG_MOVEMENT_INDEX));

		((TextView) layout.findViewById(R.id.train_movement_item_info_row_1))
				.setText(String.format(
						getString(R.string.train_movement_item_info_row_1),
						trainMovement.getLocationFullName(),
						trainMovement.getLocationCode()));

		for (int i = 0; i < Constants.LOCATION_TYPES.length; i++) {
			String locationType = Constants.LOCATION_TYPES[i];
			if (trainMovement.getLocationType().equals(locationType)) {
				((TextView) layout
						.findViewById(R.id.train_movement_item_info_row_2))
						.setText(Constants.LOCATION_TYPE_LABELS[i]);
			}
		}

		if (!trainMovement.getLocationType()
				.equals(Constants.LOCATION_TYPES[3])) {
			((TextView) layout
					.findViewById(R.id.train_movement_item_info_row_3))
					.setText(trainMovement.getDeparture() != null ? String
							.format(getString(R.string.departure),
									trainMovement.getDeparture()) : String
							.format(getString(R.string.scheduled_departure),
									trainMovement.getScheduledDeparture()));
		} else {
			((TextView) layout
					.findViewById(R.id.train_movement_item_info_row_3))
					.setText(String.format(
							getString(R.string.scheduled_arrival),
							trainMovement.getScheduledArrival()));
		}

		return layout;
	}
}
