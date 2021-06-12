package jo.sagerdrones.kml.geometries;



public class Polygon extends Geometry {

	LinearRing linearRing;
	private double area= 0;
	Coordinate centroid;
	public Polygon(LinearRing linearRing) {
		this.linearRing = linearRing;
	}
	public Polygon() {
	}
	/*
	 * I found this formula online that I believe is correct.
	 * http://paulbourke.net/geometry/polygonmesh/
	 */
	double calculateArea() {
		if(area==0) {
			Coordinate coordinate1;
			Coordinate coordinate2;
			for(int i=0;i<linearRing.getNumberOfLines();i++) {
				coordinate1=linearRing.coordinates.get(i);
				if(i==linearRing.getNumberOfLines()-1) {
					coordinate2= linearRing.coordinates.get(0);
				}else {
					coordinate2= linearRing.coordinates.get(i+1);
				}
				double xi= coordinate1.longitude;
				double yi= coordinate1.latitude;
				double xii= coordinate2.longitude;
				double yii= coordinate2.latitude;
				area +=  (xi * yii) - (xii * yi );

			}
			area/=2;
		}
		
		return area;
	}
	Coordinate getCentroid() {
		if(area==0) {
			calculateArea();
		}
		if(centroid==null) {
			double longtitude=0;
			double latitude=0;
			double altitude=0;
			Coordinate coordinate1;
			Coordinate coordinate2;
			for(int i=0;i<linearRing.getNumberOfLines();i++) {
				coordinate1=linearRing.coordinates.get(i);
				if(i==linearRing.getNumberOfLines()-1) {
					coordinate2= linearRing.coordinates.get(0);
				}else {
					coordinate2= linearRing.coordinates.get(i+1);
				}
				double xi=coordinate1.longitude;
				double yi= coordinate1.latitude;
				double zi=coordinate1.altitude;
				double xN=coordinate2.longitude;
				double yN= coordinate2.latitude;
				double zN=coordinate1.altitude;
				longtitude+=(xi+xN)*(xi*yN-xN*yi);
				latitude+=(yi+yN)*(xi*yN-xN*yi);
				if(zi==zN) {
					if(altitude==0)
						altitude=zi;
				}else {
					throw new IllegalArgumentException("All coordinates must have same altitude");
				}
					
			}
			longtitude=longtitude / (6*area);
			latitude=latitude / (6*area);
			centroid=new Coordinate(longtitude,latitude,altitude);
		}
		return centroid;
	}
	@Override
	public void printGeometryInfo() {
		System.out.println("Polygon [centroid=" + getCentroid() + "]");
		
	}
	public LinearRing getLinearRing() {
		return linearRing;
	}
	public void setLinearRing(LinearRing linearRing) {
		this.linearRing = linearRing;
	}

}
