package com.logblock.backend.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.User;
import com.logblock.backend.DataSource.Repository.UserRepository;

@Service
public class AccountService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new user account.
     *
     * @param email The email for the new user account
     * @return The ID of the newly created user
     */
    public int createAccount(String email) {
        // Create a new user with default or provided values
        User newUser = new User();
        newUser.setUserEmail(email);
        newUser.setDisplayName(email.split("@")[0]); // Example of setting a username based on the email
        newUser.setBioDesc("New user bio.");
        newUser.setProfileImg("defaultProfilePic.png");
        newUser.setPrivLevel(1); // Default user privilege level

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
