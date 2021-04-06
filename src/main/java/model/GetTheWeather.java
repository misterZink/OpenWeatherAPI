package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class GetTheWeather {

	public static void getWeather(WeatherBean wBean) throws IOException {
		// TODO Auto-generated method stub
		
		// String urlToSend = "http://api.openweathermap.org/data/2.5/weather?q=malmo,se&APPID=0442bc83abb192a5fd0085c9db42c5bc&mode=xml";
		
		String urlToSend = "http://api.openweathermap.org/data/2.5/weather?q=" + wBean.getCityString() + ","
				+ wBean.getCountryString() + "&APPID=0442bc83abb192a5fd0085c9db42c5bc&mode=xml";
		
		
		// System.out.println(urlToSend);
		
		URL line_api_url = new URL(urlToSend);
		
		HttpURLConnection lineConnection = (HttpURLConnection) line_api_url.openConnection();
		
		lineConnection.setDoInput(true);
		
		lineConnection.setDoOutput(true);
		
		lineConnection.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(lineConnection.getInputStream()));
		
		String inputLine;
		
		String ApiResponse = "";
		
		
		
		while((inputLine = in.readLine()) != null) {
			
		//	System.out.println(inputLine);
			
			ApiResponse += inputLine;
			
		}
		in.close();
		
		// System.out.println(ApiResponse);
		
		Document doc =  convertStringToXMLDocument(ApiResponse);
		
		
		doc.getDocumentElement().normalize();
		
		// System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
		
		NodeList nList = doc.getElementsByTagName("clouds");
		NodeList nListTemperature = doc.getElementsByTagName("temperature");
		
		
		setAttributesFromNodeList(wBean, nList, "clouds", "name");
		setAttributesFromNodeList(wBean, nListTemperature, "temperature", "value");
		
		
	}

	private static void setAttributesFromNodeList(WeatherBean wBean, NodeList nList, String nodeName, String attributeName) {
		for (int i= 0; i<nList.getLength(); i++) {
			
			Node node = nList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				
				String XMLString = eElement.getAttribute(attributeName);
				
				// System.out.println(XMLString);
				
				wBean.setAttributes(nodeName, XMLString);
			}
			
		}
	}

	private static Document convertStringToXMLDocument(String xmlString) {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = null;
		
		
		try {
			
			builder = factory.newDocumentBuilder();
			
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
			
			return doc;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
