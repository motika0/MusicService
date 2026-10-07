package music.service;

import music.model.Playlist;
import music.model.Track;
import music.repository.PlaylistRepository;

public class PlaylistService {

    private final PlaylistRepository repository;

    public PlaylistService(PlaylistRepository repository) {
        this.repository = repository;
    }

    public void addTrack(int playlistId, Track track) {
        Playlist playlist = repository.findById(playlistId);

        if (playlist == null) {
            throw new IllegalArgumentException("Плейлист не найден");
        }

        if (playlist.getTracks().contains(track)) {
            throw new IllegalArgumentException(
                    "Этот трек уже есть в плейлисте"
            );
        }

        playlist.getTracks().add(track);
        repository.save(playlist);
    }
}