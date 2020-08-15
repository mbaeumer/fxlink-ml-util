package se.mbaeumer.fxlink.ml;

import java.util.*;
import java.util.stream.Collectors;

public class SuggestionService {

    public List<Suggestion> createSuggestions(final Map<String, List<CategoryCount>> map, final Link link){
        List<Suggestion> suggestions = new ArrayList<>();
        UrlService urlService = new UrlService();
        String url = urlService.withoutProtocol(link.getURL());
        url = urlService.withoutPrefix(url);
        String[] urlParts = urlService.getUrlParts(url);

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
        // .sorted(Comparator.comparing(User::getName, UserNameComparator.INSTANCE))
        return suggestions.stream().sorted(Comparator.comparing(Suggestion::getCount).reversed()).collect(Collectors.toList());
    }
}
