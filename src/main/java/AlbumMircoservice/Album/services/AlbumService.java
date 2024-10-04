package AlbumMircoservice.Album.services;

import AlbumMircoservice.Album.entities.Album;
import AlbumMircoservice.Album.entities.AlbumMusic;
import AlbumMircoservice.Album.entities.AlbumSongResponseDTO;
import AlbumMircoservice.Album.entities.Music;
import AlbumMircoservice.Album.repositories.AlbumMusicRepository;
import AlbumMircoservice.Album.repositories.AlbumRepository;
import AlbumMircoservice.Album.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService implements AlbumServiceInterface {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private AlbumMusicRepository albumMusicRepository;

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
        return albumRepository.save(existingAlbum);
    }

    public AlbumSongResponseDTO addSongToAlbum(Long albumId, Long songId, Integer position) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException("Album not found"));
        Music song = musicRepository.findById(songId)
                .orElseThrow(() -> new RuntimeException("Song not found"));

        AlbumMusic albumMusic = new AlbumMusic();
        albumMusic.setAlbum(album);
        albumMusic.setMusic(song);
        albumMusic.setPosition(position);

        albumMusicRepository.save(albumMusic);


        return new AlbumSongResponseDTO(
                album.getId(),
                album.getName(),
                song.getId(),
                song.getTitle(),
                position
        );
    }

    @Override
    public void deleteAlbum(Long albumId) {
        albumRepository.deleteById(albumId);
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Boolean checkIfAlbumExistsByName(String name) {
        Album album = albumRepository.findAlbumByName(name);

        return album != null;
    }
}
