package music.repository;

import music.model.Playlist;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPlaylistRepository implements PlaylistRepository {

    private final Map<Integer, Playlist> playlists = new HashMap<>();

    @Override
    public void save(Playlist playlist) {
        playlists.put(playlist.getId(), playlist);
    }

    @Override
    public Playlist findById(int id) {
        return playlists.get(id);
    }
}