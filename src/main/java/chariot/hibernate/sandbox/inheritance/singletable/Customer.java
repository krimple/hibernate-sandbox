package chariot.hibernate.sandbox.inheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by krimple @ Sep 15, 2010 10:47:23 AM
 */
@Entity
@Table(name="CUSTOMER_SINGLE_TABLE")
@DiscriminatorValue("CUSTOMER")
public class Customer extends Person{

    @Column(name="reward_amount")
    private BigDecimal rewardAmount;

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(BigDecimal rewardAmount) {
        this.rewardAmount = rewardAmount;
    }
}
