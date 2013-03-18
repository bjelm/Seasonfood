package petter.bjelm;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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

public class GetCategory {
	
	public String chosenSort;
	public String chosenDate;
	public ArrayList<String> sortArray;
	public URL url;
	URLConnection conn;
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document doc;
	TransformerFactory tfactory;
	Transformer xform;
	Node nNode;
	NodeList nList;
	Element eElement;
	
    public GetCategory(String sort, String date) throws IOException, ParserConfigurationException, SAXException, TransformerException{
        if (sort==""){

        	chosenSort = "Grönsaker";        	        	
        }else{
        	chosenSort = sort;        	        	
        }
        
        if (date == ""){
        	chosenDate = "03";	
        	
        } else{
        	
        	chosenDate = date;		
        }

    	
    	sortArray = new ArrayList<String>();
    	
    	url = new URL("http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=ask&query=[[Kategori:"+chosenSort+"]][[I%20s%C3%A4song::1912-"+chosenDate+"-11]]");
        conn = url.openConnection();
        
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        doc = builder.parse(conn.getInputStream());

        tfactory = TransformerFactory.newInstance();
        xform = tfactory.newTransformer();

        // that’s the default xform; use a stylesheet to get a real one
        xform.transform(new DOMSource(doc), new StreamResult(System.out));
        
        doc.getDocumentElement().normalize();
		
		//System.out.println(doc);
		
		nList = doc.getElementsByTagName("results");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			nNode = nList.item(temp);
	 
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			for (int temp2=0; temp2 < nNode.getChildNodes().getLength(); temp2++ ){
				
				//System.out.println(nNode.getChildNodes().item(temp2).getNodeName());
				
			
			}
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
					eElement = (Element) nNode;
					
			}
			
		}
		
    }
    
    public void setCategory(String name){
		
		chosenSort = name;

	}
    
    public void setDate(String date){
		
		chosenDate = date;
		
	}
    
    public ArrayList<String> getArray() throws IOException, ParserConfigurationException, SAXException, TransformerException{
    	
    	sortArray.clear();
    	
    	url = new URL("http://xn--ssongsmat-v2a.nu/w/api.php?format=xml&action=ask&query=[[Kategori:"+chosenSort+"]][[I%20s%C3%A4song::1912-"+chosenDate+"-11]]");
        conn = url.openConnection();
        
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        doc = builder.parse(conn.getInputStream());

        tfactory = TransformerFactory.newInstance();
        xform = tfactory.newTransformer();

        // that’s the default xform; use a stylesheet to get a real one
        xform.transform(new DOMSource(doc), new StreamResult(System.out));
        
        doc.getDocumentElement().normalize();
		
		//System.out.println(doc);
		
		nList = doc.getElementsByTagName("results");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
	 
			nNode = nList.item(temp);
	 
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());
	 
			for (int temp2=0; temp2 < nNode.getChildNodes().getLength(); temp2++ ){
				
				sortArray.add(temp2, ""+nNode.getChildNodes().item(temp2).getAttributes().getNamedItem("fulltext").getNodeValue());
				
			}
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
					eElement = (Element) nNode;
				
			}
			
		}
		
		return sortArray;
		
    }
    
}
