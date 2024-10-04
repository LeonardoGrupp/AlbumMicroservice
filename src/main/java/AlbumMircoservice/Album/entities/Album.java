package AlbumMircoservice.Album.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "album_name", nullable = false)
    private String name;

    @Column(name = "release_date", nullable = false)
    private String releaseDate;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlbumMusic> albumMusicList;

    public Album() {}

    public Album(String name, String releaseDate) {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<AlbumMusic> getAlbumMusicList() {
        return albumMusicList;
    }

    public void setAlbumMusicList(List<AlbumMusic> albumMusicList) {
        this.albumMusicList = albumMusicList;
    }
}

