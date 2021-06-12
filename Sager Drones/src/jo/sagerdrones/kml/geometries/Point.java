package jo.sagerdrones.kml.geometries;


public class Point extends Geometry {
	
	protected Coordinate coordinate;
	public Point() {
	}

	public Point(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return "Point [coordinate=" + coordinate + "]";
	}

	
	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	
	
	
}
