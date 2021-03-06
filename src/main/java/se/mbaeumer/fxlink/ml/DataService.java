package se.mbaeumer.fxlink.ml;

import java.util.*;

public class DataService {

    public Map prepareData(final List<Link> links){
        UrlService urlService = new UrlService();
        Map<String, List<CategoryCount>> hash = new HashMap();
        for (Link link : links){
            String url = urlService.withoutProtocol(link.getURL());
            url = urlService.withoutPrefix(url);
            String[] urlParts = urlService.getUrlParts(url);
            for (String word : urlParts){
                List<CategoryCount> categoryCounts = hash.get(word);

                if (categoryCounts == null) {
                    hash.put(word, initList(link.getCategory().getName()));
                }else{
                    Optional<CategoryCount> match = categoryCounts.stream()
                            .filter(categoryCount -> categoryCount.getCategory().equalsIgnoreCase(link.getCategory().getName()))
                            .findFirst();

                    if (match.isPresent()){
                        CategoryCount categoryCount = match.get();
                        categoryCount.increaseCount();

                    }else{
                        categoryCounts.add(new CategoryCount(link.getCategory().getName(), 1));
                    }
                }
            }
        }
        return hash;
    }

    public List<Link> matchCategories(final List<Link> links, final List<Category> categories){
        List<Link> matchedLinks = new ArrayList<>();
        for (int i = 0; i < links.size(); i++){
            Link link = links.get(i);
            Category match =
                    categories.stream()
                            .filter(category -> category.getId() == link.getCategory().getId())
                            .findFirst().orElseThrow(NullPointerException::new);
            link.setCategory(match);
            matchedLinks.add(link);

        }
        return matchedLinks;
    }

    private List<CategoryCount> initList(final String categoryName){
        List<CategoryCount> categoryCounts = new ArrayList<>();
        categoryCounts.add(new CategoryCount(categoryName, 1));
        return categoryCounts;
    }
}
