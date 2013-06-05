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
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import additional.ComplexityEnum;
import additional.Field;
import additional.FunctionPointEnum;
import additional.Type;

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
			Element valueElement = doc.createElement("NodeValue");
			valueElement.setTextContent(f.getValue().toString());
			e.appendChild(valueElement);
			
			addToXML(doc, e, f.getChildren());
			
			parent.appendChild(e);
			
		}
		
		
	}
	
	private Field createField(final Element node, final Field parent, final Project proj) {
		
		Type type = Type.valueOf(node.getAttribute("Type"));
		
		Object fieldValue = null;
		
		// First node is always The Content/Value of the field
		Element valueNode = (Element) node.getElementsByTagName("NodeValue").item(0);
		if (valueNode != null) {
			String value = valueNode.getTextContent();
			if (value == null) {
				value = " ";
			}
			if (type == Type.String || type == Type.Text) {
				
				fieldValue = value;
			}
			else if (type == Type.Float) {
				fieldValue = Float.parseFloat(value);
			}
			else if (type == Type.Integer) {
				fieldValue = Integer.parseInt(value);
			}
			else if (type == Type.Null) {
				fieldValue = (null);
			}
			else if (type == Type.ComplexityEnum) {
				fieldValue = (ComplexityEnum.valueOf(value));
			}
			else if (type == Type.FunctionPointEnum) {
				fieldValue = (FunctionPointEnum.valueOf(value));
			}
		}
		else {
			System.out.println("XML Element has no nodeValue. This should never happen. Check input file.");
		}
		
		
		Field field = new Field(node.getNodeName().replace("_", " "), type,
				Boolean.parseBoolean(node.getAttribute("Editable")), proj, fieldValue);
		
		
		// add everything as child Fields, except the ValueNodes
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {
			
			Node n = node.getChildNodes().item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE && !n.getNodeName().equals("NodeValue")) {
				field.addChild(createField((Element) n, field, proj));
			}
			
		}
		
		return field;
	}
	
	@Override
	public void importProject(final String filename, final Project emptyProject) {
		ArrayList<Field> fields = new ArrayList<Field>();
		File fXmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			Element projectNode = (Element) doc.getElementsByTagName("Project").item(0);
			
			if (projectNode == null) {
				System.out.println("Import failed. XML does not contaion Project node.");
				return;
			}
			
			for (int i = 0; i < projectNode.getChildNodes().getLength(); i++) {
				
				Node node = projectNode.getChildNodes().item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					fields.add(createField((Element) node, null, emptyProject));
				}
				
			}
			
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("XML Import failed.");
			return;
		}
		
		emptyProject.setProjectFields(fields);
		
		return;
	}
}
