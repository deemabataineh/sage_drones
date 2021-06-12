package jo.sagerdrones.kml;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jo.sagerdrones.kml.geometries.Coordinate;
import jo.sagerdrones.kml.geometries.Geometry;
import jo.sagerdrones.kml.geometries.GeometryFactory;
import jo.sagerdrones.kml.geometries.GeometryType;
import jo.sagerdrones.kml.geometries.LineString;
import jo.sagerdrones.kml.geometries.LinearRing;
import jo.sagerdrones.kml.geometries.MultiGeometry;
import jo.sagerdrones.kml.geometries.Point;
import jo.sagerdrones.kml.geometries.Polygon;

public class KmlUtil {
	
	private static final String NAME_TAG_NAME="name";
	private static final String DESCRIPTION_TAG_NAME="description";
	private static final String COORDINATES_TAG_NAME="coordinates";
	private static final String POINT_TAG_NAME="Point";
	private static final String POLYGON_TAG_NAME="Polygon";
	private static final String LINE_STRING_TAG_NAME="LineString";
	private static final String MUTLIGEOMETRY_TAG_NAME="MultiGeometry";
	private static final String LINEAR_RING_TAG_NAME="LinearRing";
	
	public static Placemark parsePlacenameNode(Node placemarkNode) {
		Placemark placemark= new Placemark();
		NodeList placemarkChildNodesList=placemarkNode.getChildNodes();
		for(int i =0;i<placemarkChildNodesList.getLength();i++) {
			Node placemarkChildNode=placemarkChildNodesList.item(i);
			String tagName=placemarkChildNode.getNodeName();
			switch(tagName){
			case DESCRIPTION_TAG_NAME:
				placemark.setDescription(placemarkChildNode.getTextContent());
				break;
			case NAME_TAG_NAME:
				placemark.setName(placemarkChildNode.getTextContent());
				break;
			case POINT_TAG_NAME:
			case POLYGON_TAG_NAME:
			case LINE_STRING_TAG_NAME:
			case LINEAR_RING_TAG_NAME:
			case MUTLIGEOMETRY_TAG_NAME:
				placemark.setGeometry(parseGeometryNode(placemarkChildNode));
			}
		}
		return placemark;

	}
	static private Geometry parseGeometryNode(Node geometryNode) {
		GeometryType geometryType=GeometryType.getGeometryType(geometryNode.getNodeName());
		Geometry geometry=GeometryFactory.createInstance(geometryType);
		switch(geometryType){
			case POINT:
				parsePoint((Point)geometry,geometryNode);
				break;
			case POLYGON:
				parsePolygon((Polygon)geometry,geometryNode );
				break;
			case MULTIGEOMETRY:
				parseMultiGeometry((MultiGeometry)geometry,geometryNode);
				break;
			case LINEARRING:
				parseLinearRing((LinearRing)geometry,geometryNode);
				break;
			case LINESTRING:
				parseLineString((LineString)geometry,geometryNode);
				break;

		}

		return geometry;
	}
	private static void parseLineString(LineString lineString, Node lineStringNode) {
		Node coordinateNode=getChildNodeByTag(COORDINATES_TAG_NAME,lineStringNode,lineStringNode.getLastChild());
		lineString.setCoordinates(Coordinate.getCoordinates(coordinateNode.getTextContent()));
	
	}
	private static void parseLinearRing(LinearRing linearRing, Node linearRingNode) {
		Node coordinateNode=getChildNodeByTag(COORDINATES_TAG_NAME,linearRingNode,linearRingNode.getLastChild());
		linearRing.setCoordinates(Coordinate.getCoordinates(coordinateNode.getTextContent()));
	
	}
	private static void parseMultiGeometry(MultiGeometry multiGeometry, Node multiGeometryNode) {
		NodeList multiGeometryChildNodesList=multiGeometryNode.getChildNodes();
		List<Geometry> geometries= new LinkedList<>();
		for(int i =0;i<multiGeometryChildNodesList.getLength();i++) {
			Node multiGeometryChildNode=multiGeometryChildNodesList.item(i);
			String tagName=multiGeometryChildNode.getNodeName();
			switch(tagName){
			case POINT_TAG_NAME:
			case POLYGON_TAG_NAME:
			case LINE_STRING_TAG_NAME:
			case LINEAR_RING_TAG_NAME:
			case MUTLIGEOMETRY_TAG_NAME:
				geometries.add(parseGeometryNode(multiGeometryChildNodesList.item(i)));
			}
			
		}
		multiGeometry.setGeometries(geometries);
	}
	private static void parsePolygon(Polygon polygon, Node polygonNode) {
		Node linearRingNode=getChildNodeByTag(LINEAR_RING_TAG_NAME,polygonNode,polygonNode.getLastChild());
		LinearRing linearRing=(LinearRing) parseGeometryNode(linearRingNode);
		polygon.setLinearRing(linearRing);
	
	}
	private static void parsePoint(Point point,Node pointNode) {
		Node coordinateNode=getChildNodeByTag(COORDINATES_TAG_NAME,pointNode,pointNode.getLastChild());
		point.setCoordinate(new Coordinate(coordinateNode.getTextContent()));
		
	}
	private static Node getChildNodeByTag(String tag,Node node,Node lastChild) {
		if(node.getNodeName().equalsIgnoreCase(tag)) {
			return node;
		}else if(node.hasChildNodes()){
			return getChildNodeByTag(tag,node.getFirstChild(),lastChild);
		}else if(node.equals(lastChild)) {
			return null;
		}else if(node.getNextSibling()==null) {
			return getChildNodeByTag(tag,node.getParentNode().getNextSibling(),lastChild);
		}else{
			return getChildNodeByTag(tag,node.getNextSibling(),lastChild);
		}
	}

}
