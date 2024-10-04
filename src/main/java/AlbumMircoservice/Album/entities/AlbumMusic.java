package AlbumMircoservice.Album.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "album_music")
public class AlbumMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne
    @JoinColumn(name = "music_id", nullable = false)
    private Music music;

    @Column(name = "position", nullable = false)
    private Integer position;

    public AlbumMusic() {}

    public AlbumMusic(Album album, Music music, Integer position) {
        this.album = album;
        this.music = music;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

