package jo.sagerdrones.kml.geometries;

import java.util.List;

public class LinearRing extends Geometry{
	/*
	 * Coordinates in a linear ring are vertices, where the last vertex (xN,yN) is assumed to be the same as the first, to form a closed shape (Polygon).
	 */
	List<Coordinate> coordinates;
	
	public LinearRing(List<Coordinate> coordinates) {
		super();
		this.coordinates = coordinates;
	}
	public LinearRing() {
	}
	public List<Coordinate> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
	public long getNumberOfLines() {
		return coordinates.size();
	}
	@Override
	public String toString() {
		return "LinearRing [coordinates=" + coordinates + "]";
	}
}
