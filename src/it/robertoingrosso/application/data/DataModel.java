package it.robertoingrosso.application.data;

import java.util.HashMap;

public class DataModel {

	private static DataModel sInstance;

	protected DataModel() {
		super();
	}

	public static DataModel getInstance() {
		if (sInstance == null) {
			sInstance = new DataModel();
		}
		return sInstance;
	}

	private HashMap<String, ArrayOfObjStationData> mStationData;
	private ArrayOfObjTrainPositions mCurrentTrains;
	private HashMap<String, ArrayOfObjTrainMovements> mTrainMovements;

	public void addStationData(String stationCode,
			ArrayOfObjStationData stationData) {
		if (mStationData == null) {
			mStationData = new HashMap<String, ArrayOfObjStationData>();
		}

		mStationData.put(stationCode.trim(), stationData);
	}

	public ArrayOfObjStationData getStationData(String stationcode) {
		return mStationData.get(stationcode.trim());
	}

	public void setCurrentTrains(ArrayOfObjTrainPositions currentTrains) {
		mCurrentTrains = currentTrains;
	}

	public ArrayOfObjTrainPositions getCurrentTrains() {
		return mCurrentTrains;
	}

	public void addTrainMovements(String trainCode,
			ArrayOfObjTrainMovements trainMovements) {
		if (mTrainMovements == null) {
			mTrainMovements = new HashMap<String, ArrayOfObjTrainMovements>();
		}

		mTrainMovements.put(trainCode.trim(), trainMovements);
	}

	public ArrayOfObjTrainMovements getTrainMovements(String trainCode) {
		return mTrainMovements.get(trainCode.trim());
	}

}
