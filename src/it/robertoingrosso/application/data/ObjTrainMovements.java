package it.robertoingrosso.application.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "objTrainMovements")
public class ObjTrainMovements {

	@Element
	private String TrainCode;

	@Element
	private String TrainDate;

	@Element
	private String LocationCode;

	@Element
	private String LocationFullName;

	@Element
	private String LocationOrder;

	@Element
	private String LocationType;

	@Element
	private String TrainOrigin;

	@Element
	private String TrainDestination;

	@Element
	private String ScheduledArrival;

	@Element
	private String ScheduledDeparture;

	@Element
	private String ExpectedArrival;

	@Element
	private String ExpectedDeparture;

	@Element(required = false)
	private String Arrival;

	@Element(required = false)
	private String Departure;

	@Element(required = false)
	private String AutoArrival;

	@Element(required = false)
	private String AutoDepart;

	@Element
	private String StopType;

	public String getTrainCode() {
		return TrainCode;
	}

	public String getTrainDate() {
		return TrainDate;
	}

	public String getLocationCode() {
		return LocationCode;
	}

	public String getLocationFullName() {
		return LocationFullName;
	}

	public String getLocationOrder() {
		return LocationOrder;
	}

	public String getLocationType() {
		return LocationType;
	}

	public String getTrainOrigin() {
		return TrainOrigin;
	}

	public String getTrainDestination() {
		return TrainDestination;
	}

	public String getScheduledArrival() {
		return ScheduledArrival;
	}

	public String getScheduledDeparture() {
		return ScheduledDeparture;
	}

	public String getExpectedArrival() {
		return ExpectedArrival;
	}

	public String getExpectedDeparture() {
		return ExpectedDeparture;
	}

	public String getArrival() {
		return Arrival;
	}

	public String getDeparture() {
		return Departure;
	}

	public String getAutoArrival() {
		return AutoArrival;
	}

	public String getAutoDepart() {
		return AutoDepart;
	}

	public String getStopType() {
		return StopType;
	}

	@Override
	public String toString() {
		return "ObjTrainMovements [TrainCode=" + TrainCode + ", TrainDate="
				+ TrainDate + ", LocationCode=" + LocationCode
				+ ", LocationFullName=" + LocationFullName + ", LocationOrder="
				+ LocationOrder + ", LocationType=" + LocationType
				+ ", TrainOrigin=" + TrainOrigin + ", TrainDestination="
				+ TrainDestination + ", ScheduledArrival=" + ScheduledArrival
				+ ", ScheduledDeparture=" + ScheduledDeparture
				+ ", ExpectedArrival=" + ExpectedArrival
				+ ", ExpectedDeparture=" + ExpectedDeparture + ", Arrival="
				+ Arrival + ", Departure=" + Departure + ", AutoArrival="
				+ AutoArrival + ", AutoDepart=" + AutoDepart + ", StopType="
				+ StopType + "]";
	}

}