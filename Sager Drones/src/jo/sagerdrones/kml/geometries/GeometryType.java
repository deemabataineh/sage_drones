package jo.sagerdrones.kml.geometries;

public enum GeometryType {
	POINT,LINESTRING,MULTIGEOMETRY,POLYGON,LINEARRING;
	public static GeometryType getGeometryType(String name) {
		if(name.equalsIgnoreCase(GeometryType.POINT.name())) {
			return GeometryType.POINT;
		}else if(name.equalsIgnoreCase(GeometryType.LINESTRING.name())) {
			return GeometryType.LINESTRING;
		}else if(name.equalsIgnoreCase(GeometryType.MULTIGEOMETRY.name())) {
			return GeometryType.MULTIGEOMETRY;
		}else if(name.equalsIgnoreCase(GeometryType.POLYGON.name())) {
			return GeometryType.POLYGON;
		}else if(name.equalsIgnoreCase(GeometryType.LINEARRING.name())) {
			return GeometryType.LINEARRING;
		}
		
		return null;
	}
}
