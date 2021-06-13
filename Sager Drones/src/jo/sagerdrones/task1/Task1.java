package jo.sagerdrones.task1;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;

import jo.sagerdrones.kml.KmlUtil;
import jo.sagerdrones.kml.Placemark;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Task1 {

	public static void main(String[] args) {
			
		try {
			File kmlSample= new File(args[0]);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder= dbFactory.newDocumentBuilder();
			Document doc= dBuilder.parse(kmlSample);
			doc.getDocumentElement().normalize();
			if(!doc.getDocumentElement().getNodeName().equals("kml")){
				System.out.println("File is not Keyhole Markup Language file.");
				return;
			}
			NodeList placemarkNodesList = doc.getElementsByTagName("Placemark");
			if(placemarkNodesList.getLength()<0) {
				System.out.println("File does not contain place marks!");
				return;
			}
			for(int i =0;i<placemarkNodesList.getLength();i++) {
				Node placemarkNode=placemarkNodesList.item(i);
//				parsePlaceMarkChildNode(placemarkNode);
				Placemark placemark=KmlUtil.parsePlacenameNode(placemarkNode);
				placemark.printInfo();

			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}catch (SAXException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
			


	}

//	private static void parsePlaceMarkChildNode(Node node) {
//	if(node.getNodeName().equalsIgnoreCase("Point")) {
//		System.out.println("Point, coordinates:: "+getPointCoordinates(node));
//	}else if(node.getNodeName().equalsIgnoreCase("polygon")) {
//		System.out.println("polygon");
//	}else if(node.getNodeName().equalsIgnoreCase("multiGeometry")) {
//		System.out.println("multiGeometry");
//	}else if(node.getNodeName().equalsIgnoreCase("LinearRing")) {
//		System.out.println("LinearRing");
//	}else if(node.getNodeName().equalsIgnoreCase("LineString")) {
//	//	System.out.println("LineString, length: "+getLineStringLengthInMeters(node)/1000+"km");
//	}
//	if(!node.hasChildNodes())
//		return;
//	NodeList nodeList= node.getChildNodes();
//	for(int i =0;i<nodeList.getLength();i++) 
//		parsePlaceMarkChildNode(nodeList.item(i));
//	
//	}

	
}

	
