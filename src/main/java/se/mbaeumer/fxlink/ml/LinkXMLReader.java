package se.mbaeumer.fxlink.ml;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LinkXMLReader {
	public static final String CATEGORIES = "categories";
	public static final String CATEGORY = "category";
	public static final String LINKS = "links";
	public static final String LINK = "link";
	public static final String TAGS = "tags";
	public static final String TAG = "tag";
	public static final String LINKTAGS = "linktags";
	public static final String LINKTAG = "linktag";
	public static final String IMPORTITEMS = "importitems";
	public static final String IMPORTITEM = "importitem";
	
	private XMLInputFactory xmlInputFactory;
	private XMLEventReader xmlEventReader;
	private FileInputStream fileInputStream;
	private String configFile;
	
	private List<Category> categories = new ArrayList<>();
	private List<Link> links = new ArrayList<>();

	public LinkXMLReader(String fileName) throws FileNotFoundException, XMLStreamException {
		this.configFile = fileName;
		this.init();
	}
	
	private void init() throws FileNotFoundException, XMLStreamException {
		this.xmlInputFactory = XMLInputFactory.newInstance();
		// Setup a new eventReader
		this.fileInputStream = new FileInputStream(configFile);
		this.xmlEventReader = this.xmlInputFactory
				.createXMLEventReader(this.fileInputStream);
	}
	
	public void readDataFromFile() throws XMLStreamException, FileNotFoundException {
		while (this.xmlEventReader.hasNext()) {
			XMLEvent event = this.xmlEventReader.nextEvent();
			if (event.isStartElement()) {
				StartElement startElement = event.asStartElement();
				if (startElement.getName().getLocalPart() == (CATEGORIES)) {
					this.categories = new ArrayList<Category>();
				}else if (startElement.getName().getLocalPart() == (CATEGORY)) {
					Category c = new Category();
					c.setId(new Integer(startElement.getAttributeByName(
							new QName("id")).getValue()));
					c.setName(startElement.getAttributeByName(
							new QName("name")).getValue());
					c.setDescription(startElement.getAttributeByName(
							new QName("description")).getValue());

					// TODO: Use constants instead of hard-coded strings
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date = new Date();

					try {
						date = df.parse(startElement.getAttributeByName(
								new QName("created")).getValue());
						c.setCreated(date);
						date = df.parse(startElement.getAttributeByName(
								new QName("lastUpdated")).getValue());
						c.setLastUpdated(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					this.categories.add(c);

				}else if (startElement.getName().getLocalPart() == (LINKS)) {
					this.links = new ArrayList<Link>();
				}else if (startElement.getName().getLocalPart() == (LINK)) {
					String title = startElement.getAttributeByName(
							new QName("title")).getValue();
					String url = startElement.getAttributeByName(
							new QName("url")).getValue();
					String description = startElement.getAttributeByName(
							new QName("description")).getValue();
					Link link = new Link(title, url, description);
					
					link.setId(new Integer(startElement.getAttributeByName(
							new QName("id")).getValue()));
	
					// TODO: Use constants instead of hard-coded strings
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Date date = new Date();

					try {
						date = df.parse(startElement.getAttributeByName(
								new QName("created")).getValue());
						link.setCreated(date);
						date = df.parse(startElement.getAttributeByName(
								new QName("lastUpdated")).getValue());
						link.setLastUpdated(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					Attribute attribute = startElement.getAttributeByName(
							new QName("categoryid"));
					if (attribute != null){
						String categoryId = attribute.getValue();
						
						if (categoryId == null || categoryId.equals("")) {
							link.setCategory(null);
						}else{
							Category category = new Category();
							category.setId(new Integer(categoryId));
							link.setCategory(category);
						}
					}else{
						link.setCategory(null);
					}
					
					this.links.add(link);
				}

			}
		}
	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<Link> getLinks() {
		return links;
	}
}