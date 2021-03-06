package chariot.hibernate.sandbox.inheritance.joined;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by krimple @ Sep 15, 2010 10:49:27 AM
 */
@Entity(name = "EMPLOYEE_JOINED")
@Table(name = "EMPLOYEE_JOINED")
public class Employee extends Person {

    @Temporal(TemporalType.DATE)
    private Date reviewDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    
}
