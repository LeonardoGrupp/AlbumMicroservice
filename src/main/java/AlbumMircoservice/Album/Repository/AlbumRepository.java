package AlbumMircoservice.Album.Repository;

import AlbumMircoservice.Album.Entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findAlbumByName(String name);
}