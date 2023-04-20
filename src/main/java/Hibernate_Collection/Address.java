package Hibernate_Collection;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {

    @Id
    private int hno;
    private String hname;
    private String city;
    private int pin;

    @ManyToOne
    private Student student;
}
