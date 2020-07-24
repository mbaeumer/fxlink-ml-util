package se.mbaeumer.fxlink.ml;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SuggestionServiceTest {

    @Test
    public void shouldCreateSuggestions() {
        SuggestionService suggestionService = new SuggestionService();
        DataService dataService = new DataService();

        Link link = new Link("", "https://www.baeldung.com/jackson-kotlin", "");
        List<Suggestion> suggestions = suggestionService.createSuggestions(dataService.prepareData(createLinkList()), link);
        Assert.assertTrue(suggestions.stream().filter(suggestion -> "Kotlin".equals(suggestion.getCategory())).findFirst().isPresent());

    }

    private List<Link> createLinkList(){
        List<Link> links = new ArrayList<>();

        links.add(createLink("https://www.facebook.com/buzzfeedtasty/videos/276402623429801/", 1, "Recipes"));
        links.add(createLink("https://www.facebook.com/buzzfeedtasty/videos/276402623429834/", 1, "Recipes"));
        links.add(createLink("https://www.buzzfeed.com/terripous/natural-sleep-remedies", 1, "Recipes"));
        links.add(createLink("https://www.indusiadesign.se/", 2, "product tips"));
        links.add(createLink("https://blog.frankel.ch/write-extension-functions-own-classes-kotlin/", 3, "Kotlin"));
        links.add(createLink("https://medium.com/recombee-blog/machine-learning-for-recommender-systems-part-1-algorithms-evaluation-and-cold-start-6f696683d0ed", 4,
                "machine learning"));
        return links;
    }

    private Link createLink(final String url, final int id, final String categoryName){
        Link link = new Link("", url, "");
        link.setCategory(createCategory(id, categoryName));
        return link;
    }

    private Category createCategory(final int id, final String name){
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        return category;
    }
}