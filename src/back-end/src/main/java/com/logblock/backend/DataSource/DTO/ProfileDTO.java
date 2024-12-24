package com.logblock.backend.DataSource.DTO;

import com.logblock.backend.DataSource.Model.Profile;

public class ProfileDTO {
    public int userID;
    public String userEmail;
    public String displayName;
    public String bioDesc;
    public String profileImg;
    
    public ProfileDTO(int userID, String userEmail, String displayName, String bioDesc, String profileImg) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.displayName = displayName;
        this.bioDesc = bioDesc;
        this.profileImg = profileImg;
    }

    public static Profile toProfile(ProfileDTO dto) {
        Profile p = new Profile(dto.userEmail, dto.displayName, dto.bioDesc, dto.profileImg, 0);
        if(dto.userID > 0) p.setUserID(dto.userID);
        return p;
    }

    public static ProfileDTO toDTO(Profile p) {
        return new ProfileDTO(p.getUserID(), p.getUserEmail(), p.getUserEmail(), p.getBioDesc(), p.getProfileImg());
    }
}