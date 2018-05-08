package de.dpma.azubiweb.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BerichtsheftData {
	/**
	 * 0: 5-Tage BH 1: Wochen BH
	 */
	private int art = -1;
	private int week = -1;
	private String[] weekValues;

	public void setXMLdata(String value) {
		if (value != null) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();

				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(value));

				Document doc = db.parse(is);

				Element root = doc.getDocumentElement();
				this.art = Integer.valueOf(root.getAttribute("art"));
				this.week = Integer.valueOf(root.getAttribute("week"));
				NodeList childs = root.getChildNodes();

				if (art == 0) {
					this.weekValues = new String[5];
				} else {
					this.weekValues = new String[1];
				}
				for (int i = 0; i < childs.getLength(); i++) {
					Node temp = childs.item(i);
					if (temp.getNodeType() == Node.ELEMENT_NODE) {
						Element elementItem = (Element) temp;
						int id = Integer.valueOf(elementItem.getAttribute("id"));
						this.weekValues[id] = elementItem.getTextContent();
					}
				}
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String getXMLdata() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;

			builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element root = doc.createElement("report");
			root.setAttribute("week", "" + week);
			root.setAttribute("art", "" + art);
			if (art == 0) {

			} else if (art == -1) {

			} else {
				return null;
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String[] getWeekValues() {
		return weekValues;
	}

	public void setWeekValues(String[] weekValues) {
		this.weekValues = weekValues;
		switch (weekValues.length) {
		case 1:
			this.art = 1;
			break;
		case 5:
			this.art = 0;
			break;
		default:
			this.art = -1;
			break;
		}
	}
}
