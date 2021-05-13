package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    private Map<String, UserSession> sessions;
    private int sessionValid;

    public SessionManager(int sessionValid) {
        sessions = new HashMap<>();
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession) {
        userSession.setLastAccess(LocalDateTime.now());
        sessions.put(userSession.getUserName(), userSession);
    }

    public UserSession find(String userName) {
        for (Map.Entry<String, UserSession> session : sessions.entrySet()) {
            UserSession value = session.getValue();
            if (value.getUserName().equals(userName)) {
                value.setLastAccess(LocalDateTime.now());
                return value;

            }
        }
        return null;
    }

    public UserSession get(int sessionHandle) {
        for (Map.Entry<String, UserSession> session : sessions.entrySet()) {
            UserSession value = session.getValue();
            if (value.getSessionHandle() == sessionHandle) {
                value.setLastAccess(LocalDateTime.now());
                return value;
            }
        }
        return null;
    }

    public void delete(int sessionHandle) {
        for (Map.Entry<String, UserSession> session : sessions.entrySet()) {
            UserSession value = session.getValue();
            if (value.getSessionHandle() == sessionHandle) {
                sessions.remove(value.getUserName());

            }
        }
    }

    public void deleteExpired() {
        for (Map.Entry<String, UserSession> session : sessions.entrySet()) {
            LocalDateTime AccessExp = session.getValue().getLastAccess().plusSeconds(sessionValid);
            if (AccessExp.isBefore(LocalDateTime.now())) {
                sessions.remove(session.getKey());
            }
        }
    }
}


