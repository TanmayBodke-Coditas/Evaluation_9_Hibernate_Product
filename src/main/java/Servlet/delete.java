package Servlet;

import DAO.CustDAO;
import Entity.Products;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/delete")
public class delete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Configuration cfg = new Configuration().configure();
    static SessionFactory factory = cfg.buildSessionFactory();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pId = Integer.parseInt(request.getParameter("pId"));

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Get the product by id
            Products product = session.get(Products.class, pId);

            if (product != null) {
                // Delete the product from the database
                session.delete(product);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        HttpSession session1 = request.getSession(false);
        int id = (int)session1.getAttribute("custId");
        int bill=0;

        List<Products> product = CustDAO.selectAllSelectedProducts(id);
        //System.out.println("jiiiiiiiiiiiiiiii");
        // List<Product> products = CustDAO.selectAllProducts();
        request.setAttribute("products", product);

        for (Products p: product) {
            bill = bill +  p.getpPrice()*p.getpQuantity();
        }
        request.setAttribute("bill",bill);
        // Redirect to the list products page
        RequestDispatcher dispatcher = request.getRequestDispatcher("show-cart.jsp");
        dispatcher.forward(request, response);

       //response.sendRedirect("listAdded");

    }


}
