package AlbumMircoservice.Album.controllers;

import AlbumMircoservice.Album.entities.AlbumSongResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import AlbumMircoservice.Album.entities.Album;
import AlbumMircoservice.Album.services.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AlbumService albumService;

    private Album album;
    private String albumJson;
    private String expectedAlbumJson;

    @BeforeEach
    public void setup() throws JsonProcessingException {
        album = new Album();
        album.setId(1L);
        album.setName("Test Album");
        album.setReleaseDate("2023-09-01");

        Album expectedAlbumReturnData = new Album();
        expectedAlbumReturnData.setId(1L);
        expectedAlbumReturnData.setName("Test Album");
        expectedAlbumReturnData.setReleaseDate("2023-09-01");

        albumJson = objectMapper.writeValueAsString(album);
        expectedAlbumJson = objectMapper.writeValueAsString(expectedAlbumReturnData);
    }

    @Test
    void testGetAlbumById_ReturnsOkStatus() throws Exception {
        Long albumId = 1L;
        given(albumService.getAlbumById(albumId)).willReturn(album);

        mockMvc.perform(get("/album/GetAlbum/{albumId}", albumId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedAlbumJson));
    }

    @Test
    void testCreateAlbum_ReturnsCreatedStatus() throws Exception {
        given(albumService.createAlbum(any(Album.class))).willReturn(album);

        mockMvc.perform(post("/album/createAlbum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(albumJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedAlbumJson));
    }

    @Test
    void testUpdateAlbum_ReturnsOkStatus() throws Exception {
        Long albumId = 1L;
        Album updatedAlbum = new Album();
        updatedAlbum.setId(albumId);
        updatedAlbum.setName("Updated Album");
        updatedAlbum.setReleaseDate("2024-01-01");
        String updatedAlbumJson = objectMapper.writeValueAsString(updatedAlbum);

        given(albumService.updateAlbum(eq(albumId), any(Album.class))).willReturn(updatedAlbum);

        mockMvc.perform(put("/album/UpdateAlbum/{albumId}", albumId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedAlbumJson))
                .andExpect(status().isOk())
                .andExpect(content().json(updatedAlbumJson));
    }

    @Test
    void testDeleteAlbum_ReturnsNoContentStatus() throws Exception {
        Long albumId = 1L;

        mockMvc.perform(delete("/album/DeleteAlbum/{albumId}", albumId))
                .andExpect(status().isNoContent());

        verify(albumService, times(1)).deleteAlbum(albumId);
    }

    @Test
    void testGetAllAlbums_ReturnsOkStatus() throws Exception {
        List<Album> albums = Collections.singletonList(album);
        String albumsJson = objectMapper.writeValueAsString(albums);
        given(albumService.getAllAlbums()).willReturn(albums);

        mockMvc.perform(get("/album/getAllAlbums"))
                .andExpect(status().isOk())
                .andExpect(content().json(albumsJson));
    }

    @Test
    void testAlbumExist_ReturnsOkStatus() throws Exception {
        String albumName = "Test Album";
        given(albumService.checkIfAlbumExistsByName(albumName)).willReturn(true);

        mockMvc.perform(get("/album/exists/{name}", albumName))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testAlbumDoesNotExist_ReturnsOkStatus() throws Exception {
        String albumName = "Non-existent Album";
        given(albumService.checkIfAlbumExistsByName(albumName)).willReturn(false);

        mockMvc.perform(get("/album/exists/{name}", albumName))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void testAddSongToAlbum_ReturnsOkStatus() throws Exception {
        Long albumId = 1L;
        String albumName = "Test Album";
        Long songId = 100L;
        String songTitle = "Test Song";
        Integer position = 1;

        Map<String, Integer> requestBody = new HashMap<>();
        requestBody.put("position", position);
        String requestJson = objectMapper.writeValueAsString(requestBody);

        AlbumSongResponseDTO responseDTO = new AlbumSongResponseDTO(albumId, albumName, songId, songTitle, position);
        given(albumService.addSongToAlbum(albumId, songId, position)).willReturn(responseDTO);

        mockMvc.perform(post("/album/{albumId}/{songId}", albumId, songId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDTO)));
    }

}
