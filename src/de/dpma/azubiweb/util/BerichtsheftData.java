package de.dpma.azubiweb.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.io.DOMReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Datenmodel für Berichtsheft (Seite und DB) Wandelt Daten in XML oder die XML
 * zum Lesen um
 * 
 * @author VIT Student
 *
 */
public class BerichtsheftData {

	public static String[] getDataFromXML(String value) {
		/**
		 * 0: 5-Tage BH 1: Wochen BH
		 */
		int art = -1;
		String[] weekValues = null;
		if (value != null) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(value));

				Document doc = db.parse(is);

				Element root = doc.getDocumentElement();
				art = Integer.valueOf(root.getAttribute("art"));
				NodeList childs = root.getChildNodes();

				if (art == 0) {
					weekValues = new String[5];
				} else {
					weekValues = new String[1];
				}
				for (int i = 0; i < childs.getLength(); i++) {
					Node temp = childs.item(i);
					if (temp.getNodeType() == Node.ELEMENT_NODE) {
						Element elementItem = (Element) temp;
						int id = Integer.valueOf(elementItem.getAttribute("id"));
						weekValues[id] = elementItem.getTextContent();
					}
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return weekValues;
		}
		return null;
	}

	public static String getXMLFromData(String[] weekValues, int week) {
		int art = -1;
		switch (weekValues.length) {
		case 1:
			art = 1;
			break;
		case 5:
			art = 0;
			break;
		default:
			art = -1;
			break;
		}
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;

			builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("report");

			root.setAttribute("week", "" + week);
			root.setAttribute("art", "" + art);
			doc.appendChild(root);
			System.out.println(art);
			if (art == 0) {
				// 5 Tage
				for (int i = 0; i < weekValues.length; i++) {
					Element tDays = doc.createElement("item");
					tDays.setAttribute("id", "" + i);
					tDays.setTextContent(weekValues[i]);
					root.appendChild(tDays);
				}
			} else if (art == 1) {
				// Woche
				Element tWeek = doc.createElement("item");
				tWeek.setAttribute("id", "" + 5);
				tWeek.setTextContent(weekValues[0]);
				root.appendChild(tWeek);
			} else {
				return null;
			}

			DOMReader reader = new DOMReader();
			org.dom4j.Document doc2 = reader.read(doc);
			return doc2.asXML().toString();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
