package jo.sagerdrones.kml.geometries;

import java.util.List;

public class MultiGeometry extends Geometry{
	List<Geometry> geometries;
	
	public MultiGeometry(List<Geometry> geometries) {
		super();
		this.geometries = geometries;
	}
	public MultiGeometry() {
	}
	@Override
	public void printGeometryInfo() {
		for(Geometry geo:geometries) {
			geo.printGeometryInfo();
		}
		
	}
	public List<Geometry> getGeometries() {
		return geometries;
	}
	public void setGeometries(List<Geometry> geometries) {
		this.geometries = geometries;
	}

}
