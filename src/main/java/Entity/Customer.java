package Entity;



import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cId;
    private String cName;
    private String cEmail;
    private String  cPassword;
    private int cPhone;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Products> products;


    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public int getcPhone() {
        return cPhone;
    }

    public void setcPhone(int cPhone) {
        this.cPhone = cPhone;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public Customer(String cName, String cEmail, String cPassword, int cPhone) {
        this.cName = cName;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cPhone = cPhone;
    }

    public Customer(String cName, String cEmail, String cPassword, int cPhone, List<Products> products) {
        this.cName = cName;
        this.cEmail = cEmail;
        this.cPassword = cPassword;
        this.cPhone = cPhone;
        this.products = products;
    }

    public Customer() {
    }
}

