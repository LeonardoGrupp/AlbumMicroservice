package AlbumMircoservice.Album.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "songs")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, length = 250)
    private String url;

    @Column(nullable = false, length = 10)
    private String releaseDate;

    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlbumMusic> albumMusicList;

    public Music() {}

    public Music(String title, String url, String releaseDate) {
        this.title = title;
        this.url = url;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

