package com.example.hp.behaq.Modal;

/**
 * Created by hp on 15/10/2017.
 */

public class User {
    private String username, email,tags;

    public User(String username, String email, String tags) {
        this.username = username;
        this.email = email;
        this.tags = tags;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
