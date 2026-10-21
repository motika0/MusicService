package music;
// Проверка для коммита
import music.model.Playlist;
import music.model.Track;
import music.repository.InMemoryPlaylistRepository;
import music.repository.PlaylistRepository;
import music.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistServiceTest {

    private PlaylistRepository repository;
    private PlaylistService service;
    private Playlist playlist;
    private Track track;

    @BeforeEach
    void setUp() {
        repository = new InMemoryPlaylistRepository();
        service = new PlaylistService(repository);

        playlist = new Playlist(1, "Мои любимые");
        track = new Track(1, "Believer", "Imagine Dragons");

        repository.save(playlist);
    }

    @Test
    void shouldAddTrackToPlaylist() {
        service.addTrack(1, track);

        assertEquals(1, playlist.getTracks().size());
        assertTrue(playlist.getTracks().contains(track));
    }

    @Test
    void shouldNotAddSameTrackTwice() {
        service.addTrack(1, track);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addTrack(1, track)
        );

        assertEquals(1, playlist.getTracks().size());
    }

    @Test
    void sameTrackCanBeAddedToDifferentPlaylists() {
        Playlist anotherPlaylist =
                new Playlist(2, "Для тренировки");

        repository.save(anotherPlaylist);

        service.addTrack(1, track);
        service.addTrack(2, track);

        assertEquals(1, playlist.getTracks().size());
        assertEquals(1, anotherPlaylist.getTracks().size());
    }

    @Test
    void shouldThrowExceptionWhenPlaylistDoesNotExist() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.addTrack(999, track)
        );
    }

    @Test
    void shouldAddDifferentTracksToPlaylist() {
        Track secondTrack =
                new Track(2, "Thunder", "Imagine Dragons");

        service.addTrack(1, track);
        service.addTrack(1, secondTrack);

        assertEquals(1, playlist.getTracks().size());
        assertTrue(playlist.getTracks().contains(track));
        assertTrue(playlist.getTracks().contains(secondTrack));
    }

    @Test
    void shouldRejectTrackWithSameId() {
        Track sameTrack =
                new Track(1, "Believer (копия)", "Другой исполнитель");

        service.addTrack(1, track);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.addTrack(1, sameTrack)
        );

        assertEquals(2, playlist.getTracks().size());
    }
    @Test
    void shouldCreateEmptyPlaylist() {
        Playlist empty = new Playlist(3, "Новый");

        assertTrue(empty.getTracks().isEmpty());
    }
}
