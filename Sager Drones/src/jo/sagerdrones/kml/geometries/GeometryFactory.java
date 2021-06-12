package jo.sagerdrones.kml.geometries;


public class GeometryFactory {
	private GeometryFactory() {
		 throw new IllegalStateException("calling constructor is not allowed");
	}
    public static Geometry createInstance(GeometryType type) {
        switch (type) {
            case POINT:
                return new Point();
            case LINESTRING:
                return new LineString();
            case POLYGON:
                return new Polygon();
            case MULTIGEOMETRY:
            	return new MultiGeometry();
            case LINEARRING:
            	return new LinearRing();
        }
        return null;
    }
}
