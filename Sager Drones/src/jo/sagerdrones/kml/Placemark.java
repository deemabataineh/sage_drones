package jo.sagerdrones.kml;

import jo.sagerdrones.kml.geometries.Geometry;

public class Placemark {
	private String name;
	private String description;
	private Geometry geometry;

	public Placemark() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}


	public void printInfo() {
		System.out.print("Placemark[\n\t"+(name==null?"":"name: " + name)+ (description==null?"":"\n\tdescription: " + description) +"\n\tGeometric info:");
		geometry.printGeometryInfo();
		System.out.println("\t]");
		
		
	}

	
}
