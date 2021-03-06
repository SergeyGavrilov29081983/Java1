package ru.progwards.java1.lessons.datetime;

import java.time.LocalDateTime;
import java.util.Random;

public class UserSession {
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

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getLastAccess() {
        return lastAccess;
    }

    public void updateLastAccess() {
        lastAccess = LocalDateTime.now();
    }
}
