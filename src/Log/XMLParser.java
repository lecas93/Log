package Log;

import java.io.File;
import java.io.IOException;
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
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

	private static int mode = 1, level = 1;
	private static double fileSize = 1024;

	public static void parser() {
		try {
			String filepath = "file.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Get the root element
			Node logger = doc.getFirstChild();

			// Get the staff element , it may not working if tag has spaces, or
			// whatever weird characters in front...it's better to use
			// getElementsByTagName() to get it directly.
			// Node staff = company.getFirstChild();

			// Get the staff element by tag name directly
			Node config = doc.getElementsByTagName("config").item(0);

			// loop the staff child node
			NodeList list = config.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {

				Node node = list.item(i);

				if ("mode".equals(node.getNodeName())) {
					mode = (node.getTextContent() == "production" ? 1 : 0);

					switch (node.getTextContent()) {
					case "debug":
						mode = 0;
						break;
					case "production":
						mode = 1;
						break;
					default:
						mode = 1;
					}
				}

				if ("level".equals(node.getNodeName())) {
					switch (node.getTextContent()) {
					case "all":
						level = 0;
						break;
					case "info":
						level = 1;
						break;
					case "warning":
						level = 2;
						break;
					case "error":
						level = 3;
						break;
					case "off":
						level = 4;
						break;
					default:
						level = 1;
						break;
					}
				}

				if ("filesize".equals(node.getNodeName())) {
					fileSize = Double.parseDouble(node.getTextContent());
				} else {
					fileSize = 1024;
				}

			}

			/*
			 * // write the content into xml file TransformerFactory transformerFactory =
			 * TransformerFactory.newInstance(); Transformer transformer =
			 * transformerFactory.newTransformer(); DOMSource source = new DOMSource(doc);
			 * StreamResult result = new StreamResult(new File(filepath));
			 * transformer.transform(source, result);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			setDefaultValues();
		}
	}

	private static void setDefaultValues() {
		mode = 1;
		level = 1;
		fileSize = 1024;
	}

	public static int getLevel() {
		return level;
	}

	public static int getMode() {
		return mode;
	}

	public static double getFileSize() {
		return fileSize;
	}

}
