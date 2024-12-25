package com.logblock.backend.PostServiceTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.logblock.backend.DataSource.DTO.PostingDTO;
import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.DataSource.Repository.PostRepository;
import com.logblock.backend.DataSource.Repository.ProfileRepository;
import com.logblock.backend.PostService.PostService;

import jakarta.transaction.Transactional;

/**
 * Template for service and data access testing for LogBlock.
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ActiveProfiles("test")
public class PostTest {
   
    // Start from here.
    @Autowired
    private ProfileRepository userRepo;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepo;

    @Test
    void testScenarioInsertionStandard() {
        Profile u = new Profile("johndoe@gmail.com", "John Doe", "This is John Doe.", "https://example.com", 0);
        userRepo.addUser(u);

        // Insertion
        List<List<String>> test_inputs = List.of(
            List.of(
                String.valueOf(u.getUserID()), // User ID
                "Caption1" // Post Caption
            ),
            List.of(
                String.valueOf(u.getUserID()), // User ID
                "Caption2" // Post Caption
            ),
            List.of(
                String.valueOf(u.getUserID()), // User ID
                "Caption3" // Post Caption
            )
        );

        List<Integer> ids = new ArrayList<>(test_inputs.size());
        for(List<String> tcase : test_inputs) {
            PostingDTO dto = new PostingDTO(0, Integer.parseInt(tcase.get(0)), tcase.get(1), new Date(), new Date());
            int id = this.postService.createPost(dto);
            ids.add(id);
        }

        assertEquals(this.postRepo.findAllPostings().size(), test_inputs.size());
        // Checking
        for(int i = 0; i < ids.size(); i++) {
            Posting p = this.postService.getPost(ids.get(i));
            assertNotNull(p);

            assertEquals(test_inputs.get(i).get(0), String.valueOf(p.getAuthorID()));
            assertEquals(test_inputs.get(i).get(1), p.getCaption());
        }
    }
}
