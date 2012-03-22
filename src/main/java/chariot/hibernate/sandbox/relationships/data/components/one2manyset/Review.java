package chariot.hibernate.sandbox.relationships.data.components.one2manyset;

import chariot.hibernate.sandbox.common.BookRating;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 4:11:44 PM
 */
@Embeddable
@Table(name = "REVIEW_COMP_COLLECTION")
public class Review {
   
    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    @NotNull
    private BookRating rating;

    @Size(min = 5, max = 1000)
    private String review;

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
}
