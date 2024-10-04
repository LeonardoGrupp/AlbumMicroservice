package AlbumMircoservice.Album.repositories;

import AlbumMircoservice.Album.entities.AlbumMusic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumMusicRepository extends JpaRepository<AlbumMusic, Long> {

}
