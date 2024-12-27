package com.logblock.backend.FeedsGenerationService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.Connection;
import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Repository.ConnectionRepository;
import com.logblock.backend.DataSource.Repository.PostRepository;

@Service
public class NewFeedService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    /**
     * Generate the News Feed for a user based on their connections.
     *
     * @param userID ID of the user
     * @return List of posts generated for the user's News Feed
     */
    public List<Posting> generate(int userID, List<Integer> exclusion_ids, Integer limit) {
        // Get the list of connected user IDs
        List<Connection> connectionEntries = connectionRepository.findByConnectorID(userID);
        if (connectionEntries == null) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu không có kết nối
        }

        List<Posting> results = new ArrayList<>();
        for (Connection c : connectionEntries) {
            List<Posting> posts = postRepository.findPostingsByUserID(c.getConnectedID());
            for (Posting p : posts) {
              if (limit <= 0) {
                  return results;
              }
              if (exclusion_ids.contains(p.getPostID())) {
                  continue;
              }
              results.add(p);
              --limit;
            }
        }
        return results;
    }

    // public List<Posting> generate(int userID) {
    // // Get the list of connected user IDs
    // List<Connection> connectionEntries =
    // connectionRepository.findByConnectorID(userID);
    // List<Posting> results = new ArrayList<>();
    // for(Connection c : connectionEntries) {
    // List<Posting> posts =
    // postRepository.findPostingsByUserID(c.getConnectedID());
    // results.addAll(posts);
    // }
    // return results;
    // }
}
