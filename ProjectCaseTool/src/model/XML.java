package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import additional.Field;

public class XML implements IImport, IExport {
	
	@Override
	public void exportProject(final String filename, final Project project) {
		ArrayList<Field> fields = project.getFields();
		
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("XML Export failed.");
			return;
		}
		
		// Project element
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Project");
		doc.appendChild(rootElement);
		
		
		addToXML(doc, rootElement, fields);
		
		
		// write to file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
		} catch (TransformerConfigurationException e) {
			System.out.println("XML Export failed.");
			return;
		}
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filename));
		
		// StreamResult result = new StreamResult(System.out);
		
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			System.out.println("XML Export failed.");
			return;
		}
		
	}
	
	private void addToXML(final Document doc, final Element parent, final ArrayList<Field> fields) {
		
		for (Field f : fields) {
			
			Element e = doc.createElement(f.getName().replace(" ", "_"));
			
			// Attributes
			e.setAttribute("Type", f.getType().toString());
			e.setAttribute("Editable", f.getEditable().toString());
			
			
			// Content
			e.setTextContent(f.getValue().toString());
			
			addToXML(doc, e, f.getChildren());
			
			parent.appendChild(e);
			
		}
		
		
	}
	
	@Override
	public ArrayList<Field> importProject(final String filename) {
		File fXmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			
			for (int i = 0; i < doc.getChildNodes().getLength(); i++) {
				Element n = (Element) doc.getChildNodes().item(i);
				Field f = new Field(name, type, editable, owner, value);
				
				
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("XML Import failed.");
			return null;
		}
		
		return null;
	}
	
}
