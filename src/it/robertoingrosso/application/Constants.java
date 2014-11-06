package it.robertoingrosso.application;

public class Constants {

	public static final String[] SECTION_LABELS = { "Check ongoing trains",
			"I\'m in Arklow", "I\'m in Shankill" };

	public static final String HOST = "http://api.irishrail.ie/";

	public static final String ENDPOINT_STATION_DATA_BY_CODE_WITH_MINS = HOST
			+ "/realtime/realtime.asmx/getStationDataByCodeXML_WithNumMins";
	public static final String ENDPOINT_CURRENT_TRAINS = HOST
			+ "/realtime/realtime.asmx/getCurrentTrainsXML";
	public static final String ENDPOINT_TRAIN_MOVEMENTS = HOST
			+ "/realtime/realtime.asmx/getTrainMovementsXML";

	public static final int MSG_STATION_DATA_BY_CODE_WITH_MINS_PERFORMED = 100;
	public static final int MSG_STATION_DATA_BY_CODE_WITH_MINS_FAILED = 101;
	public static final int MSG_CURRENT_TRAINS_PERFORMED = 110;
	public static final int MSG_CURRENT_TRAINS_FAILED = 111;
	public static final int MSG_TRAIN_MOVEMENTS_PERFORMED = 120;
	public static final int MSG_TRAIN_MOVEMENTS_FAILED = 121;

	public static final String STATION_CODE_ARKLOW = "ARKLW";
	public static final String STATION_CODE_SHANKILL = "SKILL";

	public static final int DEFAULT_MINUTES_SPAN = 90;

	public static final String TRAIN_STATUS_RUNNING = "R";
	public static final String TRAIN_STATUS_NOT_YET_RUNNING = "N";

	public static final String[] LOCATION_TYPES = { "O", "S", "T", "D" };
	public static final String[] LOCATION_TYPE_LABELS = { "Origin", "Stop",
			"Timing Stop", "Destination" };

}
