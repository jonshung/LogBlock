package com.logblock.backend.PostServiceTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logblock.backend.DataSource.Model.ExpertSuggestedSolution;
import com.logblock.backend.DataSource.Model.ESSId;
import com.logblock.backend.DataSource.DTO.EssDTO;
import com.logblock.backend.DataSource.Repository.ExpertSuggestedSolutionRepository;
import com.logblock.backend.PostService.ExpertSuggestedSolutionService;
import com.logblock.backend.AuthenticationService.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.Date;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // Use a test profile for unit tests
public class ExpertSuggestedSolutionServiceTest {

    @Mock
    private ExpertSuggestedSolutionRepository essRepository; // Mock the repository

    @InjectMocks
    private ExpertSuggestedSolutionService essService; // Inject the mocks into the service

    @Autowired
    private AccountService accountService; // Inject AccountService to create users

    private ExpertSuggestedSolution testESS;
    private EssDTO testESSDTO;
    private ESSId testESSId;
    private int expertUserID;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks
        MockitoAnnotations.openMocks(this);

        // Create a test expert (user)
        String expertEmail = "expert@example.com";
        expertUserID = accountService.createAccount(expertEmail); // Create expert user

        // Create a test ExpertSuggestedSolution
        testESS = new ExpertSuggestedSolution(1, expertUserID, "This is a solution caption", new Date(), new Date());
        testESS.setSolutionID(1);

        // Create a DTO version of the ESS
        testESSDTO = new EssDTO(1, expertUserID, 101, "This is a solution caption", new Date(), new Date());

        // ESSId for test
        testESSId = new ESSId(expertUserID, 1);
    }

    @Test
    public void testCreateESS() {
        // Arrange: mock the repository's addESS method to return the ESS ID
        when(essRepository.addESS(any(ExpertSuggestedSolution.class))).thenReturn(testESS.getSolID());

        // Act: call createESS on the service
        int createdESSId = essService.createESS(testESSDTO);

        // Assert: verify the service returns the correct ESS ID
        assertEquals(testESS.getSolID(), createdESSId);
    }

    @Test
    public void testGetESS() {
        // Arrange: mock the repository's findById method to return a test ESS
        when(essRepository.findById(testESSId)).thenReturn(Optional.of(testESS));

        // Act: call getESS on the service
        Optional<ExpertSuggestedSolution> result = essService.getESS(expertUserID, 1);

        // Assert: check if the returned ESS matches the expected result
        assertTrue(result.isPresent());
        assertEquals(testESS.getSolID(), result.get().getSolID());
    }

    @Test
    public void testUpdateESS() {
        // Arrange: mock the updateESS method to return the updated ESS ID
        when(essRepository.updateESS(eq(testESSId), any(ExpertSuggestedSolution.class)))
                .thenReturn(testESS.getSolID());

        // Act: call updateESS on the service
        int updatedESSId = essService.updateESS(expertUserID, 1, testESS);

        // Assert: verify the updateESS method returns the updated ESS ID
        assertEquals(testESS.getSolID(), updatedESSId);
    }

    @Test
    public void testDeleteESS() {
        // Arrange: mock the removeESS method to return 1 (successful deletion)
        when(essRepository.removeESS(testESSId)).thenReturn(1);

        // Act: call deleteESS on the service
        int result = essService.deleteESS(expertUserID, 1);

        // Assert: verify that the deletion was successful
        assertEquals(1, result);
    }

    @Test
    public void testDeleteESS_NotFound() {
        // Arrange: mock the removeESS method to return 0 (ESS not found)
        when(essRepository.removeESS(testESSId)).thenReturn(0);

        // Act: call deleteESS on the service
        int result = essService.deleteESS(expertUserID, 1);

        // Assert: verify that the deletion failed (ESS not found)
        assertEquals(0, result);
    }
}
