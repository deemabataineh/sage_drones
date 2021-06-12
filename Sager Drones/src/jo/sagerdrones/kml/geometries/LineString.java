package jo.sagerdrones.kml.geometries;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class LineString extends Geometry {
	/*
	 * line string length in meters
	 */
	private double length=0;
	/*
	 * Earth radius in meters
	 */
	private final double R=6371e3;
	protected List<Coordinate> coordinates;

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	private double getLengthInMeters(Coordinate coordinate1, Coordinate coordinate2) {
		/*
		  I found this ‘haversine’ formula online to calculate the great-circle distance between two points
		  resource: https://www.movable-type.co.uk/scripts/latlong.html
		  
			    Haversine:	a = sin²(Δφ/2) + cos φ1 ⋅ cos φ2 ⋅ sin²(Δλ/2)
				formula:	c = 2 ⋅ atan2( √a, √(1−a) )
				d = R ⋅ c
				
			where φ is latitude, λ is longitude, R is earth’s radius (mean radius = 6,371km);
			Angles need to be in radians.
		 */
		double long1,lat1,long2,lat2,theta1,lambda1,theta2,lambda2,deltaTheta,deltaLambda;
		long1=coordinate1.getLongtitude();
		lat1= coordinate1.getLatitude();
		long2=coordinate2.getLongtitude();
		lat2= coordinate2.getLatitude();
		theta1= degreeToRadian(lat1);
		lambda1= degreeToRadian(long1);
		theta2= degreeToRadian(lat2);
		lambda2= degreeToRadian(long2);
		deltaTheta=theta2-theta1;
		deltaLambda=lambda2-lambda1;
		double a= Math.sin(deltaTheta/2) * Math.sin(deltaTheta/2) +
		          Math.cos(theta1) * Math.cos(theta2) *
		          Math.sin(deltaLambda/2) * Math.sin(deltaLambda/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return R * c;
		
	}

	public double getLength() {
		if(length==0) {
			for(int i=0;i<coordinates.size()-1;i++) {
				Coordinate coordinate1=coordinates.get(i);
				Coordinate coordinate2=coordinates.get(i+1);
				length+=getLengthInMeters(coordinate1, coordinate2);
			}
			
		}
		return length;
	}
	private double degreeToRadian(double angleInDegrees) {
		/*
		 * 180° = π
		 */
		return angleInDegrees * Math.PI/180 ;
	}

	@Override
	public void printGeometryInfo() {
		System.out.println("LineString [Length = " +new BigDecimal (getLength()/1000).setScale(3, RoundingMode.HALF_UP)+ " Km ].");
		
	}
}
