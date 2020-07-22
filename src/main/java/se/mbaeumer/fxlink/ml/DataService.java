package se.mbaeumer.fxlink.ml;

import java.util.*;

public class DataService {

    public Map prepareData(final List<Link> links){
        UrlSevice urlSevice = new UrlSevice();
        Map<String, List<CategoryCount>> hash = new HashMap();
        for (Link link : links){
            String url = urlSevice.withoutProtocol(link.getURL());
            url = urlSevice.withoutPrefix(url);
            String[] urlParts = urlSevice.getUrlParts(url);
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

    private List<CategoryCount> initList(final String categoryName){
        List<CategoryCount> categoryCounts = new ArrayList<>();
        categoryCounts.add(new CategoryCount(categoryName, 1));
        return categoryCounts;
    }
}