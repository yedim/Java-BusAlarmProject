package BusAlarmScreen;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.text.*;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.awt.List;
import java.io.*;

public class BusAPI {

	public BusAPI() {
			}

	public NodeList BusInfo() {
		NodeList nList = null;
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.seoul.go.kr:8088/7849444d62796c3435377954726871/xml/CardBusTimeNew/1/129/201708/152/");

		InputStream tmpIS = null;
		BufferedReader rd = null;

		try {
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				tmpIS = conn.getInputStream();
			} else {
				tmpIS = conn.getErrorStream();
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(tmpIS);
			doc.getDocumentElement().normalize();

			nList = doc.getElementsByTagName("row");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return nList;
	}

	public String GetBusStopInfo(int i) {
		String s = null;

		NodeList nList = BusInfo();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			if (temp == i) {
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					s ="<html>"+ eElement.getElementsByTagName("BSST_ARS_NO").item(0).getTextContent()+"<br>"+eElement.getElementsByTagName("BUS_STA_NM").item(0).getTextContent()+"</html>";
					break;
				}
			}

		}

		return s;

	}

}
