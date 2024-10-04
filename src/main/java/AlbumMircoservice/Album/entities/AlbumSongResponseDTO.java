package AlbumMircoservice.Album.entities;

public class AlbumSongResponseDTO {

    private Long albumId;
    private String albumName;
    private Long songId;
    private String songTitle;
    private Integer position;

    public AlbumSongResponseDTO(Long albumId, String albumName, Long songId, String songTitle, Integer position) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.songId = songId;
        this.songTitle = songTitle;
        this.position = position;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

