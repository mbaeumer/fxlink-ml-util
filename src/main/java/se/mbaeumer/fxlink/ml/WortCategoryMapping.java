package se.mbaeumer.fxlink.ml;

import java.util.ArrayList;
import java.util.List;

public class WortCategoryMapping {
    private String word;
    private List<CategoryCount> categoryCounts;

    public String getWord() {
        return word;
    }

    public List<CategoryCount> getCategoryCounts() {
        return categoryCounts;
    }

    public WortCategoryMapping(String word) {
        this.word = word;
        this.categoryCounts = new ArrayList<>();
    }

    public WortCategoryMapping(String word, List<CategoryCount> categoryCounts) {
        this.word = word;
        this.categoryCounts = categoryCounts;
    }
}
