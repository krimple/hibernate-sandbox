package chariot.hibernate.sandbox.embedding;

import javax.persistence.*;

/**
 * Created by krimple @ Sep 15, 2010 12:50:04 PM
 */
@Entity
@Table(name = "REVIEW_EMBEDDED")
public class ReviewEmbed {

    @EmbeddedId    
    private ReviewIdentifier id;

    @Column(name="review_score")
    int reviewScore;

    public ReviewIdentifier getId() {
        return id;
    }

    private void setId(ReviewIdentifier id) {
        this.id = id;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }


}
