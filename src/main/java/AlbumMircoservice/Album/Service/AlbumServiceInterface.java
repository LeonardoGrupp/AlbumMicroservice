package AlbumMircoservice.Album.Service;

import AlbumMircoservice.Album.Entity.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumServiceInterface {
        //List<Album> getAlbumsByArtist(Long artistId);
        Album getAlbumById(Long albumId);
        Album createAlbum(Album album);
        Album updateAlbum(Long albumId, Album album);
        void deleteAlbum(Long albumId);

}
