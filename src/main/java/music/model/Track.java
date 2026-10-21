package music.model;

import java.util.Objects;

public class Track {
    private final int id;
    private final String title;
    private final String artist;

    public Track(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return id == track.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}