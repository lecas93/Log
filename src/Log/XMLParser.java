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

	private static int mode, level;
	private static double fileSize;

	private static final int DEFAULT_MODE = Mode.PRODUCTION.ordinal();
	private static final int DEFAULT_LEVEL = Level.INFO.ordinal();
	private static final double DEFAULT_SIZE = 1024;

	public static void loadInfo() {
		try {
			String filepath = "config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node config = doc.getElementsByTagName("config").item(0);

			NodeList list = config.getChildNodes();
			System.out.println("list length = " + list.getLength());

			for (int i = 0; i < list.getLength(); i++) {
				System.out.println("i = " + i);

				Node node = list.item(i);

				if ("mode".equals(node.getNodeName())) {
					System.out.println("entro al if mode");
					switch (node.getTextContent()) {
					case "debug":
						mode = 0;
						break;
					case "production":
						mode = 1;
						break;
					default:
						mode = DEFAULT_MODE;
						break;
					}
				} else {
					System.out.println("no entro al if mode");
					mode = DEFAULT_MODE;
				}

				if ("level".equals(node.getNodeName())) {
					System.out.println("entro al if level");
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
						level = DEFAULT_LEVEL;
						break;
					}
				} else {
					System.out.println("no entro al if level");
					level = DEFAULT_LEVEL;
				}

				if ("filesize".equals(node.getNodeName())) {
					System.out.println("entro al if filesize");
					fileSize = Double.parseDouble(node.getTextContent());
					if (fileSize < 0)
						fileSize = DEFAULT_SIZE;
				} else {
					System.out.println("no entro al filesize");
					fileSize = DEFAULT_SIZE;
				}
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			fileSize = DEFAULT_SIZE;
		} catch (Exception e) {
			e.printStackTrace();
			setDefaultValues();
		}
	}

	private static void setDefaultValues() {
		mode = DEFAULT_MODE;
		level = DEFAULT_LEVEL;
		fileSize = DEFAULT_SIZE;
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
