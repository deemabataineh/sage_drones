package jo.sagerdrones.kml.geometries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Coordinate {
	
	protected double longitude;

	protected double latitude;

	protected double altitude;

	public Coordinate(double longitude, double latitude, double altitude) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}
	public Coordinate(double longitude, double latitude) {
		this(longitude,latitude,0);
	}
	 public Coordinate(String coordinatesString) {
		List<String> coordinatesAsList =  Arrays.asList(coordinatesString.split("\\s*,\\s*"));
		if(coordinatesAsList.size()<2 || coordinatesAsList.size()>3) {
			throw new IllegalArgumentException("invalid coordinates format.");
		}
		this.longitude=Double.valueOf(coordinatesAsList.get(0));
		this.latitude=Double.valueOf(coordinatesAsList.get(1));
		if(coordinatesAsList.size()==3) {
			this.altitude=Double.valueOf(coordinatesAsList.get(2));
		}else if(coordinatesAsList.size()==2) {
			this.altitude=Double.valueOf(0.0);
		}
		
		
	}
	 static public List<Coordinate> getCoordinates(String coordinatesString) {
		List<String> coordinatesAsList =  Arrays.asList(coordinatesString.split("\\s*,\\s*|\\s+"));
		List<Coordinate> coordinates=  new LinkedList<Coordinate>();
		for(int i=0;i<coordinatesAsList.size();i+=3) {
			Coordinate coordinate= new Coordinate(String.format("%s,%s,%s", coordinatesAsList.get(i),
					coordinatesAsList.get(i+1),
					coordinatesAsList.get(i+2)
					));
			coordinates.add(coordinate);
		}
		return coordinates;
		
	}
	@Override
	public String toString() {
		return "[longitude=" + longitude + ", latitude=" + latitude + ", altitude=" + altitude + "]";
	}
	public double getLongtitude() {
		return longitude;
	}

	public void setLongtitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

}