package chariot.hibernate.sandbox.nonset;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by krimple @ Sep 16, 2010 10:04:14 AM
 */
@Entity
public class Customer {

    @Id    
    @GeneratedValue(generator="customerSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="customerSeq", sequenceName = "BOOK_WITH_VERSION_SEQ")
    private Long id;

    private String name;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")    
    @OrderColumn(name="purchase_number")
    private Collection<Purchase> purchases = new ArrayList<Purchase>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    protected void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        purchase.setCustomer(this);
    }
}
