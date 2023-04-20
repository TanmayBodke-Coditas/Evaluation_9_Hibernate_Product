import Entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        Product product = new Product("Pizza",100,200);
        Product product1 = new Product("Coffee",50,1000);

        s.save(product);
        s.save(product1);

        tx.commit();
        s.close();

    }
}
