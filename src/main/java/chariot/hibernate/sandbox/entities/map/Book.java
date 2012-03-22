package chariot.hibernate.sandbox.entities.map;


import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krimple @ Sep 12, 2010 4:09:39 PM
 */
@Entity
@Table(name = "BOOK_WITH_MAP")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(generator="bookCompSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="bookCompSeq", sequenceName = "BOOK_WITH_VERSION_SEQ")
    private Long id;

    private String isbn;

    private String title;

    /** Notice that we can have multiple reviews, each review is unique, but we don't have
     * 
     */
    @OneToMany
    @MapKey(name = "id")
    //@MapKey(name = "randomVal")
    @JoinColumn(name = "book_id")
    private Map<Long, Review> reviews = new HashMap<Long, Review>();

    /** Important - put this on one side of the relationship
     * and have it add to BOTH sides...
     * @param review The review to add
     */
    public void addReview(Review review) {
        reviews.put(review.getId(), review);
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

    public Map<Long, Review> getReviews() {
        return reviews;
    }

    public void setReviews(Map<Long, Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
