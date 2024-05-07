package EventSource.src.main.java.com.app.model;

public class Ping {
    private String serverTime;

    public Ping() {

    }

    public Ping(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }
}
