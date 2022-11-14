package bio;

import java.net.Socket;

public class Session {
    private final String  sessionId;
    private Socket socket;

    public Session(String sessionId, Socket socket) {
        this.sessionId = sessionId;
        this.socket = socket;
    }

    public String getSessionId() {
        return sessionId;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
