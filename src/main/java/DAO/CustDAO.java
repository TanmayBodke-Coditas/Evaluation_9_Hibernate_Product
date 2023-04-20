package DAO;

import Entity.Customer;
import Entity.Product;
import Entity.Products;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class CustDAO {
    static Configuration cfg = new Configuration().configure();
    static SessionFactory factory = cfg.buildSessionFactory();

    public static void insertCustomer(Customer customer) {

        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        s.save(customer);
        tx.commit();
        s.close();
    }

    public static List<Product> selectAllProducts() {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        List<Product> product = s.createQuery("from Product").list();
        System.out.println("hiiii from dao");
        System.out.println(product);
        tx.commit();
        s.close();
        return product;
    }

    public static void insertProduct(Products products, int id, int qty) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        Query query = s.createQuery("from Products where customer.cId=:customerId");
        query.setParameter("customerId", id);
        List<Products> products1 = query.getResultList();
        if (products1.isEmpty()) {
            s.save(products);
            tx.commit();
        } else {
            System.out.println("Hi from update");
            products = s.get(Products.class, id);
            products.setpQuantity(qty);
            System.out.println(products);

            s.saveOrUpdate(products);
            tx.commit();

        }
        s.close();


    }


    public static List<Products> selectAllSelectedProducts(int id) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        //List<Products> products =s.createQuery("from Products").list();
        //System.out.println("hiiii from dao");

        Query query = s.createQuery("from Products where customer.cId=:customerId");
        query.setParameter("customerId", id);
        List<Products> products = query.getResultList();
        System.out.println(products);

        tx.commit();
        s.close();
        return products;
    }

    public static Products selectProduct(String productId, int customerId) {
        Session session = factory.openSession();
        Transaction tx = null;
        Products product = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Products WHERE pName = :productId AND customer_cId = :customerId";
            Query query = session.createQuery(hql);
            query.setParameter("productId", productId);
            query.setParameter("customerId", customerId);
            List<Products> results = query.getResultList();
            if (!results.isEmpty()) {
                product = results.get(0);
                System.out.println("hii from not empty");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return product;
    }

    public static void updateProductQuantity(String pname, int quantity) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "UPDATE Product SET qty = :quantity WHERE pName = :pName";
            Query query = session.createQuery(hql);
            query.setParameter("quantity", quantity);
            query.setParameter("pName", pname);
            int rowsAffected = query.executeUpdate();
            System.out.println("hello from update");
            tx.commit();
        } catch (Exception e) {

        }
    }

    public static List<Customer> selectCustomer(int id) {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        String hql = "FROM Customer WHERE cId = :CId ";
        Query query = s.createQuery(hql);
        query.setParameter("CId", id);
        //query.setParameter("customerId", customerId);
        List<Customer> customer = query.getResultList();

        tx.commit();
        s.close();
        return customer;
    }
}
