package Servlet;

import DAO.CustDAO;
import Entity.Customer;
import Entity.Product;
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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static Configuration cfg = new Configuration().configure();
    static SessionFactory factory = cfg.buildSessionFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {


            case "/insertCustomer":
                insertCustomer(request, response);
                break;

            case "/insertProduct":
                //insertProduct(request, response);
                break;
            case "/listProducts":
                showProducts(request, response);
                break;
            case "/addPrduct":
                addProduct(request, response);
                break;
            case "/listAdded":
                showAddedProducts(request, response);
                break;


        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {


            case "/insertCustomer":
                insertCustomer(request, response);
                break;

            case "/insertProduct":
                //insertProduct(request, response);
                break;
            case "/listProducts":
                showProducts(request, response);
                break;
            case "/addPrduct":
                addProduct(request, response);
                break;
            case "/listAdded":
                showAddedProducts(request, response);
                break;


        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("custId");
        int pId = Integer.parseInt(request.getParameter("id"));
        String pName = request.getParameter("pName");
        int price = Integer.parseInt(request.getParameter("price"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        int uQty = Integer.parseInt(request.getParameter("uQty"));


        System.out.println(qty);
        System.out.println(uQty);
        // qty-=uQty;
        System.out.println(qty);
        System.out.println(uQty);

        int cId = (int) session.getAttribute("custId");
        System.out.println(cId);

        Customer customer = new Customer();
        customer.setcId(cId);

        // Products products = new Products(pName,uQty,price,customer);
        //  products.setpQuantity(qty);
        // CustDAO.insertProduct(products,pId,qty);

        //System.out.println("hii from addproduct");

//

        Session s = factory.openSession();
        Transaction tx = null;
        Products product = null;
        try {
            tx = s.beginTransaction();


            // Check if the product is already in the user's cart
            product = CustDAO.selectProduct(pName, cId);
            if (product != null) {
                if (qty < uQty) {
                    PrintWriter out = response.getWriter();
                    String abc = "Ordered quantity is not available";
                    request.setAttribute("abc", abc);
                }else {
                    int existingQuantity = product.getpQuantity();
                    int orderedQuantity = uQty;
                    int remainingQuantity = existingQuantity - orderedQuantity;
                    // Update the quantity of the existing product in the cart
                    product.setpQuantity(orderedQuantity);
                    s.update(product);

            } }else {
                if (qty < uQty) {
                    PrintWriter out = response.getWriter();
                    String abc = "Ordered quantity is not available";
                    request.setAttribute("abc", abc);
                } else {

                    // Add the product to the user's cart
                    product = new Products(pName, uQty, price, customer);
                    s.save(product);
                }
            }


                // Reduce the available quantity of the product in the database
            if (qty < uQty) {
                PrintWriter out = response.getWriter();
                String abc = "Ordered quantity is not available";
                request.setAttribute("abc", abc);
            }else {
                int remainingQuantity = qty - uQty;
                CustDAO.updateProductQuantity(pName, remainingQuantity);
            }
                tx.commit();
            } catch(HibernateException e){
                if (tx != null) tx.rollback();
                throw e;
            } finally{
                s.close();

            }
            System.out.println("hii before");
            //response.sendRedirect("listProducts");
            RequestDispatcher dispatcher = request.getRequestDispatcher("listProducts");
            dispatcher.forward(request, response);


        }


//    private void showProduct(HttpServletRequest request, HttpServletResponse response) {
//
//      //  Session s = factory.openSession();
//        Transaction tx = s.beginTransaction();
//        Product product = new Product("Pizza",100,20);
//        Product product1 = new Product("Coffee",50,10);
//
//        s.save(product);
//
//        tx.commit();
//        s.close();
//
//    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cName = request.getParameter("name");
        String cEmail = request.getParameter("email");
        String cPassword = request.getParameter("password");
        int cPhone = Integer.parseInt(request.getParameter("phno"));

        Customer newCustomer = new Customer(cName, cEmail, cPassword, cPhone);
        CustDAO.insertCustomer(newCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    private void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> product = CustDAO.selectAllProducts();
        System.out.println("jiiiiiiiiiiiiiiii");
        // List<Product> products = CustDAO.selectAllProducts();
        request.setAttribute("product", product);

        System.out.println(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("add-product.jsp");
        dispatcher.forward(request, response);
    }

    private void showAddedProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        int id = (int) session.getAttribute("custId");
        int bill = 0;

        List<Products> product = CustDAO.selectAllSelectedProducts(id);
        //System.out.println("jiiiiiiiiiiiiiiii");
        // List<Product> products = CustDAO.selectAllProducts();
        request.setAttribute("products", product);

        for (Products p : product) {
            bill = bill + p.getpPrice() * p.getpQuantity();
        }
        request.setAttribute("bill", bill);
        System.out.println(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("show-cart.jsp");
        dispatcher.include(request, response);
    }








    }


