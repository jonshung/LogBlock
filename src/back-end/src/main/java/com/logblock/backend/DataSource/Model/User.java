package com.logblock.backend.DataSource.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    private String userEmail;
    private String userName;
    private String bioDesc;
    private String profileImg;
    private int privLevel;
    private List<Integer> connections; // List of connected user IDs
    @ElementCollection
    private List<Integer> pinnedPosts;

    @ElementCollection
    private List<Integer> blockedProfiles;

    @ElementCollection
    private List<Integer> recentlyViewedPosts;

    // Getters và Setters
    public int getUserID() {
        return userID;
    }

    public List<Integer> getConnections() {
        return connections;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Integer> getPinnedPosts() {
        return pinnedPosts;
    }

    public void setPinnedPosts(List<Integer> pinnedPosts) {
        this.pinnedPosts = pinnedPosts;
    }

    public List<Integer> getBlockedProfiles() {
        return blockedProfiles;
    }

    public void setBlockedProfiles(List<Integer> blockedProfiles) {
        this.blockedProfiles = blockedProfiles;
    }

    public List<Integer> getRecentlyViewedPosts() {
        return recentlyViewedPosts;
    }

    public void setRecentlyViewedPosts(List<Integer> recentlyViewedPosts) {
        this.recentlyViewedPosts = recentlyViewedPosts;
    }
}
