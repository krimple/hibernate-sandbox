package chariot.hibernate.sandbox.embedding;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by krimple @ Sep 15, 2010 12:48:17 PM
 */
@Embeddable
public class ReviewIdentifier implements Serializable {

    @Column(name="person_id")
    private Long personId;

    @Column(name="review_date")
    @Temporal(TemporalType.DATE)
    private Date reviewDate;
    
    private static final long serialVersionUID = 6634618045482200950L;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }


}
