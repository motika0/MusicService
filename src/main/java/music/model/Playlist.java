package music.model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private final int id;
    private final String name;
    private final List<Track> tracks = new ArrayList<>();

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}