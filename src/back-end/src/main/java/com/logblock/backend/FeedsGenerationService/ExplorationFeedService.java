package com.logblock.backend.FeedsGenerationService;

import com.logblock.backend.DataSource.Model.Post;
import com.logblock.backend.DataSource.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExplorationFeedService {

    @Autowired
    private PostRepository postRepository;

    /**
     * Generate the Exploration Feed by fetching trending posts.
     *
     * @return List of trending posts
     */
    public List<Post> generate() {
        // Retrieve all posts ordered by popularity or other metrics that represent
        // trending posts
        return postRepository.findTrendingPosts(); // Assuming there is a method to get trending posts
    }
}
