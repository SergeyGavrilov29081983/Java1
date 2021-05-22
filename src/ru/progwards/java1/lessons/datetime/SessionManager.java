package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class SessionManager {
    private Collection<UserSession> sessions = new ArrayList<>();
    private int sessionValid;

    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession) {
        userSession.updateLastAccess();
        sessions.add(userSession);
    }

    public UserSession find(String userName) {
        for (UserSession temp : sessions) {
            if (userName.equals(temp.getUserName())) {
                LocalDateTime AccessExp = temp.getLastAccess().plusSeconds(sessionValid);
                if (AccessExp.isAfter(LocalDateTime.now())) {
                    temp.updateLastAccess();
                    return temp;
                }
            }
        }
        return null;
    }

    public UserSession get(int sessionHandle) {
        for (UserSession temp : sessions) {
            if (sessionHandle == temp.getSessionHandle()) {
                LocalDateTime AccessExp = temp.getLastAccess().plusSeconds(sessionValid);
                if (AccessExp.isAfter(LocalDateTime.now())) {
                    temp.updateLastAccess();
                    return temp;
                }
            }
        }
        return null;
    }

    public void delete(int sessionHandle) {
        sessions.removeIf(temp -> sessionHandle == temp.getSessionHandle());
    }

    public void deleteExpired() {
        sessions.removeIf(temp -> temp.getLastAccess().plusSeconds(sessionValid).isBefore(LocalDateTime.now()));
    }
}




