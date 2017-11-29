package Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {	
	private static XMLParser xmlParser;

	private static int mode, level;
	private static double fileSize;
	private static String emailTO = "", emailFROM = "", emailPASS = "";

	private static final int DEFAULT_MODE = Mode.PRODUCTION.ordinal();
	private static final int DEFAULT_LEVEL = Level.INFO.ordinal();
	private static final double DEFAULT_SIZE = 1024;
	
	private XMLParser() {}
	
	public static synchronized XMLParser getInstance() {
		if (xmlParser == null) {
			xmlParser = new XMLParser();
			loadInfo();
		}
		return xmlParser;
	}

	private static void loadInfo() {
		setDefaultValues();
		try {
			String filepath = "config.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node config = doc.getElementsByTagName("config").item(0);

			NodeList list = config.getChildNodes();

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);

				if ("mode".equals(node.getNodeName())) {
					switch (node.getTextContent()) {
					case "debug":
						mode = 0;
						break;
					case "production":
						mode = 1;
						break;
					default:
						mode = DEFAULT_MODE;
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
						level = DEFAULT_LEVEL;
					}
				}

				if ("filesize".equals(node.getNodeName())) {
					fileSize = Double.parseDouble(node.getTextContent());
					if (fileSize < 0)
						fileSize = DEFAULT_SIZE;
				}

				if ("emailTO".equals(node.getNodeName())) {
					emailTO = node.getTextContent();
				}
				if ("emailFROM".equals(node.getNodeName())) {
					emailFROM = node.getTextContent();
				}
				if ("emailPASS".equals(node.getNodeName())) {
					emailPASS = node.getTextContent();
				}
			}
		} catch (NumberFormatException nfe) {
			System.out.println(nfe.getMessage());
			fileSize = DEFAULT_SIZE;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Un error ha ocurrido en la lectura del archivo XML!\nSe han cargado los valores por defecto!");
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

	public static String[] getEmailInfo() {
		String[] emailInfo = { emailTO, emailFROM, emailPASS };
		return emailInfo;
	}

}
