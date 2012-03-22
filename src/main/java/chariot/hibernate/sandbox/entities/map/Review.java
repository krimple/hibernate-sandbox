package chariot.hibernate.sandbox.entities.map;

import chariot.hibernate.sandbox.common.BookRating;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 4:11:44 PM
 */
@Entity
@Table(name = "REVIEW_MAP")
public class Review {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(generator = "reviewMapGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reviewMapGenerator", sequenceName = "REVIEW_MAP_SEQ")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    @NotNull
    private BookRating rating;

    @Size(min = 5, max = 1000)
    private String review;

    private Long randomVal;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public BookRating getRating() {
        return rating;
    }

    public void setRating(BookRating rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Long getRandomVal() {
        return randomVal;
    }

    public void setRandomVal(Long randomVal) {
        this.randomVal = randomVal;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", reviewDate=" + reviewDate +
                ", rating=" + rating +
                ", review='" + review + '\'' +
                ", randomVal=" + randomVal +
                ", book=" + book +
                "}\n";
    }
}
