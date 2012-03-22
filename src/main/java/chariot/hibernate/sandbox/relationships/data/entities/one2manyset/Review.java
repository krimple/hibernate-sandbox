package chariot.hibernate.sandbox.relationships.data.entities.one2manyset;

import chariot.hibernate.sandbox.common.BookRating;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 4:11:44 PM
 */
@Entity
@Table(name = "REVIEW_O2MSET")
@org.hibernate.annotations.Table(appliesTo = "REVIEW_O2MSET", indexes = @Index(name="fk_review_book_id", columnNames = "book_id"))
public class Review {

    @Id
    @GeneratedValue(generator = "reviewo2m", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reviewo2m", sequenceName = "REVIEW_O2MSET_SEQ")
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private BookRating rating;

    @Size(min = 5, max = 1000)
    private String review;

    @ManyToOne(optional = false)
    private Book book;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
