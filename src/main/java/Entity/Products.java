package Entity;

import javax.persistence.*;

@Entity
public class Products {
    @Id

    private int pId;
    private String pName;
    private int pQuantity;
    private int pPrice;

    @ManyToOne
    private Customer customer;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Products( String pName, int pQuantity, int pPrice, Customer customer) {

        this.pName = pName;
        this.pQuantity = pQuantity;
        this.pPrice = pPrice;
        this.customer = customer;
    }

    public Products() {
    }

    @Override
    public String toString() {
        return "Products{" +
                "pId=" + pId +
                ", pName='" + pName + '\'' +
                ", pQuantity=" + pQuantity +
                ", pPrice=" + pPrice +
                ", customer=" + customer +
                '}';
    }
}
