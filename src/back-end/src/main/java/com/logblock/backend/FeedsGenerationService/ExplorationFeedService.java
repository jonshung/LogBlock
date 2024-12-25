package com.logblock.backend.FeedsGenerationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Repository.PostRepository;

@Service
public class ExplorationFeedService {

    @Autowired
    private PostRepository postRepository;

    /**
     * Generate the Exploration Feed by fetching trending posts.
     *
     * @return List of trending posts
     */
    public List<Posting> generate() {
        // Retrieve all posts ordered by the number of upvotes within 2 weeks
        // trending posts
        return postRepository.findTrendingPosts(); // Assuming there is a method to get trending posts
    }
}
