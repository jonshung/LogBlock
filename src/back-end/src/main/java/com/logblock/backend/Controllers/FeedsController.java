package com.logblock.backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.FeedsGenerationService.ExplorationFeedService;
import com.logblock.backend.FeedsGenerationService.NewFeedService;

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
        return posts != null ? ResponseEntity.ok(posts) : ResponseEntity.internalServerError().build();
    }

    /**
     * Generate the Exploration Feed (Trending Feed).
     *
     * @return Response with the generated exploration feed posts
     */
    @GetMapping("/exploration")
    public ResponseEntity<List<Posting>> generateExplorationFeed() {
        List<Posting> posts = explorationFeedService.generate();
        return posts != null ? ResponseEntity.ok(posts) : ResponseEntity.internalServerError().build();
    }
}
