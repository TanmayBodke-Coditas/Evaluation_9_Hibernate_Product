package Hibernate_Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory factory = cfg.buildSessionFactory();
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();


        Set<Movie> movies = new HashSet<>();

       Movie movie = new Movie();
       movie.setId(1);
       movie.setName("bahubali");
       Movie movie1 = new Movie();
       movie.setId(2);
       movie.setName("bahubali2");

       movies.add(movie);
       movies.add(movie1);

       Director director = new Director("rajamauli",movies);

//       movies.add("bahubali2");
//       movies.add();

        s.save(movie);
        s.save(director);

        tx.commit();
        s.close();
    }
}
