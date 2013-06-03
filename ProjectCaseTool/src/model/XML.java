package model;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
		
		
		// staff elements
		Element staff = doc.createElement("Staff");
		rootElement.appendChild(staff);
		
		
		// write to file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
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
			
			Element e = doc.createElement(f.getName());
			
			// Attributes
			e.setAttribute("Type", f.getType().toString());
			
			// Content
			e.setTextContent(f.getValue().toString());
			
			addToXML(doc, e, f.getChildren());
			
		}
		
		
	}
	
	@Override
	public Project importProject(final String filename) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
