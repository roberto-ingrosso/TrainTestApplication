package it.robertoingrosso.application.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "objStationData")
public class ObjStationData {

	@Element
	private String Servertime;

	@Element
	private String Traincode;

	@Element
	private String Stationfullname;

	@Element
	private String Stationcode;

	@Element
	private String Querytime;

	@Element
	private String Traindate;

	@Element
	private String Origin;

	@Element
	private String Destination;

	@Element
	private String Origintime;

	@Element
	private String Destinationtime;

	@Element
	private String Status;

	@Element(required = false)
	private String Lastlocation;

	@Element
	private String Duein;

	@Element
	private String Late;

	@Element
	private String Exparrival;

	@Element
	private String Expdepart;

	@Element
	private String Scharrival;

	@Element
	private String Schdepart;

	@Element
	private String Direction;

	@Element
	private String Traintype;

	@Element
	private String Locationtype;

	public String getServertime() {
		return Servertime;
	}

	public String getTraincode() {
		return Traincode;
	}

	public String getStationfullname() {
		return Stationfullname;
	}

	public String getStationcode() {
		return Stationcode;
	}

	public String getQuerytime() {
		return Querytime;
	}

	public String getTraindate() {
		return Traindate;
	}

	public String getOrigin() {
		return Origin;
	}

	public String getDestination() {
		return Destination;
	}

	public String getOrigintime() {
		return Origintime;
	}

	public String getDestinationtime() {
		return Destinationtime;
	}

	public String getStatus() {
		return Status;
	}

	public String getLastlocation() {
		return Lastlocation;
	}

	public String getDuein() {
		return Duein;
	}

	public String getLate() {
		return Late;
	}

	public String getExparrival() {
		return Exparrival;
	}

	public String getExpdepart() {
		return Expdepart;
	}

	public String getScharrival() {
		return Scharrival;
	}

	public String getSchdepart() {
		return Schdepart;
	}

	public String getDirection() {
		return Direction;
	}

	public String getTraintype() {
		return Traintype;
	}

	public String getLocationtype() {
		return Locationtype;
	}

	@Override
	public String toString() {
		return "ObjStationData [Servertime=" + Servertime + ", Traincode="
				+ Traincode + ", Stationfullname=" + Stationfullname
				+ ", Stationcode=" + Stationcode + ", Querytime=" + Querytime
				+ ", Traindate=" + Traindate + ", Origin=" + Origin
				+ ", Destination=" + Destination + ", Origintime=" + Origintime
				+ ", Destinationtime=" + Destinationtime + ", Status=" + Status
				+ ", Lastlocation=" + Lastlocation + ", Duein=" + Duein
				+ ", Late=" + Late + ", Exparrival=" + Exparrival
				+ ", Expdepart=" + Expdepart + ", Scharrival=" + Scharrival
				+ ", Schdepart=" + Schdepart + ", Direction=" + Direction
				+ ", Traintype=" + Traintype + ", Locationtype=" + Locationtype
				+ "]";
	}

}