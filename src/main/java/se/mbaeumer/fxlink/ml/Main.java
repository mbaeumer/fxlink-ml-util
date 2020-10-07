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
        categorizedLinks = dataService.matchCategories(categorizedLinks, reader.getCategories());
        Map<String, List<CategoryCount>> hashMap = dataService.prepareData(categorizedLinks);

        SuggestionService suggestionService = new SuggestionService();
        List<Suggestion> suggestions = suggestionService.createSuggestions(hashMap, new Link("", "https://www.adlibris.com/se/bok/beginning-ethical-hacking-with-python-9781484225400", ""));

        for (Suggestion suggestion:suggestions){
            System.out.println(suggestion.toString());
        }

    }

}
