package com.logblock.backend.DataSource.Model;

import jakarta.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String userName;
    private String profileImg;
    private String biography;

    // Getters
    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public String getBiography() {
        return biography;
    }

    // Setters
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
