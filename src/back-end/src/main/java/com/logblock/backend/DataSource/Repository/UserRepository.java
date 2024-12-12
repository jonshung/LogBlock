package com.logblock.backend.DataSource.Repository;

import com.logblock.backend.DataSource.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Retrieve all users.
     *
     * @return List of all users
     */
    @Query("SELECT u FROM User u")
    List<User> retrieveAllUsers();

    /**
     * Retrieve a user by their ID.
     *
     * @param userID ID of the user
     * @return User object if found, otherwise null
     */
    @Override
    Optional<User> findById(Integer userID); // Make sure to use Optional<User>

    /**
     * Retrieve a user by their email.
     *
     * @param email Email of the user
     * @return User object if found, otherwise null
     */
    @Query("SELECT u FROM User u WHERE u.userEmail = :email")
    User retrieveUserByEmail(String email);

    /**
     * Add a new user.
     *
     * @param newUser User object to be added
     * @return ID of the newly added user
     */
    @Transactional
    default int addUser(User newUser) {
        // Save the user and return the user's ID
        User savedUser = save(newUser);
        return savedUser.getUserID();
    }

    /**
     * Update an existing user.
     *
     * @param userID   ID of the user to update
     * @param userInfo Updated user information
     * @return ID of the updated user
     */
    @Transactional
    default int updateUser(int userID, User userInfo) {
        Optional<User> existingUserOpt = findById(userID);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUserEmail(userInfo.getUserEmail());
            existingUser.setUserName(userInfo.getUserName());
            existingUser.setBioDesc(userInfo.getBioDesc());
            existingUser.setProfileImg(userInfo.getProfileImg());
            existingUser.setPrivLevel(userInfo.getPrivLevel());
            existingUser.setPinnedPosts(userInfo.getPinnedPosts());
            existingUser.setBlockedProfiles(userInfo.getBlockedProfiles());
            existingUser.setRecentlyViewedPosts(userInfo.getRecentlyViewedPosts());
            save(existingUser); // Save the updated user
            return existingUser.getUserID();
        }
        return -1; // Return -1 if user does not exist
    }

    /**
     * Remove a user by their ID.
     *
     * @param userID ID of the user to be removed
     * @return 1 if the operation is successful, otherwise 0
     */
    @Transactional
    default int removeUser(int userID) {
        Optional<User> userOpt = findById(userID);
        if (userOpt.isPresent()) {
            delete(userOpt.get()); // Delete the user from the database
            return 1;
        }
        return 0; // Return 0 if user does not exist
    }
}
