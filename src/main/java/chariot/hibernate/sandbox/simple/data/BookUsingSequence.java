package chariot.hibernate.sandbox.simple.data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 11:36:52 AM
 */
@Entity
@Table(name = "BOOK_WITH_SEQUENCE")
public class BookUsingSequence {

    @Id
    @GeneratedValue(generator="bookSequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="bookSequence", sequenceName = "BOOK_SEQ")
    private Long id;

    private String isbn;

    private String title;

    @Column(precision = 6, scale = 2)
    private BigDecimal listPrice;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
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

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}
