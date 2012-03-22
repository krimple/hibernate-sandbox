package chariot.hibernate.sandbox.nonset;

import javax.persistence.*;

/**
 * Created by krimple @ Sep 16, 2010 10:04:27 AM
 */
@Entity
public class Purchase {
    @Id
    @GeneratedValue(generator="purchaseSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="purchaseSeq", sequenceName = "BOOK_WITH_VERSION_SEQ")
    Long id;

    @Column(length = 100)
    private String widget;

    @ManyToOne
    private Customer customer;

    private int quantity;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getWidget() {
        return widget;
    }

    public void setWidget(String widget) {
        this.widget = widget;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
