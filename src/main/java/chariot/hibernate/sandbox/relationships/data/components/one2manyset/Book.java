package chariot.hibernate.sandbox.relationships.data.components.one2manyset;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by krimple @ Sep 12, 2010 4:09:39 PM
 */
@Entity
@Table(name = "BOOK_WITH_COMP_COLLECTION")
public class Book {

    @Id
    @GeneratedValue(generator="bookCompSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="bookCompSeq", sequenceName = "BOOK_WITH_VERSION_SEQ")
    private Long id;

    private String isbn;

    private String title;

    /** Notice that we can have multiple reviews, each review is unique, but we don't have
     * 
     */
    @ElementCollection(targetClass = Review.class)
    @JoinTable(name = "REVIEW_COMPONENT", joinColumns = {
            @JoinColumn(name = "book_id") }
    )
    private Set<Review> reviews = new HashSet<Review>();

    /** Important - put this on one side of the relationship
     * and have it add to BOTH sides...
     * @param review The review to add
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
