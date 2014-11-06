package it.robertoingrosso.application.fragment;

import it.robertoingrosso.application.data.DataModel;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

	protected DataModel getDataModel() {
		return DataModel.getInstance();
	}

}
