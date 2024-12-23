package com.logblock.backend.DataSource.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Profile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userID;

    @Column(name = "useremail")
    private String userEmail;
    @Column(name = "displayname")
    private String displayName;
    @Column(name = "biographydesc")
    private String bioDesc;
    @Column(name = "profileimage")
    private String profileImg;
    @Column(name = "privlevel")
    private int privLevel;

    // Getters và Setters
    public int getUserID() {
        return userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String userName) {
        this.displayName = userName;
    }

    public String getBioDesc() {
        return bioDesc;
    }

    public void setBioDesc(String bioDesc) {
        this.bioDesc = bioDesc;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public int getPrivLevel() {
        return privLevel;
    }

    public void setPrivLevel(int privLevel) {
        this.privLevel = privLevel;
    }
}
