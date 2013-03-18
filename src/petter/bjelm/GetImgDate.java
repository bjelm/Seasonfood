package petter.bjelm;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetImgDate {

	public static String monthText = "00";
	public static String dayText = "00";
	public static String type = "";
	static ArrayList<String> imgNamesArray = new ArrayList<String>();
	static ArrayList<String> imgURLArray = new ArrayList<String>();
	static Document doc;
	static Document imgDoc;

	public static void main(String[] args) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {

		getCurrDate();
		type = "Kål";

		parse();

		NodeList nList = doc.getElementsByTagName("results");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			//System.out.println("\nCurrent Element :" + nNode.getNodeName());

			for (int temp2 = 0; temp2 < nNode.getChildNodes().getLength(); temp2++) {

			//	System.out.println(type + " : "+ nNode.getChildNodes().item(temp2).getNodeName());
			}

			Element root = doc.getDocumentElement();
			NodeList nodes = root.getElementsByTagName("value");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node data = nodes.item(i);

				if (data instanceof Element) {
					Element name = (Element) data;
					imgNamesArray.add(name.getAttribute("fulltext"));
					
				}
			}
			
			for (int i = 0; i < imgNamesArray.size(); i++) {
				imgURLArray.add(parseImg(imgNamesArray.get(i)));
			}
			

			
			System.out.println(dayText + "/" + monthText);
			for (int i = 0; i < imgNamesArray.size(); i++) {
				System.out.println(imgNamesArray.get(i));
				//System.out.println(imgURLArray.get(i));
			}
			
			for (int i = 0; i < imgURLArray.size(); i++) {
				//System.out.println(imgNamesArray.get(i));
				System.out.println(imgURLArray.get(i));
			}
		}
	}

	// * Ändrar URL till dagens datum */
	public static void getCurrDate() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < 10) {
			dayText = "0" + Integer.toString(day);
		} else {
			dayText = Integer.toString(day);
		}
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 10) {
			monthText = "0" + Integer.toString(month);
		} else {
			monthText = Integer.toString(month);
		}
	}

	public static void parse() throws IOException,
			ParserConfigurationException, SAXException, TransformerException {
		URL url = new URL(
				"http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=ask&query=[[Kategori:"
						+ type + "]][[I+säsong+Z3::1912-" + monthText + "-"
						+ dayText + "]]|?bild");
		URLConnection conn = url.openConnection();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		doc = builder.parse(conn.getInputStream());

		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer xform = tfactory.newTransformer();

		// that’s the default xform; use a stylesheet to get a real one
		xform.transform(new DOMSource(doc), new StreamResult(System.out));

		doc.getDocumentElement().normalize();
	}

	public static String parseImg(String imgText) throws IOException,
			ParserConfigurationException, SAXException, TransformerException {
		String s = null;
		URL url = new URL(
				"http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=query&titles="
						+ imgText.replace(" ", "%20") + "&prop=imageinfo&iiprop=url");
		URLConnection conn = url.openConnection();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		imgDoc = builder.parse(conn.getInputStream());
		
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer xform = tfactory.newTransformer();

		// that’s the default xform; use a stylesheet to get a real one
		xform.transform(new DOMSource(imgDoc), new StreamResult(System.out));

		imgDoc.getDocumentElement().normalize();
		
		Element imgRoot = imgDoc.getDocumentElement();
		NodeList imgNodes = imgRoot.getElementsByTagName("ii");
		for (int i = 0; i < imgNodes.getLength(); i++) {
			Node imgData = imgNodes.item(i);

			if (imgData instanceof Element) {
				Element name = (Element) imgData;
				s= name.getAttribute("url");

			}
		}
		return s;
	}
	
}