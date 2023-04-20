package Hibernate_Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Songs {

    @Id
    private int sId;
    private String sName;

    @ManyToOne
    private Movie movie;

    public Songs(String sName, Movie movie) {
        this.sName = sName;
        this.movie = movie;
    }

    public Songs() {
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
