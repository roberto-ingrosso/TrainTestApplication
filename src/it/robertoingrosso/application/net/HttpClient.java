package it.robertoingrosso.application.net;

import it.robertoingrosso.application.Constants;
import it.robertoingrosso.application.data.ArrayOfObjStationData;
import it.robertoingrosso.application.data.ArrayOfObjTrainMovements;
import it.robertoingrosso.application.data.ArrayOfObjTrainPositions;
import it.robertoingrosso.application.data.DataModel;

import org.apache.http.Header;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.os.Handler;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

// HttpClient is a singleton used to perform all the network requests
// for every request an handler is required, in order for the activity or the fragment
// to receive an asynchronous message and update the UI
// Network requests are performed using loopj library
// XML responses are parsed using simple-xml library
public class HttpClient {
	protected static final String TAG = "HttpClient";

	private static AsyncHttpClient sClient = new AsyncHttpClient();

	private static DataModel getDataModel() {
		return DataModel.getInstance();
	}

	// request the station data given a station code and the number of minutes
	public static void getStationData(final String stationCode,
			final int numMins, final Handler handler) {

		RequestParams params = new RequestParams();
		params.add("StationCode", stationCode);
		params.add("NumMins", String.valueOf(numMins));

		sClient.get(Constants.ENDPOINT_STATION_DATA_BY_CODE_WITH_MINS, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String response = new String(responseBody);
						Log.i(TAG, "getStationDataByCodeWithMins performed\n"
								+ response);

						// parse the response
						Serializer serializer = new Persister();
						try {
							ArrayOfObjStationData parsedResponse = serializer
									.read(ArrayOfObjStationData.class, response);

							// store the parsed response in the DataModel
							// to be shown in the UI
							getDataModel().addStationData(stationCode,
									parsedResponse);

							// notify the successful result
							handler.dispatchMessage(obtainMessage(
									Constants.MSG_STATION_DATA_BY_CODE_WITH_MINS_PERFORMED,
									stationCode));
						} catch (Exception e) {
							// notify that the request failed (parser exception)
							handler.dispatchMessage(obtainMessage(
									Constants.MSG_STATION_DATA_BY_CODE_WITH_MINS_FAILED,
									e.getMessage()));
						}

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// notify that the request failed (network exception)
						handler.dispatchMessage(obtainMessage(
								Constants.MSG_STATION_DATA_BY_CODE_WITH_MINS_FAILED,
								error.getMessage()));
					}
				});

	}

	public static void getCurrentTrains(final Handler handler) {

		sClient.get(Constants.ENDPOINT_CURRENT_TRAINS,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String response = new String(responseBody);
						Log.i(TAG, "getCurrentTrains performed\n" + response);

						Serializer serializer = new Persister();
						try {
							ArrayOfObjTrainPositions parsedResponse = serializer
									.read(ArrayOfObjTrainPositions.class,
											response);

							getDataModel().setCurrentTrains(parsedResponse);

							handler.dispatchMessage(obtainMessage(
									Constants.MSG_CURRENT_TRAINS_PERFORMED,
									null));
						} catch (Exception e) {
							handler.dispatchMessage(obtainMessage(
									Constants.MSG_CURRENT_TRAINS_FAILED,
									e.getMessage()));
						}

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						handler.dispatchMessage(obtainMessage(
								Constants.MSG_CURRENT_TRAINS_FAILED,
								error.getMessage()));
					}
				});

	}

	public static void getTrainMovements(final String trainCode, final int day,
			final int month, final int year, final Handler handler) {

		RequestParams params = new RequestParams();
		params.add("TrainId", trainCode);
		params.add("TrainDate", String.valueOf(day) + String.valueOf(month)
				+ String.valueOf(year));

		sClient.get(Constants.ENDPOINT_TRAIN_MOVEMENTS, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						String response = new String(responseBody);
						Log.i(TAG, "getTrainMovements performed\n" + response);

						Serializer serializer = new Persister();
						try {
							ArrayOfObjTrainMovements parsedResponse = serializer
									.read(ArrayOfObjTrainMovements.class,
											response);

							getDataModel().addTrainMovements(trainCode,
									parsedResponse);

							handler.dispatchMessage(obtainMessage(
									Constants.MSG_TRAIN_MOVEMENTS_PERFORMED,
									trainCode));
						} catch (Exception e) {
							handler.dispatchMessage(obtainMessage(
									Constants.MSG_TRAIN_MOVEMENTS_FAILED,
									e.getMessage()));
						}

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						handler.dispatchMessage(obtainMessage(
								Constants.MSG_TRAIN_MOVEMENTS_FAILED,
								error.getMessage()));
					}
				});

	}

}
