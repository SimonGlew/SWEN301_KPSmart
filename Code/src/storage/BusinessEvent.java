package storage;

import java.text.SimpleDateFormat;

public abstract class BusinessEvent {
    int id;
    String username;
    String dateyymmddhhmmss;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateyymmddhhmmss() {
        return dateyymmddhhmmss;
    }

    public void setDateyymmddhhmmss(String dateyymmddhhmmss) {
        this.dateyymmddhhmmss = dateyymmddhhmmss;
    }

    @Override
    public String toString() {
        return "BusinessEvent{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", dateyymmddhhmmss='" + dateyymmddhhmmss + '\'' +
                '}';
    }
}

