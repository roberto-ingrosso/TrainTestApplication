package it.robertoingrosso.application.data;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ArrayOfObjTrainPositions {

	@ElementList(inline = true, required = false)
	private List<ObjTrainPositions> list;

	public List<ObjTrainPositions> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "ArrayOfObjTrainPositions [list=" + list + "]";
	}

}
