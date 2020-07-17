package se.mbaeumer.fxlink.ml;

public class CategoryCount {
    private String category;
    private String count;

    public String getCategory() {
        return category;
    }

    public String getCount() {
        return count;
    }

    public CategoryCount(String category, String count) {
        this.category = category;
        this.count = count;
    }
}
