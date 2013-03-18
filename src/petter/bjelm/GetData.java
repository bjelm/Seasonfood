package petter.bjelm;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

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

public class GetData {
    public static void main (String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        URL url = new URL("http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=ask&query=[[Kategori:Gr%C3%B6nsaker]][[I%20s%C3%A4song::1912-03-11]]");
        URLConnection conn = url.openConnection();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());

        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform = tfactory.newTransformer();

        // that’s the default xform; use a stylesheet to get a real one
        xform.transform(new DOMSource(doc), new StreamResult(System.out));
        
        doc.getDocumentElement().normalize();
		
		//System.out.println(doc);
		
		NodeList nList = doc.getElementsByTagName("results");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			Node nNode = nList.item(temp);
	 
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			for (int temp2=0; temp2 < nNode.getChildNodes().getLength(); temp2++ ){
				
			//	System.out.println("Grönsak : " + nNode.getChildNodes().item(temp2).getNodeName());
			}
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
					Element eElement = (Element) nNode;
				
					//System.out.println("Grönsak : " + eElement.);
					//System.out.println("current_value : " + eElement.getElementsByTagName("value").item(0).getTextContent());
					//System.out.println("max_value : " + eElement.getElementsByTagName("value").item(0).getAttributes().getNamedItem("maxValue").getNodeValue());

					//Add values
					
			}
			
		}
    }
}