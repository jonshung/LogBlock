package com.logblock.backend.FeedsGenerationService;

import java.util.ArrayList;
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
    public List<Posting> generate(List<Integer> exclusion_ids, Integer limit) {
        // Retrieve all posts ordered by the number of upvotes within 2 weeks
        // trending posts
        List<Posting> pre = postRepository.findTrendingPosts(limit);
        List<Posting> results = new ArrayList<>();
        pre.removeIf(
            p -> exclusion_ids.contains(p.getPostID())
        );
        
        for (Posting p : pre) {
            if(limit <= 0) {
                return results;
            }
            results.add(p);
            --limit;
        }
        return results;
    }
}
