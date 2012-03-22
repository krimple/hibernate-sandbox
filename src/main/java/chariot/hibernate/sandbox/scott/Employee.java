package chariot.hibernate.sandbox.scott;

import javax.persistence.*;

/**
 * Created by krimple @ Sep 15, 2010 8:35:13 AM
 */
@Entity
@Table(name="EMP")
public class Employee {
    @Id
    @Column(name="empno")
    private Long id;

    @Column(name="ename")
    private String employeeName;

    @Column(name="job")
    private String position;

    @ManyToOne(targetEntity = Employee.class, optional = true)
    @JoinColumn(name="mgr")
    private Employee manager;

    
    
    
}
