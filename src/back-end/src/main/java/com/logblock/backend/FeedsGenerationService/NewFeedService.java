package com.logblock.backend.FeedsGenerationService;

import com.logblock.backend.DataSource.Model.Post;
import com.logblock.backend.DataSource.Model.User;
import com.logblock.backend.DataSource.Repository.UserRepository;
import com.logblock.backend.DataSource.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewFeedService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * Generate the News Feed for a user based on their connections.
     *
     * @param userID ID of the user
     * @return List of posts generated for the user's News Feed
     */
    public List<Post> generate(int userID) {
        // Retrieve the user by ID
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the list of connected user IDs
        List<Integer> connectedUserIDs = user.getConnections(); // Assuming 'Connections' is a list of User IDs

        // Retrieve all posts from the connected users
        return postRepository.findPostsByUserIDs(connectedUserIDs);
    }
}
