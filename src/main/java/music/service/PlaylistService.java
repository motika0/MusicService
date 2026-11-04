
package music.service;

import music.model.Playlist;
import music.model.Track;
import music.repository.PlaylistRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaylistService {
    // THIS IS AN INTENTIONAL CHECKSTYLE VIOLATION CREATED FOR LABORATORY DEMONSTRATION PURPOSESfvsdgsdgdfgsfdvhbsdvjsdvjsdvhsdvsdvsdhvhsdvhsdvsdvsdvsddvsdv
    // Создание Logger для данного класса
    private static final Logger logger =
            LoggerFactory.getLogger(PlaylistService.class);

    private final PlaylistRepository repository;

    public PlaylistService(PlaylistRepository repository) {
        this.repository = repository;
    }

    public void addTrack(int playlistId, Track track) {

        Playlist playlist = repository.findById(playlistId);

        // WARN: плейлист не найден
        if (playlist == null) {
            logger.warn(
                    "Cannot add track: playlist with id {} was not found",
                    playlistId
            );

            throw new IllegalArgumentException("Плейлист не найден");
        }

        // WARN: трек уже существует в плейлисте
        if (playlist.getTracks().contains(track)) {
            logger.warn(
                    "Track '{}' is already in playlist '{}'",
                    track.getTitle(),
                    playlist.getName()
            );

            throw new IllegalArgumentException(
                    "Этот трек уже есть в плейлисте"
            );
        }

        playlist.getTracks().add(track);

        // ERROR: ошибка при сохранении плейлиста
        try {
            repository.save(playlist);
        } catch (RuntimeException exception) {

            logger.error(
                    "Failed to save playlist with id {}",
                    playlistId,
                    exception
            );

            throw exception;
        }

        // INFO: успешное добавление трека
        logger.info(
                "Track '{}' successfully added to playlist '{}'",
                track.getTitle(),
                playlist.getName()
        );
    }
}
