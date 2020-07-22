package se.mbaeumer.fxlink.ml;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        List<Link> allLinks = reader.getLinks();
        System.out.println("Stats:");
        System.out.println("Total number of links: " + reader.getLinks().size());
        System.out.println("Total number of categories: " + reader.getCategories().size());

        // only links with categories
        List<Link> categorizedLinks = allLinks.stream()
                .filter(link -> link.getCategory() != null)
                .collect(Collectors.toList());

        DataService dataService = new DataService();
        Map<String, CategoryCount> hashMap = dataService.prepareData(categorizedLinks);
        hashMap.keySet().stream().








    }
}
