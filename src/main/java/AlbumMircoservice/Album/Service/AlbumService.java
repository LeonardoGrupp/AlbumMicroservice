package AlbumMircoservice.Album.Service;

import AlbumMircoservice.Album.Entity.Album;
import AlbumMircoservice.Album.Repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements AlbumServiceInterface {

    @Autowired
    private AlbumRepository albumRepository;

    /*@Override
    public List<Album> getAlbumsByArtist(Long artistId) {
        return albumRepository.findByArtistsId(artistId);
    }*/

    @Override
    public Album getAlbumById(Long albumId) {
        return albumRepository.findById(albumId).orElseThrow(() -> new RuntimeException("Album not found"));
    }

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Long albumId, Album album) {
        Album existingAlbum = getAlbumById(albumId);
        existingAlbum.setName(album.getName());
        existingAlbum.setReleaseDate(album.getReleaseDate());
        //existingAlbum.setArtists(album.getArtists());
        return albumRepository.save(existingAlbum);
    }

    @Override
    public void deleteAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
    }
}
