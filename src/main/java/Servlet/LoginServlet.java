package Servlet;

import DAO.CustDAO;
import Entity.Customer;
import Entity.Product;
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

@WebServlet("/login")
public class LoginServlet  extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();


        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        List<Customer> cutomers = s.createQuery("from Customer").list();
        tx.commit();
        s.close();

        if (email.equals("admin") && password.equals("admin")) {
            System.out.println("admin");
            RequestDispatcher rd = request.getRequestDispatcher("admin-page.jsp");
            rd.forward(request, response);
        }else{
            for (Customer customer : cutomers) {
                if (email.equals(customer.getcEmail()) && password.equals(customer.getcPassword())) {


                    HttpSession session = request.getSession();
                    session.setAttribute("custId", customer.getcId());

                    int id = customer.getcId();

                    List<Customer> customers = CustDAO.selectCustomer(id);
                    //System.out.println("jiiiiiiiiiiiiiiii");
                    // List<Product> products = CustDAO.selectAllProducts();
                    request.setAttribute("customer", customers);

                    //session.setAttribute("email", customer.getcEmail());
                    RequestDispatcher rd = request.getRequestDispatcher("customer-page.jsp");
                    rd.forward(request, response);
                }
            }
        }
    }
}