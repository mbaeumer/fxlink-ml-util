package se.mbaeumer.fxlink.ml;

import java.util.*;

public class SuggestionService {

    private Map<String, List<CategoryCount>> data;

    public List<Suggestion> doSomething(final Map<String, List<CategoryCount>> map, final Link link){
        List<Suggestion> suggestions = new ArrayList<>();
        data = map;
        UrlSevice urlSevice = new UrlSevice();
        String url = urlSevice.withoutProtocol(link.getURL());
        url = urlSevice.withoutPrefix(url);
        List<String> urlParts = Arrays.asList(urlSevice.getUrlParts(url));

        for (String word : urlParts){
            List<CategoryCount> categoryCounts = map.get(word);
            if (categoryCounts == null){
                continue;
            }
            categoryCounts.forEach(categoryCount ->{
                    Optional<Suggestion> matchingSuggestion = suggestions
                            .stream()
                            .filter(suggestion -> suggestion.getCategory().equalsIgnoreCase(categoryCount.getCategory()))
                            .findFirst();
                    if (matchingSuggestion.isPresent()){
                        matchingSuggestion.get().increaseCount();
                    }else {
                        suggestions.add(new Suggestion(categoryCount.getCategory(), 1));
                    }
            });
        }
        // go through each word
        //   word exists in map?
        //   if yes:
        //

        //   if no:

        return suggestions;
    }

    public void doOp(String word){

    }
}
