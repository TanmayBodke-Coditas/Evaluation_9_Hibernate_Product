package Hibernate_Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayDeque;
import java.util.Set;

@Entity
public class Student {
    @Id
    private int id;
    private String name;

    private int phno;

    @OneToMany(mappedBy = "student")
    Set<Address> addresses;

}
