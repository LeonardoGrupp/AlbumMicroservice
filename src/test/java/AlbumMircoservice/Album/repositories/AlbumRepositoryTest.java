package AlbumMircoservice.Album.repositories;

import AlbumMircoservice.Album.entities.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    public void testSaveAlbum() {
        // Arrange
        Album album = new Album();
        album.setName("Test Album");
        album.setReleaseDate("2024-10-01");

        Album savedAlbum = albumRepository.save(album);

        assertThat(savedAlbum).isNotNull();
        assertThat(savedAlbum.getId()).isNotNull();
        assertThat(savedAlbum.getName()).isEqualTo("Test Album");
        assertThat(savedAlbum.getReleaseDate()).isEqualTo("2024-10-01");
    }

    @Test
    public void testFindAlbumByName() {
        Album album = new Album();
        album.setName("Test Album");
        album.setReleaseDate("2024-10-01");
        albumRepository.save(album);

        Album foundAlbum = albumRepository.findAlbumByName("Test Album");

        assertThat(foundAlbum).isNotNull();
        assertThat(foundAlbum.getName()).isEqualTo("Test Album");
        assertThat(foundAlbum.getReleaseDate()).isEqualTo("2024-10-01");
    }

    @Test
    public void testFindAlbumById() {
        Album album = new Album();
        album.setName("Test Album");
        album.setReleaseDate("2024-10-01");
        Album savedAlbum = albumRepository.save(album);

        Optional<Album> foundAlbum = albumRepository.findById(savedAlbum.getId());

        assertThat(foundAlbum).isPresent();
        assertThat(foundAlbum.get().getName()).isEqualTo("Test Album");
        assertThat(foundAlbum.get().getReleaseDate()).isEqualTo("2024-10-01");
    }

    @Test
    public void testFindAlbumByName_NotFound() {
        Album foundAlbum = albumRepository.findAlbumByName("Nonexistent Album");

        assertThat(foundAlbum).isNull();
    }

    @Test
    public void testDeleteAlbum() {
        Album album = new Album();
        album.setName("Test Album");
        album.setReleaseDate("2024-10-01");
        Album savedAlbum = albumRepository.save(album);

        albumRepository.deleteById(savedAlbum.getId());
        Optional<Album> foundAlbum = albumRepository.findById(savedAlbum.getId());

        assertThat(foundAlbum).isNotPresent();
    }
}
