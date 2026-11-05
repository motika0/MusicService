package music.model;

public class User {
    // This is an intentionally very long comment created to demonstrate that Checkstyle detects a line exceeding the maximum allowed length of 120 characters in the Java source code.hvyigkjbgkhvkhmvjch jhvkkhkhkvkvj
    private final int id;
    private final String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}