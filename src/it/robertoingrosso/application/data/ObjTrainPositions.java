package it.robertoingrosso.application.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "objTrainPositions")
public class ObjTrainPositions {

	@Element
	private String TrainStatus;

	@Element
	private String TrainLatitude;

	@Element
	private String TrainLongitude;

	@Element
	private String TrainCode;

	@Element
	private String TrainDate;

	@Element
	private String PublicMessage;

	@Element
	private String Direction;

	public String getTrainStatus() {
		return TrainStatus;
	}

	public String getTrainLatitude() {
		return TrainLatitude;
	}

	public String getTrainLongitude() {
		return TrainLongitude;
	}

	public String getTrainCode() {
		return TrainCode;
	}

	public String getTrainDate() {
		return TrainDate;
	}

	public String getPublicMessage() {
		return PublicMessage;
	}

	public String getDirection() {
		return Direction;
	}

	@Override
	public String toString() {
		return "ObjStationData [TrainStatus=" + TrainStatus
				+ ", TrainLatitude=" + TrainLatitude + ", TrainLongitude="
				+ TrainLongitude + ", TrainCode=" + TrainCode + ", TrainDate="
				+ TrainDate + ", PublicMessage=" + PublicMessage
				+ ", Direction=" + Direction + "]";
	}

}