package AlbumMircoservice.Album.Entity;

import jakarta.persistence.*;

import javax.print.attribute.standard.Media;
import java.util.Date;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_name")
    private String name;

    @Column(name = "release_date")
    private Date releaseDate;

    // ARTIST RELATERADE GREJER
    // VET EJ OM DETTA FUNKAR

    /*@ManyToMany
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;

    // Constructors, getters and setters
    public Album() {}

    public Album(String name, Date releaseDate, List<Artist> artists) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.artists = artists;
    }*/

    public Album() {
    }

    public Album(String name, Date releaseDate) {
        this.name = name;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

}

