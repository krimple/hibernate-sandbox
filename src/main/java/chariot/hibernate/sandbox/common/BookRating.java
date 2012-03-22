package chariot.hibernate.sandbox.common;

/**
 * Created by krimple @ Sep 12, 2010 4:12:31 PM
 */
public enum BookRating {
    POOR("Poor"),
    SUBAVERAGE("Sub-par"),
    AVERAGE("Average"),
    ABOVEAVERAGE("Above Average"),
    EXCELLENT("Excellent");

    BookRating(String label) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }
}
