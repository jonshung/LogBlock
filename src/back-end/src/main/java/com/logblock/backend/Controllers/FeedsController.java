package com.logblock.backend.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.DataSource.DTO.PostingDTO;
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

    public static class FeedGenerationFilter {
        public List<Integer> exclude;
        public Integer limit;

        public List<Integer> getExcluding() {
            return exclude;
        }

        public Integer getLimit() {
            return limit;
        }
    };

    /**
     * Generate the user's News Feed.
     *
     * @param userID ID of the user
     * @return Response with the generated news feed posts
     */
    @PostMapping( path = "/news/{userID}", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostingDTO>> generateNewsFeed(@PathVariable int userID, @RequestBody FeedGenerationFilter filter) {
        List<Posting> posts;
        Integer limit = 5;
        if(filter.getLimit() != null) {
            limit = filter.getLimit();
        }
        if(filter.getExcluding() != null) {
            posts = newsFeedService.generate(userID, filter.getExcluding(), limit);
        } else {
            posts = newsFeedService.generate(userID, new ArrayList<>(), limit);
        }
        List<PostingDTO> res = new ArrayList<>();
        for(Posting p : posts) {
            res.add(PostingDTO.toDTO(p));
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Generate the Exploration Feed (Trending Feed).
     *
     * @return Response with the generated exploration feed posts
     */
    @PostMapping( path = "/exploration/", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostingDTO>> generateExplorationFeed(@RequestBody FeedGenerationFilter filter) {
        List<Posting> posts;
        Integer limit = 5;
        if(filter.getLimit() != null) {
            limit = filter.getLimit();
        }
        if(filter.getExcluding() != null) {
            posts = explorationFeedService.generate(filter.getExcluding(), limit);
        } else {
            posts = explorationFeedService.generate(new ArrayList<>(), limit);
        }
        List<PostingDTO> res = new ArrayList<>();
        for(Posting p : posts) {
            res.add(PostingDTO.toDTO(p));
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
