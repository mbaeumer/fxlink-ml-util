package se.mbaeumer.fxlink.ml;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        LinkXMLReader reader = new LinkXMLReader("/Users/martinbaumer/Documents/fxlink-backup-20200705.xml");


        try {
            reader.readDataFromFile();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Stats:");
        System.out.println("Total number of links: " + reader.getLinks().size());
        System.out.println("Total number of categories: " + reader.getCategories().size());

        // only links with categories



    }
}
