package com.logblock.backend.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.DataSource.Repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    /**
     * Update user's display name.
     * 
     * @param userID
     * @param newDisplayName
     * @return
     */
    public int updateDisplayName(int userID, String newDisplayName) {
        Profile profile = profileRepository.findById(userID).orElse(null);
        if(profile == null) {
            return 0;
        }
        profile.setDisplayName(newDisplayName);
        profileRepository.save(profile);
        return 0;
    }
    /**
     * Update user's biography.
     *
     * @param userID       ID of the user
     * @param newBiography The new biography
     * @return 1 if update is successful, otherwise 0
     */
    public int updateBiography(int userID, String newBiography) {
        Profile profile = profileRepository.findById(userID).orElse(null);
        if (profile != null) {
            profile.setBioDesc(newBiography);
            profileRepository.save(profile);
            return 1; // Success
        }
        return 0; // Failure
    }

    /**
     * Update user's profile image.
     *
     * @param userID        ID of the user
     * @param newProfileImg URL or path of the new profile image
     * @return 1 if update is successful, otherwise 0
     */
    public int updateProfileImg(int userID, String newProfileImg) {
        Profile profile = profileRepository.findById(userID).orElse(null);
        if (profile != null) {
            profile.setProfileImg(newProfileImg);
            profileRepository.save(profile);
            return 1; // Success
        }
        return 0; // Failure
    }

    /**
     * Update user's privilege level.
     * 
     * @param userID
     * @param newPrivilegeLevel
     * @return int
     */
    public int updatePrivilegeLevel(int userID, int newPrivilegeLevel) {
        Profile profile = profileRepository.findById(userID).orElse(null);
        if(newPrivilegeLevel < 0 || newPrivilegeLevel > 2) {
            return 0; // privlege only from 0 to 2.
        }
        if (profile != null) {
            profile.setPrivLevel(newPrivilegeLevel);
            profileRepository.save(profile);
            return 1; // Success
        }
        return 0; // Failure
    }

    /**
     * Retrieve the user's profile information.
     *
     * @param userID ID of the user
     * @return Profile object if found, otherwise null
     */
    public Profile getProfileByID(int userID) {
        return profileRepository.findById(userID).orElse(null);
    }

    /**
     * Retrieve the user's profile information by email.
     * 
     * @param email
     * @return Profile
     */
    public Profile getProfileByEmail(String email) {
        return profileRepository.findUserByUserEmail(email).orElse(null);
    }

    /**
     * Get the current user requesting this service endpoint if exists, else return null.
     * 
     * @return Profile
     */
    public Profile getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.isAuthenticated()) {
            return null;
        }
        DefaultOidcUser u = (DefaultOidcUser) auth.getPrincipal();
        if(u == null) {
            return null;
        }
        return getProfileByEmail(u.getEmail());
    }
}
