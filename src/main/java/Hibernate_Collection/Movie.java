package Hibernate_Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Movie {

    @Id
    private int id;
    private String name;

    @ManyToOne
    private Director director;

    @OneToMany(mappedBy = "movie")
    private Set<Songs> songs;


    public Movie(String name, Director director, Set<Songs> songs) {
        this.name = name;
        this.director = director;
        this.songs = songs;
    }

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Set<Songs> getSongs() {
        return songs;
    }

    public void setSongs(Set<Songs> songs) {
        this.songs = songs;
    }
}
