package it.robertoingrosso.application.data;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ArrayOfObjStationData {

	@ElementList(inline = true, required = false)
	private List<ObjStationData> list;

	public List<ObjStationData> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "ArrayOfObjStationData [list=" + list + "]";
	}

}
