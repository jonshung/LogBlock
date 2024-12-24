package com.logblock.backend.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.DataSource.Repository.ProfileRepository;

@Service
public class AccountService {

    @Autowired
    private ProfileRepository userRepository;

    /**
     * Create a new user account.
     *
     * @param email The email for the new user account
     * @return The ID of the newly created user
     */
    public int createAccount(String email) {
        // Create a new user with default or provided values
        Profile newUser = new Profile(email, email.split("@")[0], "New user bio.", "defaultProfilePic.png", 0);
        userRepository.addUser(newUser);
        return newUser.getUserID();
    }

    /**
     * Delete an existing user account.
     *
     * @param userID The ID of the user account to delete
     * @return 1 if the operation is successful, otherwise 0
     */
    public int deleteAccount(int userID) {
        return userRepository.removeUser(userID);
    }
}
