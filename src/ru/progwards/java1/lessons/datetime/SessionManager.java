package ru.progwards.java1.lessons.datetime;

import java.time.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SessionManager {

    private Map<String, UserSession> sessions = new HashMap<>();
    private int sessionValid;


    public SessionManager(int sessionValid) {
        this.sessionValid = sessionValid;
    }

    public void add(UserSession userSession) {
        sessions.put(userSession.getUserName(), userSession);
    }

    public UserSession find(String userName) {
        return sessions.get(userName);
    }

    public UserSession get(int sessionHandle) {
        for(Map.Entry<String, UserSession> session : sessions.entrySet()) {
            UserSession value = session.getValue();
            if (value.sessionHandle == sessionHandle) {
                value.setLastAccess(LocalDateTime.now());
                return value;
            }
        }
        return null;
    }

            public void delete(int sessionHandle) {
                for (Map.Entry<String, UserSession> session : sessions.entrySet()) {
                    UserSession value = session.getValue();
                    if (value.sessionHandle == sessionHandle) {
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


    static class UserSession {
        private int sessionHandle;
        private String userName;
        private LocalDateTime lastAccess;

        public UserSession(String userName) {
            Random random = new Random();
            this.userName = userName;
            sessionHandle = random.nextInt();
            lastAccess = LocalDateTime.now();
        }

        public int getSessionHandle() {
            return sessionHandle;
        }

        public void setSessionHandle(int sessionHandle) {
            this.sessionHandle = sessionHandle;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public LocalDateTime getLastAccess() {
            return lastAccess;
        }

        public void setLastAccess(LocalDateTime lastAccess) {
            this.lastAccess = lastAccess;
        }
    }

}


