package com.logblock.backend.Controllers;

import com.logblock.backend.FeedsGenerationService.NewFeedService;
import com.logblock.backend.FeedsGenerationService.ExplorationFeedService;
import com.logblock.backend.DataSource.Model.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feeds")
public class FeedsController {

    @Autowired
    private NewFeedService newsFeedService;

    @Autowired
    private ExplorationFeedService explorationFeedService;

    /**
     * Generate the user's News Feed.
     *
     * @param userID ID of the user
     * @return Response with the generated news feed posts
     */
    @GetMapping("/news/{userID}")
    public ResponseEntity<List<Posting>> generateNewsFeed(@PathVariable int userID) {
        List<Posting> posts = newsFeedService.generate(userID);
        return posts != null ? ResponseEntity.ok(posts) : ResponseEntity.noContent().build();
    }

    /**
     * Generate the Exploration Feed (Trending Feed).
     *
     * @return Response with the generated exploration feed posts
     */
    @GetMapping("/exploration")
    public ResponseEntity<List<Posting>> generateExplorationFeed() {
        List<Posting> posts = explorationFeedService.generate();
        return posts != null ? ResponseEntity.ok(posts) : ResponseEntity.noContent().build();
    }
}
