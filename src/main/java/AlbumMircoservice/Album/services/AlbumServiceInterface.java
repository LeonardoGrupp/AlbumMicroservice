package AlbumMircoservice.Album.services;

import AlbumMircoservice.Album.entities.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumServiceInterface {
        Album getAlbumById(Long albumId);
        Album createAlbum(Album album);
        Album updateAlbum(Long albumId, Album album);
        void deleteAlbum(Long albumId);
        Boolean checkIfAlbumExistsByName(String name);
        List<Album> getAllAlbums();

}
