package it.robertoingrosso.application.data;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ArrayOfObjTrainMovements {

	@ElementList(inline = true, required = false)
	private List<ObjTrainMovements> list;

	public List<ObjTrainMovements> getList() {
		return list;
	}
	// TODO

}
